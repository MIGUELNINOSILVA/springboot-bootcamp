"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const json_bigint_string_1 = __importDefault(require("json-bigint-string"));
const strip_json_comments_1 = __importDefault(require("strip-json-comments"));
const runtime_core_1 = require("@postman/runtime.core");
const runtime_grpc_utils_1 = require("@postman/runtime.grpc-utils");
const metadata_1 = require("./metadata");
exports.default = (async function handler(item, agent, context) {
    const { isTLS } = (0, runtime_grpc_utils_1.parseURL)(item.payload.url);
    const settings = item.payload.settings || {};
    const metadata = (0, metadata_1.toRawMetadata)(item.payload.metadata);
    const events = runtime_core_1.EventChannel.specific(context.events);
    const proxyOptions = typeof context.proxy === 'function' ?
        await context.proxy(item.payload.url, settings.secureConnection)
        : context.proxy;
    const channelOptions = {
        'grpc.max_send_message_length': -1,
        'grpc.max_receive_message_length': settings.maxResponseMessageSize ?
            settings.maxResponseMessageSize * 1024 * 1024
            : -1,
        'grpc.ssl_target_name_override': settings.serverNameOverride || '',
        'grpc.enable_http_proxy': proxyOptions?.ignoreEnvironmentVariables && !proxyOptions?.url ? 0 : 1,
    };
    const callOptions = {
        connectionTimeout: settings.connectionTimeout || Infinity,
    };
    const tlsOptions = isTLS || settings.secureConnection ?
        {
            rejectUnauthorized: Boolean(settings.strictSSL),
            secureContext: typeof context.secureContext === 'function' ?
                await context.secureContext(item.payload.url)
                : context.secureContext,
        }
        : undefined;
    let descriptor;
    if (item.extensions.schema && item.extensions.schema.source !== 'none') {
        descriptor = await agent.resolveSchema(item.extensions.schema, {
            url: item.payload.url,
            channelOptions: { ...channelOptions },
            callOptions: { ...callOptions },
            tlsOptions: { ...tlsOptions },
            proxy: proxyOptions?.url,
            metadata: [...metadata],
        });
    }
    if (!descriptor) {
        if (item.payload.methodDescriptor) {
            descriptor = item.payload.methodDescriptor;
        }
        else {
            throw new Error('Missing service definition');
        }
    }
    const jsonDescriptor = (0, runtime_grpc_utils_1.toJSONDescriptor)(descriptor);
    const definition = new runtime_grpc_utils_1.Definition(jsonDescriptor);
    const method = definition.methods.get(item.payload.methodPath);
    if (!method) {
        throw new Error(`Method not found: "${item.payload.methodPath}"`);
    }
    let message;
    if (method.kind === 'unary' || method.kind === 'server-stream') {
        const composedStr = item.payload.message.content.trim();
        message = (0, strip_json_comments_1.default)(composedStr, { whitespace: false }) || '{}';
        if (!method.requestType.validate(message)) {
            throw new Error('Message violates its Protobuf type definition');
        }
    }
    const invocation = await agent.invoke({
        url: item.payload.url,
        descriptor: jsonDescriptor,
        method: item.payload.methodPath,
        includeDefaultFields: Boolean(settings.includeDefaultFields ?? true),
        proxy: proxyOptions?.url,
        channelOptions,
        callOptions,
        tlsOptions,
        metadata,
        message,
    });
    events.emit('sent-request-header', {
        metadata: (0, metadata_1.toItemMetadata)(metadata),
        isRequestStreamed: ['client-stream', 'bidi'].includes(method.kind),
        isResponseStreamed: ['server-stream', 'bidi'].includes(method.kind),
    });
    if (message !== undefined) {
        events.emit('sent-request-data', { data: json_bigint_string_1.default.parse(message) });
    }
    const onEnd = () => invocation.end();
    const onCancel = () => invocation.cancel();
    const onWrite = async (event) => {
        try {
            const message = event.payload.json;
            const resolvedMessage = context.variables.replaceIn(message);
            await invocation.write(resolvedMessage);
            events.emit('sent-request-data', {
                data: json_bigint_string_1.default.parse(resolvedMessage),
            });
        }
        catch (err) {
            events.emit('internal:transient-error', err);
        }
    };
    return new Promise((resolve) => {
        const onDone = () => {
            invocation.cancel();
            events.off('write', onWrite).off('end', onEnd).off('cancel', onCancel);
            resolve();
        };
        events
            .on('write', onWrite)
            .on('end', onEnd)
            .on('cancel', onCancel)
            .onCleanup(onDone);
        invocation
            .on('response', (data) => {
            events.emit('received-response-data', { data });
        })
            .on('metadata', (metadata) => {
            events.emit('received-response-header', {
                metadata: (0, metadata_1.toItemMetadata)(metadata),
            });
        })
            .on('status', (status) => {
            events.emit('status', {
                statusCode: status.code,
                statusMessage: status.message,
                metadata: (0, metadata_1.toItemMetadata)(status.metadata),
                timings: status.timings,
            });
            onDone();
        })
            .on('error', (err) => {
            events.emit('internal:error', err);
            onDone();
        });
    });
});
//# sourceMappingURL=handler.js.map
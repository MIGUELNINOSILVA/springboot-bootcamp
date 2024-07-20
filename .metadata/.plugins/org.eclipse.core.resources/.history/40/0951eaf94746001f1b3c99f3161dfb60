"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const runtime_core_1 = require("@postman/runtime.core");
const strip_json_comments_1 = __importDefault(require("strip-json-comments"));
const file_type_1 = __importDefault(require("file-type"));
const parse_message_1 = require("./utils/parse-message");
const data_transformers_1 = require("./utils/data-transformers");
const mime_types_1 = require("./utils/mime-types");
const validators_1 = require("./utils/validators");
const parse_url_1 = require("./utils/parse-url");
const uuid_1 = require("uuid");
exports.default = (async function handler(item, agent, context) {
    if (!item.payload.url) {
        throw new Error('URL is required');
    }
    const url = (0, parse_url_1.parseUrl)(item.payload.url, item.payload.queryParams);
    const { variables } = context;
    const settings = {
        clientVersion: item.payload.settings?.version ?? '3',
        connectOptions: {
            handshakeTimeout: item.payload.settings?.handshakeTimeout,
            handshakePath: item.payload.settings?.path ?? '/socket.io',
        },
        reconnectOptions: {
            retryCount: item.payload.settings?.retryCount ?? 0,
            retryDelay: item.payload.settings?.retryDelay ?? 5000,
        },
    };
    const headers = (0, data_transformers_1.transformKVItemToObject)(item.payload.headers || []);
    const tlsOptions = url.isTLS ?
        {
            rejectUnauthorized: Boolean(item.payload.settings?.strictSSL),
            secureContext: typeof context.secureContext === 'function' ?
                await context.secureContext(item.payload.url)
                : context.secureContext,
        }
        : {};
    const connection = await agent.connect({
        url: url.url,
        headers,
        ...settings,
        tlsOptions,
    });
    const events = runtime_core_1.EventChannel.specific(context.events);
    const encoder = new TextEncoder();
    const subscriptions = new Set();
    const onPublish = (event) => {
        const eventName = event.payload.eventName;
        const messages = [];
        const consumerMessages = [];
        let totalSize = 0;
        event.payload.payload.forEach((message, index) => {
            let payload = message.message;
            if (variables) {
                payload = variables.replaceIn(payload);
            }
            if (message.type === mime_types_1.MessageType.POSTMAN_JSON) {
                payload = (0, strip_json_comments_1.default)(payload, { whitespace: false });
            }
            else if (message.type === mime_types_1.MessageType.BINARY) {
                payload = (0, parse_message_1.binaryStringToUint8Array)(payload, message.subType);
            }
            messages.push(payload);
            consumerMessages.push({
                id: (0, uuid_1.v4)(),
                name: (0, parse_message_1.getArgumentName)(index),
                value: payload instanceof Uint8Array ? (0, parse_message_1.uint8ArrayToString)(payload) : payload,
                meta: {
                    mimeType: mime_types_1.MIME_TYPE_MAP[message.type],
                    size: payload instanceof Uint8Array ?
                        payload.byteLength
                        : encoder.encode(payload).byteLength,
                },
            });
            totalSize += consumerMessages.at(-1).meta.size;
        });
        connection.publish(eventName, messages, {
            acknowledgement: Boolean(event.payload.acknowledgement),
        });
        events.emit('sent-message', {
            data: { payload: JSON.stringify(consumerMessages), type: 'Array' },
            size: totalSize,
            eventName,
        });
    };
    const onSubscribe = (event) => {
        if (subscriptions.has(event.payload.eventName)) {
            return;
        }
        connection.subscribe(event.payload.eventName);
    };
    const onUnsubscribe = (event) => {
        if (!subscriptions.has(event.payload.eventName)) {
            return;
        }
        connection.unsubscribe(event.payload.eventName);
    };
    const onDisconnect = () => {
        connection.disconnect();
    };
    let isEndEventReceived = false;
    return new Promise((resolve) => {
        const onDone = () => {
            events
                .off('disconnect', onDisconnect)
                .off('publish', onPublish)
                .off('subscribe', onSubscribe)
                .off('unsubscribe', onUnsubscribe);
            if (!isEndEventReceived) {
                connection.disconnect();
            }
            resolve();
        };
        events
            .on('publish', onPublish)
            .on('subscribe', onSubscribe)
            .on('unsubscribe', onUnsubscribe)
            .on('disconnect', onDisconnect)
            .onCleanup(onDone);
        connection
            .on('open', ({ request, response }) => {
            const payload = {};
            if (request) {
                const { href, httpVersion, ...rest } = request;
                payload.handshakeRequest = { url: href, ...rest };
            }
            if (response) {
                const { httpVersion, ...rest } = response;
                payload.handshakeResponse = rest;
            }
            events.emit('connected', payload);
            item.payload.events?.forEach(({ name, subscribeOnConnect }) => {
                if (!subscribeOnConnect) {
                    return;
                }
                connection.subscribe(name);
            });
        })
            .on('reconnect', () => {
            events.emit('reconnecting', {});
        })
            .on('error', ({ handshakeRequest: request, handshakeResponse: response, error }) => {
            const payload = {
                error: { message: error.message },
            };
            if (request) {
                const { href, httpVersion, ...rest } = request;
                payload.handshakeRequest = { url: href, ...rest };
            }
            if (response) {
                const { httpVersion, ...rest } = response;
                payload.handshakeResponse = rest;
            }
            events.emit('error', payload);
        })
            .on('subscribed', ({ event }) => {
            events.emit('subscribe-event', { eventName: event });
            subscriptions.add(event);
        })
            .on('unsubscribed', ({ event }) => {
            events.emit('unsubscribe-event', { eventName: event });
            subscriptions.delete(event);
        })
            .on('message', async (event) => {
            const eventName = event.eventName;
            let totalSize = 0;
            const messages = await Promise.all(event.messages.map(async (message, index) => {
                const meta = {
                    mimeType: 'text/plain',
                    size: 0,
                    fileExtension: 'txt',
                };
                if (typeof message === 'string') {
                    meta.size = encoder.encode(message).byteLength;
                    totalSize += meta.size;
                    (0, validators_1.isJSON)(message) && (meta.mimeType = 'application/json');
                    return {
                        id: (0, uuid_1.v4)(),
                        name: (0, parse_message_1.getArgumentName)(index),
                        value: message,
                        meta,
                    };
                }
                meta.size = message.byteLength;
                totalSize += meta.size;
                try {
                    const result = await file_type_1.default.fromBuffer(message);
                    meta.mimeType = result?.mime || 'application/octet-stream';
                    meta.fileExtension = result?.ext || 'bin';
                }
                catch (err) {
                    meta.mimeType = 'application/octet-stream';
                    meta.fileExtension = 'bin';
                }
                finally {
                    return {
                        id: (0, uuid_1.v4)(),
                        name: (0, parse_message_1.getArgumentName)(index),
                        value: (0, parse_message_1.uint8ArrayToString)(message),
                        meta,
                    };
                }
            }));
            events.emit('received-message', {
                data: { payload: JSON.stringify(messages), type: 'Array' },
                size: totalSize,
                eventName,
            });
        })
            .on('end', ({ reason, aborted }) => {
            isEndEventReceived = true;
            if (aborted) {
                events.emit('aborted', {});
            }
            else {
                events.emit('disconnected', { reason });
            }
            onDone();
        });
    });
});
//# sourceMappingURL=handler.js.map
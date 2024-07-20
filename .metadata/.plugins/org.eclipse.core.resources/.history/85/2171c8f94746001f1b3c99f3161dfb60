"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.ServerMethods = void 0;
const runtime_runtime_rpc_1 = require("@postman/runtime.runtime-rpc");
const GRPCClient = __importStar(require("@postman/runtime.grpc-client"));
const MQTTClient = __importStar(require("@postman/runtime.mqtt-client"));
const SocketIOClient = __importStar(require("@postman/runtime.socket.io-client"));
const WebsocketClient = __importStar(require("@postman/runtime.websocket-client"));
/*
    This file defines the standard RPC methods supported by Runtime servers.
*/
exports.ServerMethods = {
    'grpc-request.invoke': async (options) => {
        const invocation = GRPCClient.invoke(options);
        const remote = new runtime_runtime_rpc_1.ProxyResult(invocation);
        invocation.on('status', () => remote.disconnect());
        remote.onCleanup(() => invocation.cancel());
        return { remote };
    },
    'mqtt-request.connect': async (options) => {
        const connection = MQTTClient.connect(options);
        const remote = new runtime_runtime_rpc_1.ProxyResult(connection);
        connection.on('close', () => remote.disconnect());
        remote.onCleanup(() => connection.disconnect());
        return { remote };
    },
    'websocket-request.connect': async (options) => {
        const connection = WebsocketClient.connect(options);
        const remote = new runtime_runtime_rpc_1.ProxyResult(connection);
        connection.on('end', () => remote.disconnect());
        remote.onCleanup(() => connection.close());
        return { remote };
    },
    'socketio-request.connect': async (options) => {
        const connection = SocketIOClient.connect(options);
        const remote = new runtime_runtime_rpc_1.ProxyResult(connection);
        connection.on('end', () => remote.disconnect());
        remote.onCleanup(() => connection.disconnect());
        return { remote };
    },
};
//# sourceMappingURL=server-methods.js.map
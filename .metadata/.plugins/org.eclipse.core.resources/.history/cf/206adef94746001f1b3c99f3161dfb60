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
exports.Client = void 0;
const mqtt = __importStar(require("mqtt/dist/mqtt"));
const runtime_mqtt_utils_1 = require("@postman/runtime.mqtt-utils");
const connection_1 = require("./connection");
const custom_stream_1 = require("./custom-stream");
const utilities_1 = require("./utilities");
/*
    An MQTT Client capable of connecting to a broker, publishing messages,
    subscribing to topics, and receiving messages.

    Instantiates the client and connects to the broker using a Connection object.
*/
class Client {
    constructor(options) {
        this.client = null;
        const { tlsOptions, mqttOptions } = options;
        const parsedURL = (0, runtime_mqtt_utils_1.parseURL)(options.url);
        let clientOptions = (0, utilities_1.validateSettings)(parsedURL, mqttOptions);
        if (parsedURL.isTLS && tlsOptions) {
            clientOptions = {
                ...clientOptions,
                ...tlsOptions,
            };
        }
        // Create the client, but don't connect yet
        try {
            this.client = new mqtt.Client(() => (0, custom_stream_1.customStream)({ parsedURL, tlsOptions }), { ...clientOptions, manualConnect: true });
        }
        catch (err) {
            throw new Error(`Unable to create mqtt client: ${err}`);
        }
    }
    connect() {
        if (!this.client) {
            throw new Error('Client is not initialized');
        }
        return new connection_1.Connection(this.client);
    }
}
exports.Client = Client;
//# sourceMappingURL=client.js.map
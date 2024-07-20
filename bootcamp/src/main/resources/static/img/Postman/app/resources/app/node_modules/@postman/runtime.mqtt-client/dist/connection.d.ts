/// <reference types="node" />
/// <reference types="node" />
import { AgentPublishOptions, AgentSubscribeOptions } from '@postman/runtime.mqtt-utils';
import { EventEmitter } from 'events';
import * as mqtt from 'mqtt/dist/mqtt';
export declare class Connection extends EventEmitter {
    private client;
    private reconnectCount;
    constructor(client: mqtt.MqttClient);
    publish(topic: string, message: string | Buffer, options: AgentPublishOptions): void;
    subscribe(topic: string, options: AgentSubscribeOptions): void;
    unsubscribe(topic: string): void;
    disconnect(force?: boolean, options?: Partial<mqtt.IDisconnectPacket>): void;
    getClient(): mqtt.MqttClient;
    isConnected(): boolean;
}

import type { Packet } from 'mqtt-packet';
/**
 * A function to remove null and extra properties from a packet. So the packets match their documentation.
 * https://github.com/mqttjs/mqtt-packet
 */
export declare function parsePacket(obj: Packet): Partial<import("mqtt-packet").IConnackPacket> | Partial<import("mqtt-packet").ISubackPacket> | Partial<import("mqtt-packet").IUnsubackPacket> | Partial<import("mqtt-packet").IPubackPacket | import("mqtt-packet").IPubcompPacket | import("mqtt-packet").IPubrelPacket | import("mqtt-packet").IPubrecPacket> | Partial<import("mqtt-packet").IPublishPacket> | Partial<import("mqtt-packet").IDisconnectPacket> | Partial<import("mqtt-packet").IAuthPacket | import("mqtt-packet").IConnectPacket | import("mqtt-packet").ISubscribePacket | import("mqtt-packet").IUnsubscribePacket | import("mqtt-packet").IPingreqPacket | import("mqtt-packet").IPingrespPacket>;

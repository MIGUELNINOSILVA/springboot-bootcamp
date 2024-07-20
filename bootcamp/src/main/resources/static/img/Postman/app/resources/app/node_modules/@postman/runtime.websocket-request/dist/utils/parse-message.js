"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.uint8ArrayToString = exports.binaryStringToUint8Array = void 0;
const mime_types_1 = require("./mime-types");
const validators_1 = require("./validators");
function binaryStringToUint8Array(str, encoding) {
    if (encoding === mime_types_1.MessageSubType.BASE64) {
        const binaryString = str
            .split(/\r?\n/)
            .map((line) => line.replace(/\s+/g, ''))
            .filter(Boolean)
            .map(validators_1.validateBase64)
            .map((line) => atob(line))
            .join('');
        const len = binaryString.length;
        const bytes = new Uint8Array(len);
        for (let i = 0; i < len; i++) {
            bytes[i] = binaryString.charCodeAt(i);
        }
        return bytes;
    }
    if (encoding === mime_types_1.MessageSubType.HEX) {
        const cleanedString = str.replace(/\s+/g, '');
        (0, validators_1.validateHexadecimal)(cleanedString);
        const length = cleanedString.length;
        const uint8Array = new Uint8Array(length / 2);
        for (let i = 0; i < length; i += 2) {
            uint8Array[i / 2] = parseInt(cleanedString.substring(i, i + 2), 16);
        }
        return uint8Array;
    }
    throw new TypeError(`Unrecognized binary format: ${encoding}`);
}
exports.binaryStringToUint8Array = binaryStringToUint8Array;
function uint8ArrayToString(message) {
    let str = '';
    for (let i = 0; i < message.length; i++) {
        str += String.fromCharCode(message[i]);
    }
    return str;
}
exports.uint8ArrayToString = uint8ArrayToString;
//# sourceMappingURL=parse-message.js.map
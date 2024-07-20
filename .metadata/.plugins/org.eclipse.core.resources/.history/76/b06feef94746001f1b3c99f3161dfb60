"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.prepareProtocols = exports.transformKVItemToObject = void 0;
function transformKVItemToObject(items) {
    if (!items || items.length === 0)
        return {};
    return items.reduce((acc, { key, value, disabled }) => {
        if (key === null ||
            value === null ||
            Boolean(disabled) ||
            key === undefined ||
            value === undefined)
            return acc;
        acc[key] = value;
        return acc;
    }, {});
}
exports.transformKVItemToObject = transformKVItemToObject;
function prepareProtocols(headers) {
    const headerKeys = Object.keys(headers);
    for (let i = headerKeys.length - 1; i >= 0; i--) {
        if (headerKeys[i].toLowerCase() === 'sec-websocket-protocol') {
            return headers[headerKeys[i]];
        }
    }
    return;
}
exports.prepareProtocols = prepareProtocols;
//# sourceMappingURL=data-transformers.js.map
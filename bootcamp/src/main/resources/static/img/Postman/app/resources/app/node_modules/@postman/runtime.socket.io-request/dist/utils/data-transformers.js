"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.transformKVItemToObject = void 0;
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
//# sourceMappingURL=data-transformers.js.map
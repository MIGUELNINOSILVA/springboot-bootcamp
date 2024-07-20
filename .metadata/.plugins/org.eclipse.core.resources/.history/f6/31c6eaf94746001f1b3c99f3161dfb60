"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.isJSON = exports.validateBase64 = exports.validateHexadecimal = void 0;
function validateHexadecimal(str) {
    if (!/^([a-fA-F\d]{2})*$/.test(str)) {
        throw new SyntaxError('Invalid Hexadecimal');
    }
    return str;
}
exports.validateHexadecimal = validateHexadecimal;
function validateBase64(str) {
    if (!/^([a-zA-Z\d+/]{4})*([a-zA-Z\d+/]{3}=|[a-zA-Z\d+/]{2}==)?$/.test(str)) {
        throw new SyntaxError('Invalid Base64');
    }
    return str;
}
exports.validateBase64 = validateBase64;
/**
 * Helper function to detect whether a give value is JSON or not
 *
 * @note This function only determines whether a given value is
 * JSON on best-effort basis and thus might result in false positives
 *
 * @param {String} value
 * @returns {Boolean}
 */
function isJSON(value) {
    value = value.trim();
    switch (value.charAt(0) + value.charAt(value.length - 1)) {
        case '[]':
        case '{}':
        case '0}': // socket.io open prefix
        case '4]': // socket.io message prefix
            return true;
        default:
            return false;
    }
}
exports.isJSON = isJSON;
//# sourceMappingURL=validators.js.map
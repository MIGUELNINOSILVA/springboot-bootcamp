"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseUrl = void 0;
function parseUrl(str, queryParams = []) {
    str = String(str); // For non-TypeScript applications
    // Test if the string doesn't start with a protocol
    if (!/^[a-z0-9+.-]+:\/\//i.test(str)) {
        str = `ws://${str}`; // Default protocol
    }
    let url;
    try {
        url = new URL(str);
    }
    catch (_) {
        throw new Error(`Invalid URL "${str}"`);
    }
    queryParams.forEach(({ key, value, disabled }) => {
        if (disabled ||
            key === undefined ||
            value === undefined ||
            key === null ||
            value === null) {
            return;
        }
        // Skip if query param already exists with the same values
        if (url.searchParams.get(key) === value) {
            return;
        }
        url.searchParams.set(key, value);
    });
    if (!url.host && !url.port && !url.pathname) {
        throw new Error(`Invalid URL "${str}"`);
    }
    const validProtocols = ['ws:', 'wss:'];
    if (!validProtocols.includes(url.protocol)) {
        throw new Error(`Invalid protocol "${url.protocol}//", try "ws://" instead`);
    }
    const { port } = url;
    return {
        protocol: url.protocol.replace(/:$/, ''),
        host: url.hostname,
        port: port,
        url: url.href,
        pathname: url.pathname,
        isTLS: url.protocol === 'wss:',
    };
}
exports.parseUrl = parseUrl;
//# sourceMappingURL=parse-url.js.map
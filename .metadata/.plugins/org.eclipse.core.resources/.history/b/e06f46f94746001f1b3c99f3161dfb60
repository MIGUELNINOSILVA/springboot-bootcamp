"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Client = void 0;
const secrets_js_1 = require("./secrets.js");
const items_js_1 = require("./items.js");
class Client {
    constructor(innerClient) {
        this.secrets = new secrets_js_1.SecretsSource(innerClient);
        this.items = new items_js_1.ItemsSource(innerClient);
    }
}
exports.Client = Client;

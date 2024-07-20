import { InnerClient } from "./core.js";
import { SecretsApi } from "./secrets.js";
import { ItemsApi } from "./items.js";
export declare class Client {
    secrets: SecretsApi;
    items: ItemsApi;
    constructor(innerClient: InnerClient);
}

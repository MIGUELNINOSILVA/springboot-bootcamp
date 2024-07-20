import { InnerClient } from "./core.js";
export interface SecretsApi {
    resolve(secretReference: string): Promise<string>;
}
export declare class SecretsSource implements SecretsApi {
    #private;
    constructor(inner: InnerClient);
    resolve(secretReference: string): Promise<string>;
}

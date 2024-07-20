import { InnerClient } from "./core.js";
import * as types from "./types.js";
export interface ItemsApi {
    /**
     *  Create a new item
     */
    create(item: types.Item): Promise<types.Item>;
    /**
     *  Get an item by vault and item ID
     */
    get(vaultId: string, itemId: string): Promise<types.Item>;
    /**
     *  Update an existing item. Warning: Only text and concealed fields are currently supported. Other fields will be permanently lost when you update an item.
     */
    update(item: types.Item): Promise<types.Item>;
    /**
     *  Delete an item. Warning:  Information saved in fields other than text and concealed fields will be permanently lost.
     */
    delete(vaultId: string, itemId: string): Promise<void>;
}
export declare class ItemsSource implements ItemsApi {
    #private;
    constructor(inner: InnerClient);
    create(item: types.Item): Promise<types.Item>;
    get(vaultId: string, itemId: string): Promise<types.Item>;
    update(item: types.Item): Promise<types.Item>;
    delete(vaultId: string, itemId: string): Promise<void>;
}

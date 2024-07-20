"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.getOsName = exports.clientAuthConfig = void 0;
const os = __importStar(require("os"));
const LANGUAGE = "JS";
const VERSION = "0010008"; // v0.1.0-beta.8
/**
 * Creates a default client configuration.
 * @returns The client configuration to instantiate the client with.
 */
const clientAuthConfig = (userConfig) => {
    // TODO: Add logic for computing the correct sanitized version value for each platform
    const defaultOsVersion = "0.0.0";
    return {
        serviceAccountToken: userConfig.auth,
        programmingLanguage: LANGUAGE,
        sdkVersion: VERSION,
        integrationName: userConfig.integrationName,
        integrationVersion: userConfig.integrationVersion,
        requestLibraryName: "Fetch API",
        requestLibraryVersion: "Fetch API",
        os: (0, exports.getOsName)(),
        osVersion: defaultOsVersion,
        architecture: os.arch(),
    };
};
exports.clientAuthConfig = clientAuthConfig;
const getOsName = () => {
    // Only supported on Node.js
    const os_name = os.type().toLowerCase();
    if (os_name === "windows_nt") {
        return "windows";
    }
    return os_name;
};
exports.getOsName = getOsName;

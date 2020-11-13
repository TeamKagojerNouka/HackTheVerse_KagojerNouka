import httpClient from "./httpClient";

const END_POINT = "/auth";

// used to validate tokens received from social login
const convertToken = (params: object) => httpClient.post(`${END_POINT}/convert-token/`, params);

// used to refresh current access token
const getNewToken = (params: object) => httpClient.post(`${END_POINT}/token/`, params);

export {
    convertToken,
    getNewToken
}
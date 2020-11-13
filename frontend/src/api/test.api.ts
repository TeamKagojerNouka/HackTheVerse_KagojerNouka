import httpClient from "./httpClient";

const END_POINT = "/test";

const testWorkingApi = () => httpClient.get(END_POINT);

export {
    testWorkingApi
}
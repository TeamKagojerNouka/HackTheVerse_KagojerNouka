import httpClient from "./httpClient";

const END_POINT = "/api/suggest";

const getServiceSuggestions = (location: string, category: string, time: number) => httpClient.get(`${END_POINT}/service`, {
    params: {
        location,
        category,
        time
    }
});

export {
    getServiceSuggestions
}
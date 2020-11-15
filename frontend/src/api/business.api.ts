import httpClient from "./httpClient";

const END_POINT = "/api/business";

const getBusinessDetails = (businessID: number) => httpClient.get(END_POINT, {
    params: {
        id: businessID
    }
});

export {
    getBusinessDetails
}
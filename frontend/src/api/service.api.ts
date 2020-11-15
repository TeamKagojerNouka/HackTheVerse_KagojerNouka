import httpClient from "./httpClient";

const END_POINT = "/api/service";

const getServiceDetails = (serviceID: number) => httpClient.get(END_POINT, {
    params: {
        id: serviceID
    }
});

const getAllServices = () => httpClient.get(END_POINT);

export {
    getServiceDetails,
    getAllServices
}
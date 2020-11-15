import httpClient from "./httpClient";

const END_POINT = "/api/delivery";

const generateQRcode = (delivery: object) => httpClient.post(`${END_POINT}/`, delivery);

const getDeliveryList = () => httpClient.get(END_POINT);

export {
    generateQRcode,
    getDeliveryList
}
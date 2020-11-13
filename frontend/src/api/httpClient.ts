import axios, { AxiosInstance, AxiosRequestConfig } from "axios";

const httpClient: AxiosInstance = axios.create({
    baseURL: process.env.VUE_APP_API_URL,
    timeout: 2000
});

const authInterceptor = (config: AxiosRequestConfig) => {
    const accessToken = localStorage.getItem("accessToken");

    if (accessToken) {
        config.headers["Authorization"] = `Bearer ${accessToken}`;
    }
    return config;
}

httpClient.interceptors.request.use(authInterceptor);

export default httpClient;
import axios, { AxiosInstance } from "axios";

const baseUrl = import.meta.env.VITE_BACKEND_URL;

const apiClient: AxiosInstance = axios.create({
  baseURL: `${baseUrl}`,
   withCredentials: true
});

apiClient.interceptors.request.use(function (request) {
  request.headers['Access-Control-Allow-Origin'] = '*';
  return request;
}, function (error) {
  return Promise.reject(error);
});

apiClient.interceptors.response.use(function (response) {
  return response;
}, function (error) {
  return Promise.reject(error);
});


export default apiClient;
import axios from "axios";


const api = axios.create({
    baseURL: import.meta.env.VITE_API_URL, 
});

// Configuracion del interceptor
api.interceptors.request.use(config => {
        const token = localStorage.getItem("authToken");
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export default api;


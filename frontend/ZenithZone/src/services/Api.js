import Constant from '@/Constant';
import axios from 'axios';
import userService from './client/user.service';
import store from '@/store';

//Variable para controlar el proceso de refresh
let isRefreshing = false;

//Almacena la promesa del refresh en curso
let refreshPromise = null;

//Cola de solicitudes pendientes
let pendingRequests = [];

//Función para procesar la cola de solicitudes pendientes
const processPendingRequests = (token) => {
    pendingRequests.forEach(callback => callback(token));
    pendingRequests = [];
};

export default (URL) => {
    const api = axios.create({
        baseURL: URL,
    });

    //Crear una instancia separada para el refresh token
    const refreshApi = axios.create({
        baseURL: URL,
    });

    api.interceptors.request.use(
        (config) => {
            //No añadir token para endpoints de autenticación
            if (config.url.includes('/login') || config.url.includes('/refresh') || config.url.includes('/logout')) {
                return config;
            }

            const accessToken = localStorage.getItem('accessToken');

            if (accessToken) {
                console.log('api services using accessToken');
                config.headers = config.headers || {};
                config.headers['Authorization'] = `Bearer ${accessToken}`;
            }

            return config;
        },
        (error) => {
            return Promise.reject(error);
        }
    );

    api.interceptors.response.use(
        (response) => response,
        async (error) => {
            const originalRequest = error.config;

            //Si no es un error 401 o es una solicitud de autenticación, rechazar directamente
            if (!error.response ||
                error.response.status !== 401 ||
                originalRequest.url.includes('/login') ||
                originalRequest.url.includes('/refresh') ||
                originalRequest.url.includes('/logout') ||
                originalRequest._retry) {
                return Promise.reject(error);
            }

            //Marcar esta solicitud para evitar reintentos infinitos
            originalRequest._retry = true;

            const refreshToken = localStorage.getItem('refreshToken');
            if (!refreshToken) {
                console.warn('No refresh token available, logging out...');
                store.dispatch(`user/${Constant.LOGOUT}`);
                return Promise.reject(error);
            }

            //Si ya hay un refresh en curso, añadir esta solicitud a la cola
            if (isRefreshing) {
                return new Promise((resolve) => {
                    pendingRequests.push((token) => {
                        originalRequest.headers['Authorization'] = `Bearer ${token}`;
                        resolve(api.request(originalRequest));
                    });
                });
            }

            //Iniciar proceso de refresh
            isRefreshing = true;

            try {
                console.log('Token expirado, intentando refresh...');

                //Usar la instancia separada para el refresh
                if (!refreshPromise) {
                    refreshPromise = refreshApi.post(`main/refresh`, { refreshToken });
                }

                const response = await refreshPromise;
                console.log('Refresh exitoso, nuevo token obtenido');

                const newAccessToken = response.data.accessToken;
                localStorage.setItem('accessToken', newAccessToken);

                //Actualizar el token en la solicitud original
                originalRequest.headers['Authorization'] = `Bearer ${newAccessToken}`;

                //Procesar todas las solicitudes pendientes
                processPendingRequests(newAccessToken);

                //Reiniciar variables de control
                isRefreshing = false;
                refreshPromise = null;

                //Reintentar la solicitud original
                return api.request(originalRequest);
            } catch (refreshError) {
                console.error('Error al refrescar el token:', refreshError);
                isRefreshing = false;
                refreshPromise = null;
                store.dispatch(`user/${Constant.LOGOUT}`, { refreshToken });
                return Promise.reject(refreshError);
            }
        }
    );

    return api;
};

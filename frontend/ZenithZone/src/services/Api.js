import Constant from '@/Constant';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import userService from './client/user.service';
import store from '@/store';

export default (URL, isSpringboot = 'true') => {
    const api = axios.create({
        baseURL: URL,
    });

    api.interceptors.request.use(
        (config) => {
            const token = localStorage.getItem('token');
            const tokenAdmin = localStorage.getItem('tokenAdmin');
            const entrenadorToken = localStorage.getItem('entrenadorToken');
            // console.log(`api services isSpringboot: ${isSpringboot}`);

            config.headers = config.headers || {};

            if (token) {
                console.log(`api services is Token`);
                config.headers['Authorization'] = `Bearer ${token}`;
                config.headers['isSpringboot'] = isSpringboot;
            } else if (tokenAdmin) {
                console.log(`api services is tokenAdmin`);
                config.headers['Authorization'] = `Bearer ${tokenAdmin}`;
                config.headers['isSpringboot'] = isSpringboot;
            } else if (entrenadorToken) {
                console.log(`api services is entrenadorToken`);
                config.headers['Authorization'] = `Bearer ${entrenadorToken}`;
                config.headers['isSpringboot'] = isSpringboot;
            } else {
                config.headers['isSpringboot'] = isSpringboot;
            }

            // console.log('Request Headers:', config.headers);
            return config;
        },
        (error) => {
            return Promise.reject(error);
        }
    );

    api.interceptors.response.use(
        (response) => response, // Si la respuesta es correcta, continuar
        async (error) => {
            // Este IF evita un bucle infinito, ya que poner mal las credenciales de login genera un 401 tambien
            if (error.config && error.config.url.includes('/login')) {
                return Promise.reject(error);
            }

            if (error.response && error.response.status === 401) {
                const refreshToken = { refreshToken: localStorage.getItem('refreshToken') };

                if (!refreshToken) {
                    console.warn('No refresh token available, logging out...');
                    store.dispatch(`user/${Constant.LOGOUT}`);
                    return Promise.reject(error);
                }

                try {
                    const token = await userService.Refresh(refreshToken);
                    const newAccessToken = token.data.token;
                    localStorage.setItem('token', newAccessToken);
                    store.dispatch(`user/${Constant.INITIALIZE_USER}`, { "token": newAccessToken });

                    // Reintentar la solicitud original con el nuevo token.
                    error.config.headers['Authorization'] = `Bearer ${newAccessToken}`;
                    return api.request(error.config);
                } catch (refreshError) {
                    store.dispatch(`user/${Constant.LOGOUT}`, refreshToken);

                    return Promise.reject(refreshError);
                }
            }

            return Promise.reject(error);
        }
    );

    return api;
};

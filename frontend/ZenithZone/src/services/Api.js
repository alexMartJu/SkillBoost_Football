import Constant from '@/Constant';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

export default (URL, isSpringboot = 'true') => {
    const api = axios.create({
        baseURL: URL,
    });

    api.interceptors.request.use(
        (config) => {
            const token = localStorage.getItem('token');
            const tokenAdmin = localStorage.getItem('tokenAdmin');
            const tokenEntrenador = localStorage.getItem('tokenEntrenador');
            console.log(`api services isSpringboot: ${isSpringboot}`);

            config.headers = config.headers || {};

            if (token) {
                console.log(`api services is Token`);
                config.headers['Authorization'] = `Bearer ${token}`;
                config.headers['isSpringboot'] = isSpringboot;
            } else if (tokenAdmin) {
                console.log(`api services is tokenAdmin`);
                config.headers['Authorization'] = `Bearer ${tokenAdmin}`;
                config.headers['isSpringboot'] = isSpringboot;  
            } else if (tokenEntrenador) {
                console.log(`api services is tokenEntrenador`);
                config.headers['Authorization'] = `Bearer ${tokenEntrenador}`;
                config.headers['isSpringboot'] = isSpringboot;
            } else {
                config.headers['isSpringboot'] = isSpringboot;
            }

            console.log('Request Headers:', config.headers);
            return config;
        },
        (error) => {
            return Promise.reject(error);
        }
    );

    api.interceptors.response.use(
        (response) => response, // Si la respuesta es correcta, continuar
        (error) => {
            console.log('Error Response:', error.response);
            if (error.response && error.response.status === 401) {
                const store = useStore();
                const router = useRouter();
                try {
                    const refreshToken = localStorage.getItem('refreshToken');
                    console.log('Attempting token refresh...');
                    // Aquí podrías llamar a un endpoint de refresco
                    // y actualizar el token en el state y localStorage
                } catch {
                    console.log('Refresh token failed, logging out...');
                    store.dispatch(`user/${Constant.LOGOUT}`);
                    router.push({ name: 'home' });
                }
            }
            return Promise.reject(error);
        }
    );

    return api; // Devolver la instancia de Axios configurada
};

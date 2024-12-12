import Constant from '@/Constant';
import axios from 'axios'
import { useRouter } from 'vue-router';
import { useStore } from 'vuex'

export default (URL) => {
    const store = useStore();
    const router = useRouter();

    const api = axios.create({
        baseURL: URL
    })

    const token = localStorage.getItem('token');
    const tokenAdmin = localStorage.getItem('tokenAdmin');
    const tokenEntrenador = localStorage.getItem('tokenEntrenador');
    if (token) {
        console.log(`existe el token en api services`);
        api.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    } else if (tokenAdmin) {
        console.log(`existe el tokenAdmin en api services`);
        api.defaults.headers.common['Authorization'] = `Bearer ${tokenAdmin}`;
    } else if (tokenEntrenador) {
        console.log(`existe el tokenEntrenador en api services`);
        api.defaults.headers.common['Authorization'] = `Bearer ${tokenEntrenador}`;
    }

    api.defaults.headers.common['isSpringboot'] = true;

    api.interceptors.response.use(
        response => response,
        error => {
            console.log(error.response);
            if (error.response.status === 401) {
                store.dispatch(`user/${Constant.LOGOUT}`);
                router.push({ name: 'home' });
            }
            return Promise.reject(error);
        }
    )

    return api;
}
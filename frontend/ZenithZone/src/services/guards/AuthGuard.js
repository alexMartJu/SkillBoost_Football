import store from '../../store';
import Constant from '../../Constant';
import UserService from '../client/user.service';

export default {
    async authGuardAdmin(to, from, next) {
        try {
            if (localStorage.getItem('isAdmin') && localStorage.getItem('accessToken')) {
                const response = await UserService.GetCurrentUser();
                if (response.status === 200 && 
                    (response.data.tipoUsuario?.toLowerCase() === 'admin' || 
                     response.data.roles?.some(role => role.toLowerCase() === 'admin'))) {
                    next();
                } else {
                    next('/home');
                }
            } else {
                next('/home');
            }
        } catch (error) {
            store.dispatch(`user/${Constant.LOGOUT}`);
            next('/login');
        }
    },//authGuardAdmin

    async authGuardEntrenador(to, from, next) {
        try {
            if (localStorage.getItem('isEntrenador') && localStorage.getItem('accessToken')) {
                const response = await UserService.GetCurrentUser();
                if (response.status === 200 && 
                    (response.data.tipoUsuario?.toLowerCase() === 'entrenador' || 
                     response.data.roles?.some(role => role.toLowerCase() === 'entrenador'))) {
                    next();
                } else {
                    next('/home');
                }
            } else {
                next('/home');
            }
        } catch (error) {
            store.dispatch(`user/${Constant.LOGOUT}`);
            next('/login');
        }
    },//authGuardEntrenador

    async AuthGuard(to, from, next) {
        if (localStorage.getItem('isAuth') && localStorage.getItem('accessToken')) {
            try {
                await store.dispatch(`user/${Constant.INITIALIZE_USER}`);
                if (store.getters['user/GetIsAuth']) {
                    next();
                } else {
                    next('/login');
                }
            } catch (error) {
                next('/login');
            }
        } else {
            next('/login');
        }
    },//AuthGuard

    noAuthGuard(to, from, next) {
        if (!localStorage.getItem('accessToken')) {
            next();
        } else {
            next('/home');
        }
    },//noAuthGuard
}//guards

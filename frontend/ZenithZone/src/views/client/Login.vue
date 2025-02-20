<template>
    <loginRegisterFormVue :isLogin="true" @send="login" :errorMessage="errorMessage" />
</template>

<script>
import { ref } from 'vue';
import loginRegisterFormVue from '../../components/auth/loginRegisterForm.vue';
import Constant from '../../Constant';
import { useStore } from 'vuex';

export default {
    components: { loginRegisterFormVue },
    setup() {
        const store = useStore();
        const errorMessage = ref('');

        const login = async (data) => {
            try {
                await store.dispatch(`user/${Constant.LOGIN}`, data);
            } catch (error) {
                console.log(error.response);
                if (error.response && error.response.status === 401) {
                    errorMessage.value = 'Usuario o contraseña incorrectos.';
                } else {
                    errorMessage.value = 'Ocurrió un error. Inténtalo de nuevo.';
                }
            }
        }

        return { login, errorMessage };
    }
}
</script>

<style scoped>
</style>
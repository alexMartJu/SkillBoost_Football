<template>
    <loginRegisterFormVue :isLogin="false" @send="register" :errorMessage="errorMessage" :registerSuccesful="registerSuccesful" />
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
        const registerSuccesful = ref(false);

        const register = async (data) => {
            try {
                await store.dispatch(`user/${Constant.ADD_USER}`, data);
                registerSuccesful.value = true;
            } catch (error) {
                if (error.response && error.response.status === 422) {
                    errorMessage.value = 'El email registrado ya existe';
                } else {
                    console.log(`error`);
                    errorMessage.value = 'Ocurrió un error. Inténtalo de nuevo.';
                }
                registerSuccesful.value = false;
            }
            
        }

        return { register, errorMessage, registerSuccesful };
    }
}
</script>

<style scoped>
</style>
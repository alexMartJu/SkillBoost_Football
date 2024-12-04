<template>
    <main class="main-auth">
        <!-- <h1>Auth</h1> -->
        <LoginRegisterForm />
    </main>
</template>

<script>
import LoginRegisterForm from '@/components/auth/loginRegisterForm.vue';


export default {
    components: {
        LoginRegisterForm
    }
};
</script>

<style>
.main-auth {
    margin-top: 50px;
}
</style>
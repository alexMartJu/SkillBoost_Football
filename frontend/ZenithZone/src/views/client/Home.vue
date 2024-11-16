<template>
    <h1>Home</h1>
    <ul>
        <li v-for="deporte in state.deportes" :key="deporte.id">{{ deporte.nombre }}</li>
    </ul>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';

export default {
    setup() {
        const store = useStore();

        store.dispatch(`deportes/${Constant.INITIALIZE_DEPORTE}`);

        const state = reactive({
            deportes: computed(() => store.getters['deportes/GetDeportes'])
        });

        return { state };
    }
}
</script>

<style></style>
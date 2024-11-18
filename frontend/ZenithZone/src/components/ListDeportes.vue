<template>
    <div class="container">
        <div class="row g-4 justify-content-center">
            <CardDeportes v-for="deporte in state.deportes" :key="deporte.id" :deporte="deporte" class="col-md-3" />
        </div>
    </div>
</template>

<script>
import { reactive, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import Constant from '../Constant';
import CardDeportes from './CardDeportes.vue';

export default {
    components: {
        CardDeportes
    },
    setup() {
        const store = useStore();

        store.dispatch(`deportes/${Constant.INITIALIZE_DEPORTE}`);

        const state = reactive({
            deportes: computed(() => store.getters['deportes/GetDeportes'])
        });

        return { state };
    }
};
</script>

<style>
.deportes-list {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}
</style>
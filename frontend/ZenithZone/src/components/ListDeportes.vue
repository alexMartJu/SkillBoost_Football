<template>
    <div>
        <div class="deportes-list">
            <CardDeportes v-for="deporte in state.deportes" :key="deporte.id" :deporte="deporte" />
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
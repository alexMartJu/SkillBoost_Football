<template>
    <div class="container">
        <div class="row g-4 justify-content-center">
            <CardPistas v-for="pista in state.pistas" :key="pista.id" :pista="pista" class="col-md-3" />
        </div>
    </div>
</template>

<script>
import { reactive, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import Constant from '../Constant';
import CardPistas from './CardPistas.vue';

export default {
    components: {
        CardPistas
    },
    setup() {
        const store = useStore();

        store.dispatch(`pistas/${Constant.INITIALIZE_PISTA}`);

        const state = reactive({
            pistas: computed(() => store.getters['pistas/GetPistas'])
        });

        return { state };
    }
};
</script>

<style>
.Pistas-list {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}
</style>
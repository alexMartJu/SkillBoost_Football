<template>
    <div class="container">
        <div class="row g-4 justify-content-center">
            <CardPistas v-for="pista in pistas" :key="pista.id" :pista="pista" class="col-md-6" />
        </div>
    </div>
</template>

<script>
import { computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import CardPistas from './CardPistas.vue';

export default {
    components: { CardPistas },
    setup() {
        const store = useStore();

        onMounted(() => {
            store.dispatch(`pistas/${Constant.INITIALIZE_PISTA}`);
        });

        const pistas = computed(() => {
            return store.getters['pistas/GetPistas'];
        });

        return { pistas };
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
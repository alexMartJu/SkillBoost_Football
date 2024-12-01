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
import { useRoute } from 'vue-router';
import { usePistas } from '@/composables/client/usePistas.js';
import Constant from '../Constant';
import CardPistas from './CardPistas.vue';

export default {
    components: {
        CardPistas
    },
    setup() {
        const store = useStore();
        const route = useRoute();
        const slug = route.params.slug || undefined;

        console.log(slug ? slug : 'sin slug');

        if (slug) {
            const { pistas, fetchPistas } = usePistas(slug);
            console.log(pistas);

            onMounted(fetchPistas);

            const state = reactive({
                pistas: pistas
            });

            return { state };
        } else {
            store.dispatch(`pistas/${Constant.INITIALIZE_PISTA}`);

            const state = reactive({
                pistas: computed(() => store.getters['pistas/GetPistas'])
            });

            return { state };
        }
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
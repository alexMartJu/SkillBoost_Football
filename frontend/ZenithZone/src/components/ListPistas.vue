<template>
    <div class="container">
        <div class="row g-4 justify-content-center">
            <CardPistas v-for="pista in state.pistas" :key="pista.id" :pista="pista" class="col-md-3" />
        </div>
        <InfiniteLoading @infinite="onInfinite"/>
    </div>
</template>

<script>
import { reactive, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRoute } from 'vue-router';
import { usePistas, usePistasInfinite } from '@/composables/client/usePistas.js';
import Constant from '../Constant';
import CardPistas from './CardPistas.vue';
import InfiniteLoading from "v3-infinite-loading";

export default {
    components: {CardPistas, InfiniteLoading},
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
                pistas: computed(() => store.getters['pistas/GetPistas']),
                pistasInfinite: usePistasInfinite(1, 4),
                offset: 1
            });

            const onInfinite = ($state) => {
                state.offset += 1;
                const newPistas = usePistasInfinite(state.offset, 3);
                newPistas.then(res => {
                    if (res.value.length) {
                        state.pistas.push(...res.value);
                        $state.loaded();
                    } else {
                        $state.complete();
                    }
                }).catch(() => {
                    $state.complete();
                });
            };

            return { state, onInfinite };
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
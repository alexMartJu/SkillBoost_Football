<template>
    <div class="container">
        <div class="row g-4 justify-content-center">
            <CardPistas v-for="pista in state.pistas" :key="pista.id" :pista="pista" class="col-md-4" />
        </div>
        <InfiniteLoading v-if="!slug" @infinite="onInfinite"/>
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

        const state = reactive({
            pistas: [],
            offset: 0,
            busy: false
        });

        const loadInitialPistas = () => {
            state.busy = true;
            usePistasInfinite(state.offset, 6).then(res => {
                if (res.value.length) {
                    state.pistas.push(...res.value);
                    state.offset += 1;
                }
                state.busy = false;
            }).catch(error => {
                console.error(error);
                state.busy = false;
            });
        };

        onMounted(() => {
            if (slug) {
                const { pistas, fetchPistas } = usePistas(slug);
                fetchPistas().then(() => {
                    state.pistas = pistas.value;
                });
            } else {
                console.log(`No slug`);
                store.dispatch(`pistas/${Constant.INITIALIZE_PISTA}`);
                loadInitialPistas();
            }
        });

        const onInfinite = () => {
            if (state.busy) return;
            state.busy = true;
            usePistasInfinite(state.offset, 6).then(res => {
                if (res.value.length) {
                    state.pistas.push(...res.value);
                    state.offset += 1;
                }
                state.busy = false;
            }).catch(() => {
                state.busy = false;
            });
        };

        return { state, onInfinite, slug };
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
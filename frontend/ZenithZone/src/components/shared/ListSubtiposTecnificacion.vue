<template>
    <div class="container">
        <div class="row g-4 justify-content-center">
            <CardSubtiposTecnificacion v-for="subtipo in state.subtiposTecnificacion" :key="subtipo.id"
                :subtipo="subtipo" class="col-md-6" />
        </div>
        <InfiniteLoading v-if="!slug" @infinite="onInfinite" />
    </div>
</template>

<script>
import { reactive, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRoute } from 'vue-router';
import { useSubtiposTecnificacion, useSubtiposTecnificacionInfinite } from '@/composables/client/useSubtiposTecnificacion.js';
import Constant from '../../Constant';
import CardSubtiposTecnificacion from './CardSubtiposTecnificacion.vue';
import InfiniteLoading from "v3-infinite-loading";

export default {
    components: { CardSubtiposTecnificacion, InfiniteLoading },
    setup() {
        const store = useStore();
        const route = useRoute();
        const slug = route.params.slug || undefined;

        const state = reactive({
            subtiposTecnificacion: [],
            offset: 0,
            busy: false
        });

        const loadInitialSubtipos = () => {
            state.busy = true;
            useSubtiposTecnificacionInfinite(state.offset, 6).then(res => {
                if (res.value.length) {
                    state.subtiposTecnificacion.push(...res.value);
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
                const { subtiposTecnificacion, fetchSubtiposTecnificacion } = useSubtiposTecnificacion(slug);
                fetchSubtiposTecnificacion().then(() => {
                    state.subtiposTecnificacion = subtiposTecnificacion.value;
                });
            } else {
                console.log(`No slug`);
                store.dispatch(`subtiposTecnificacion/${Constant.INITIALIZE_SUBTIPO_TECNIFICACION}`);
                loadInitialSubtipos();
            }
        });

        const onInfinite = () => {
            if (state.busy) return;
            state.busy = true;
            useSubtiposTecnificacionInfinite(state.offset, 6).then(res => {
                if (res.value.length) {
                    state.subtiposTecnificacion.push(...res.value);
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
.subtipos-list {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}
</style>
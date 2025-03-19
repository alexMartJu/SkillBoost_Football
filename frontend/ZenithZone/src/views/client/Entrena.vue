<template>
    <div class="container-fluid">
        <div class="row pt-4">
            <!-- Filters Column -->
            <div class="col-lg-3 col-md-4">
                <FiltersComponent :filters_url="state.filters" :meta="state.meta" @apply-filters="ApplyFilters"
                    @reset-filters="resetFilters" />
            </div>

            <!-- Cards Column -->
            <div class="col-lg-9 col-md-8">
                <div class="row g-3">
                    <div v-for="entrenamiento in state.entrenamientos" :key="entrenamiento.id"
                        class="col-xl-4 col-lg-6 col-md-12">
                        <CardClases :entrenamiento="entrenamiento" :isServiciosView="true"
                            :isSuscribed="suscribedEntrenamientos.has(entrenamiento.slug)" :isProfile="false" />
                    </div>
                </div>
                <PaginateComponent :page="state.offset" :totalPages="state.totalPages" @update:page="updatePage"
                    @page-click="clickCallback" />
            </div>
        </div>
    </div>
</template>


<script>
import FiltersComponent from '../../components/filters/Filters.vue';
import CardClases from '../../components/shared/CardEntrenamientos.vue';
import PaginateComponent from '../../components/filters/Paginate.vue';
import {
    useEntrenamientos
} from '../../composables/client/useEntrenamientos';
import { reactive, watchEffect, onMounted } from 'vue';
import { computed } from 'vue';
import { useStore } from 'vuex';
import entrenamientosService from '@/services/client/entrenamientos.service';
import Constant from '../../Constant';

export default {
    components: {
        FiltersComponent,
        CardClases,
        PaginateComponent
    },
    setup() {
        const store = useStore();

        //Cargar tecnificaciones
        onMounted(() => {
            store.dispatch(`tecnificaciones/${Constant.INITIALIZE_TECNIFICACION}`);
        });

        const {
            state,
            ApplyFilters,
            resetFilters,
            clickCallback
        } = useEntrenamientos();

        const currentUser = reactive({
            isUser: computed(() => store.getters['user/GetIsAuth']),
        });

        const suscribedEntrenamientos = reactive(new Set());

        const checkAlreadySuscribed = async () => {
            const { data } = await entrenamientosService.GetSuscribedEntrenamientos();
            suscribedEntrenamientos.clear();
            data.forEach(entrenamiento => suscribedEntrenamientos.add(entrenamiento.slug));
        };

        if (currentUser.isUser) {
            checkAlreadySuscribed();
        }

        const updatePage = (pageNum) => {
            state.offset = pageNum;
        };

        return {
            state,
            ApplyFilters,
            resetFilters,
            clickCallback,
            updatePage,
            suscribedEntrenamientos
        };
    }
};
</script>


<style>
.pagination {
    --bs-pagination-font-size: 20px;
    --bs-pagination-color: black;
    --bs-pagination-border-color: #000000;
    --bs-pagination-active-color: white;
    --bs-pagination-active-bg: #ff6600;
    --bs-pagination-active-border-color: #000000;
    --bs-pagination-hover-color: white;
    --bs-pagination-hover-bg: black;
    --bs-pagination-hover-border-color: black;
    --bs-pagination-disabled-border-color: lightgrey;
    --bs-pagination-focus-box-shadow: none;
}
</style>

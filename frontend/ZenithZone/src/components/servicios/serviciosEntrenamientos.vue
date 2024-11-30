<template>
    <div class="container-fluid">        
        <div class="text-center">
            <filters @filters="ApplyFilters" @deleteFilters="resetFilters" :filters="filters_url" :meta="state.meta" />
        </div>

        <div>
            <div class="row justify-content-center">
                <CardClases 
                    v-for="entrenamiento in state.entrenamientos" 
                    :key="entrenamiento.id" 
                    :entrenamiento="entrenamiento" 
                    class="col-md-6" />
            </div>
        </div>
        
        <div class="d-flex justify-content-center mt-4">
            <paginate 
                v-model="state.page" 
                :page-count="state.totalPages" 
                :page-range="2" 
                :margin-pages="2"
                :click-handler="clickCallback" 
                :prev-text="'Prev'" 
                :next-text="'Next'" 
                :container-class="'pagination'"
                :page-class="'page-item'">
            </paginate>
        </div>

    </div>
</template>


<script>
import Filters from '@/components/filters.vue';
import CardClases from '../CardEntrenamientos.vue';
import { useEntrenamientosData, useEntrenamientosFilters, useEntrenamientosPaginate } from '@/composables/client/useEntrenamientos';
import { reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import Paginate from 'vuejs-paginate-next';

export default {
    components: { Filters, Paginate, CardClases },

    setup() {
        const route = useRoute();
        const router = useRouter();

        let filters_url = {
            nombre: "",
            dia: "",
            duracionMin: 0,
            duracionMax: 0,
            maxPlazasMin: 0,
            maxPlazasMax: 0,
            precioMin: 0,
            precioMax: 0,
            deporteId: "",
            offset: 0,
            limit: 4,
        };

        const state = reactive({
            entrenamientos: useEntrenamientosFilters(filters_url),
            offset: filters_url.offset,
            filters: filters_url,
            totalPages: useEntrenamientosPaginate(filters_url.limit),
            meta: useEntrenamientosData(),
        });

        const ApplyFilters = (filters) => {
            const filters_64 = btoa(JSON.stringify(filters));
            router.push({ name: 'serviciosEntrenamientosFilter', params: { filters: filters_64 } });
            state.entrenamientos = useEntrenamientosFilters(filters);
            state.totalPages = useEntrenamientosPaginate(filters_url.limit);
        }

        const resetFilters = () => {
            state.filters = {
                nombre: "",
                dia: "",
                duracionMin: 0,
                duracionMax: 0,
                maxPlazasMin: 0,
                maxPlazasMax: 0,
                precioMin: 0,
                precioMax: 0,
                deporteId: "",
                offset: 0,
                limit: 4,
            };
            ApplyFilters();
        }

        const clickCallback = (pageNum) => {
            try {
                if (route.params.filters !== '') {
                    filters_url = JSON.parse(atob(route.params.filters));
                }
            } catch (error) {
                console.log(error);
            }
            // console.log(pageNum);
            filters_url.offset = pageNum - 1;
            console.log(`pagenum`, pageNum);
            state.offset = pageNum;
            ApplyFilters(filters_url);
        }

        return { state, filters_url, ApplyFilters, resetFilters, clickCallback };
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
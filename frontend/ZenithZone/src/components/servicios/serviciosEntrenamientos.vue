<template>
<div class="container-fluid ">
    <FiltersComponent :filters_url="state.filters" :meta="state.meta" @apply-filters="ApplyFilters" @reset-filters="resetFilters" />
    <div class="row justify-content-center">
        <CardClases 
            v-for="entrenamiento in state.entrenamientos" 
            :key="entrenamiento.id" 
            :entrenamiento="entrenamiento" 
            class="col-md-6" />
    </div>
    <PaginateComponent :page="state.offset" :totalPages="state.totalPages" @update:page="updatePage" @page-click="clickCallback" />
</div>
</template>


<script>
import FiltersComponent from '../filters/Filters.vue';
import CardClases from '../shared/CardEntrenamientos.vue';
import PaginateComponent from '../filters/Paginate.vue';
import {
    useEntrenamientos
} from '../../composables/client/useEntrenamientos';

export default {
    components: {
        FiltersComponent,
        CardClases,
        PaginateComponent
    },
    setup() {
        const {
            state,
            ApplyFilters,
            resetFilters,
            clickCallback
        } = useEntrenamientos();

        const updatePage = (pageNum) => {
            state.offset = pageNum;
        };

        return {
            state,
            ApplyFilters,
            resetFilters,
            clickCallback,
            updatePage
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

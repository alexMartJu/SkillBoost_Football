<template>
    <main>
        <section>
            <filters @filters="ApplyFilters" @deleteFilters="resetFilters" :filters="filters_url" />
            <ul v-for="entrenamiento in state.entrenamientos">
                <li>Nombre: {{ entrenamiento.nombre }}</li>
                <li>Deporte: {{ entrenamiento.deporte.nombre }}</li>
            </ul>
            <paginate v-model="state.page" :page-count="10" :page-range="3" :margin-pages="2"
                :click-handler="clickCallback" :prev-text="'Prev'" :next-text="'Next'" :container-class="'pagination'"
                :page-class="'page-item'">
            </paginate>
        </section>
    </main>
</template>

<script>
import Filters from '@/components/filters.vue';
import { useEntrenamientosFilters, useEntrenamientosPaginate } from '@/composables/client/useEntrenamientos';
import { reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import Paginate from 'vuejs-paginate-next';


export default {
    components: { Filters, Paginate },

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

        try {
            if (route.params.filters) {
                filters_url = JSON.parse(atob(route.params.filters));
            }
        } catch (error) {
            console.log(error);
        }

        const state = reactive({
            entrenamientos: useEntrenamientosFilters(filters_url),
            offset: filters_url.offset,
            filters: filters_url,
            totalPages: useEntrenamientosPaginate(filters_url),
        });

        const ApplyFilters = (filters) => {
            const filters_64 = btoa(JSON.stringify(filters));
            router.push({ name: 'serviciosFilter', params: { filters: filters_64 } });
            state.entrenamientos = useEntrenamientosFilters(filters);
            state.totalPages = useEntrenamientosPaginate(filters);
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
main {
    margin-top: 150px;
    margin-left: 100px;
}
</style>
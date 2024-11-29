<template>
    <main>
        <section>
            <ul>
                <li v-for="entrenamiento in state.entrenamientos">{{ entrenamiento.nombre }}</li>
            </ul>
        </section>
    </main>
</template>

<script>
import { useEntrenamientosFilters, useEntrenamientosPaginate } from '@/composables/client/useEntrenamientos';
import { reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';

export default {
    components: {
    },

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
            deporteId: 0,
            page: 1,
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
            page: filters_url.page,
            filters: filters_url,
            totalPages: useEntrenamientosPaginate(filters_url),
        });

        const ApplyFilters = () => {
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
                deporteId: 0,
                page: 1,
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
            filters_url.page = pageNum;
            state.page = pageNum;
            ApplyFilters(filters_url);
        }

        return { state, filters_url, ApplyFilters, resetFilters, clickCallback };
    }
};
</script>

<style>
main {
    margin-top: 50px;
}
</style>
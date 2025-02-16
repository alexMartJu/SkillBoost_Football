<template>
    <div class="container-fluid p-3">
        <div class="row g-4">
            <!-- Search Section -->
            <div class="col-12">
                <select v-model="state.filters.deporteId" class="form-select mb-3">
                    <option value="" disabled selected>✨ Elige tu deporte favorito</option>
                    <option v-for="deporte in state.deportes" :key="deporte.id" :value="deporte.id">
                        {{ deporte.nombre }}
                    </option>
                </select>
                <Search :filters="state.filters" @search="sendFilters"/>
            </div>

            <!-- Sliders Section -->
            <div class="col-12">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="mb-4">
                            <label class="form-label d-flex justify-content-between">
                                <span>Precio</span>
                                <span class="text-primary">{{ state.filters.precioMax }}€</span>
                            </label>
                            <input type="range" class="form-range" 
                                :min="minPrice" 
                                :max="maxPrice" 
                                v-model.lazy="state.filters.precioMax"
                                @input="debouncedFilter">
                        </div>

                        <div>
                            <label class="form-label d-flex justify-content-between">
                                <span>Duración</span>
                                <span class="text-primary">{{ state.filters.duracionMax }} min</span>
                            </label>
                            <input type="range" class="form-range"
                                :min="minDuracion" 
                                :max="maxDuracion" 
                                v-model="state.filters.duracionMax">
                        </div>
                    </div>
                </div>
            </div>

            <!-- Days Section -->
            <div class="col-12">
                <h5 class="text-center mb-3">¿Qué días te vienen mejor?</h5>
                <div class="d-flex flex-wrap gap-2">
                    <div v-for="dia in diasDeLaSemana" :key="dia" class="form-check">
                        <input type="checkbox" 
                            :id="dia" 
                            :value="dia" 
                            v-model="state.filters.diasSeleccionados"
                            class="btn-check">
                        <label :for="dia" class="btn btn-outline-primary">
                            {{ dia.substring(0,10) }}
                        </label>
                    </div>
                </div>
            </div>

            <!-- Reset Button -->
            <div class="col-12 text-end">
                <button class="btn btn-outline-primary" @click="deleteFilters()">
                    <i class="bi bi-arrow-clockwise me-2"></i>
                    Reiniciar filtros
                </button>
            </div>
        </div>
    </div>
</template>

<script>
import Constant from '../../Constant';
import { useStore } from 'vuex';
import { reactive, getCurrentInstance, computed, watch } from 'vue';
import Search from './Search.vue';
import { debounce } from 'lodash';

export default {
    components: {
        Search
    },
    props: {
        filters: Object,
        meta: Object
    },
    emits: ['filters', 'deleteFilters'],
    setup(props) {
        const store = useStore();
        const { emit } = getCurrentInstance();

        const diasDeLaSemana = [
            "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
        ];

        store.dispatch(`deportes/${Constant.INITIALIZE_DEPORTE}`);

        const state = reactive({
            deportes: computed(() => store.getters['deportes/GetDeportes']),
            filters: { 
                ...props.filters,
                precioMin: props.meta?.precioMinimo || 0,
                precioMax: props.meta?.precioMaximo || 100,
                duracionMax: props.meta?.duracionMaxima || 100,  // Asignar duracionMaxima como duracionMax
                duracionMin: props.meta?.duracionMinima || 0,  // Asignar duracionMinima como duracionMin
                diasSeleccionados: []
            },
            meta: computed(() => props.meta)
        });

        // Reactividad para sincronizar meta y filtros
        watch(
            () => props.meta,
            (newMeta) => {
                if (newMeta) {
                    state.filters.precioMin = newMeta.precioMinimo;
                    state.filters.precioMax = newMeta.precioMaximo;
                    state.filters.duracionMin = newMeta.duracionMinima; // Cambiar duracionMaxima a duracionMinima
                    state.filters.duracionMax = newMeta.duracionMaxima; // Cambiar duracionMinima a duracionMaxima
                }
            },
            { immediate: true }
        );

        // Computados para minPrice, maxPrice, minDuracion y maxDuracion
        const minPrice = computed(() => state.meta.precioMinimo || 0);
        const maxPrice = computed(() => state.meta.precioMaximo || 100);
        const minDuracion = computed(() => state.meta.duracionMinima || 0); // Duración mínima
        const maxDuracion = computed(() => state.meta.duracionMaxima || 100); // Duración máxima

        const debouncedFilter = debounce((event) => {
            state.filters[event.target.name] = event.target.value;
            sendFilters();
        }, 300);

        watch(
            () => state.filters,
            (newFilters) => {
                emit('filters', newFilters);
            },
            { deep: true }
        );

        const sendFilters = () => {
            emit('filters', state.filters);
        };

        const deleteFilters = () => {
            state.filters.nombre = "";
            state.filters.dia = "";
            state.filters.duracionMin = minDuracion.value;
            state.filters.duracionMax = maxDuracion.value;
            state.filters.maxPlazasMin = 0;
            state.filters.maxPlazasMax = 0;
            state.filters.precioMin = minPrice.value;
            state.filters.precioMax = maxPrice.value;
            state.filters.deporteId = "";
            state.filters.offset = 0;
            state.filters.limit = 3;
            state.filters.diasSeleccionados = [];
            emit('deleteFilters', state.filters);
        };

        const updateSearch = (search) => {
            state.filters.nombre = search;
        };

        return { state, sendFilters, deleteFilters, updateSearch, diasDeLaSemana, minPrice, maxPrice, minDuracion, maxDuracion };
    }
};
</script>

<style scoped>

</style>

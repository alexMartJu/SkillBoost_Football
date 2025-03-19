<template>
    <div class="container-fluid p-3">
        <div class="row g-4">
            <!-- Search Section -->
            <div class="col-12">
                <select v-model="state.filters.tecnificacionNombre" class="form-select mb-3">
                    <option value="" disabled selected>✨ Elige tu tipo de tecnificación</option>
                    <option v-for="tecnificacion in state.tecnificaciones" :key="tecnificacion.id" :value="tecnificacion.nombre">
                        {{ tecnificacion.nombre }}
                    </option>
                </select>
                <Search :filters="state.filters" @search="sendFilters"/>
            </div>

            <!-- Nivel Section -->
            <div class="col-12">
                <select v-model="state.filters.nivel" class="form-select mb-3">
                    <option value="" disabled selected>🏆 Selecciona tu nivel</option>
                    <option value="principiante">Principiante</option>
                    <option value="intermedio">Intermedio</option>
                    <option value="avanzado">Avanzado</option>
                </select>
            </div>

            <!-- Sliders Section -->
            <div class="col-12">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="mb-4">
                            <label class="form-label d-flex justify-content-between">
                                <span>Edad Mínima</span>
                                <span class="text-primary">{{ state.filters.edadMinima || 0 }} años</span>
                            </label>
                            <input type="range" class="form-range" 
                                min="1" 
                                max="99" 
                                v-model.number="state.filters.edadMinima"
                                @input="debouncedFilter">
                        </div>

                        <div class="mb-4">
                            <label class="form-label d-flex justify-content-between">
                                <span>Edad Máxima</span>
                                <span class="text-primary">{{ state.filters.edadMaxima || 0 }} años</span>
                            </label>
                            <input type="range" class="form-range" 
                                min="1" 
                                max="99" 
                                v-model.number="state.filters.edadMaxima"
                                @input="debouncedFilter">
                        </div>

                        <div>
                            <label class="form-label d-flex justify-content-between">
                                <span>Plazas Máximas</span>
                                <span class="text-primary">{{ state.filters.maxPlazasMax || 1 }}</span>
                            </label>
                            <input type="range" class="form-range"
                                :min="minPlazas" 
                                :max="maxPlazas" 
                                v-model.number="state.filters.maxPlazasMax">
                        </div>
                    </div>
                </div>
            </div>

            <!-- Date Range Section -->
            <div class="col-12">
                <div class="mb-3">
                    <label class="form-label">Fecha Inicio</label>
                    <input type="datetime-local" class="form-control" v-model="state.filters.fechaInicio">
                </div>
                <div class="mb-3">
                    <label class="form-label">Fecha Fin</label>
                    <input type="datetime-local" class="form-control" v-model="state.filters.fechaFin">
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

        store.dispatch(`tecnificaciones/${Constant.INITIALIZE_TECNIFICACION}`);

        const state = reactive({
            tecnificaciones: computed(() => store.getters['tecnificaciones/GetTecnificaciones']),
            filters: { 
                ...props.filters,
                edadMinima: props.filters.edadMinima || 1,
                edadMaxima: props.filters.edadMaxima || 99,
                maxPlazasMin: props.filters.maxPlazasMin || 1,
                maxPlazasMax: props.filters.maxPlazasMax || 50,
                nivel: props.filters.nivel || "",
                tecnificacionNombre: props.filters.tecnificacionNombre || "",
                fechaInicio: props.filters.fechaInicio || null,
                fechaFin: props.filters.fechaFin || null
            },
            meta: computed(() => props.meta)
        });

        // Computados para los límites de los sliders
        const minPlazas = computed(() => state.meta?.plazasMinimas || 1);
        const maxPlazas = computed(() => state.meta?.plazasMaximas || 50);

        // Reactividad para sincronizar meta y filtros
        watch(
            () => props.meta,
            (newMeta) => {
                if (newMeta) {
                    // Actualizar con los valores del meta
                    if (newMeta.plazasMinimas !== undefined) {
                        state.filters.maxPlazasMin = newMeta.plazasMinimas;
                    }
                    if (newMeta.plazasMaximas !== undefined) {
                        state.filters.maxPlazasMax = newMeta.plazasMaximas;
                    }
                }
            },
            { immediate: true }
        );

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
            state.filters.nivel = "";
            state.filters.edadMinima = 1;
            state.filters.edadMaxima = 99;
            state.filters.maxPlazasMin = state.meta?.plazasMinimas || 1;
            state.filters.maxPlazasMax = state.meta?.plazasMaximas || 50;
            state.filters.tecnificacionNombre = "";
            state.filters.fechaInicio = null;
            state.filters.fechaFin = null;
            state.filters.offset = 0;
            state.filters.limit = 3;
            emit('deleteFilters', state.filters);
        };

        const updateSearch = (search) => {
            state.filters.nombre = search;
        };

        return { 
            state, 
            sendFilters, 
            deleteFilters, 
            updateSearch, 
            debouncedFilter,
            minPlazas,
            maxPlazas
        };
    }
};
</script>

<style scoped>

</style>

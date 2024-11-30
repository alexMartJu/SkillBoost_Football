<template>
    <div class="container">
        <!-- <h1>{{ state.meta }}</h1> -->
        <!-- Fila principal -->
        <div class="row align-items-center">
            <!-- Select para Deporte -->
            <div class="col-3">
                <select class="form-select" v-model="state.filters.deporteId">
                    <option value="" disabled selected>Selecciona un deporte</option>
                    <option v-for="deporte in state.deportes" :key="deporte.id" :value="deporte.id">
                        {{ deporte.nombre }}
                    </option>
                </select>
            </div>

            <!-- Select para Día -->
            <div class="col-3">
                <select class="form-select" v-model="state.filters.dia">
                    <option value="" disabled selected>Selecciona un día</option>
                    <option v-for="(dia, index) in diasDeLaSemana" :key="index" :value="dia">
                        {{ dia }}
                    </option>
                </select>
            </div>

            <!-- Rango de Precios -->
            <div class="col-2">
                <label class="mb-2">Precio máximo</label>
                <div class="d-flex flex-column">
                    <div class="d-flex row align-items-center">
                        <input 
                            type="range" 
                            class="form-range me-3" 
                            :min="minPrice" 
                            :max="maxPrice" 
                            step="5"
                            v-model="state.filters.precioMax" 
                        />
                        <span>{{ state.filters.precioMax }}€</span>
                    </div>
                </div>
            </div>

            <!-- Rango de Duración -->
            <div class="col-2">
                <label class="mb-2">Duración máxima</label>
                <div class="d-flex flex-column">
                    <div class="d-flex row align-items-center">
                        <input 
                            type="range" 
                            class="form-range me-3" 
                            :min="minDuracion" 
                            :max="maxDuracion" 
                            step="5"
                            v-model="state.filters.duracionMax" 
                        />
                        <span>{{ state.filters.duracionMax }} minutos</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Botones -->
        <div class="container_filter mt-3 text-center">
            <button class="" @click="sendFilters()">Filter</button>
            <button class="" @click="deleteFilters()">Clear</button>
        </div>
    </div>
</template>

<script>
import Constant from '../Constant';
import { useStore } from 'vuex';
import { reactive, getCurrentInstance, computed, watch } from 'vue';

export default {
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
            state.filters.limit = 4;
            emit('deleteFilters', state.filters);
        };

        const updateSearch = (search) => {
            state.filters.nombre = search;
        };

        return { state, sendFilters, deleteFilters, updateSearch, diasDeLaSemana, minPrice, maxPrice, minDuracion, maxDuracion };
    }
};
</script>

<style>
select {
    padding: 10px;
    margin: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
}

input[type="range"] {
    width: 100%;
    margin: 5px 0;
}

button {
    padding: 10px;
    margin: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}
</style>

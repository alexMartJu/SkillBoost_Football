<template>
    <div>
        <select v-model="state.filters.deporteId">
            <option value="" disabled selected>Selecciona un deporte</option>
            <option v-for="deporte in state.deportes" :value="deporte.id">{{ deporte.nombre }}</option>
        </select>

        <select v-model="state.filters.dia">
            <option value="" disabled selected>Selecciona un día</option>
            <option v-for="(dia, index) in diasDeLaSemana" :key="index" :value="dia">{{ dia }}</option>
        </select>

    </div>

    <div class="container_filter_search">
        <!-- <searchVue :search="state.filters.nombre" @emitSearch="updateSearch" /> -->
    </div>
    <div class="container_filter">
        <!-- <input type="number" min="0" v-model="state.filters.precioMax" /> -->
        <button class="raise" @click="sendFilters()">Filter</button>
        <button class="raise" @click="deleteFilters()">Clear</button>
    </div>
</template>

<script>
import Constant from '../Constant';
import { useStore } from 'vuex'
import { reactive, getCurrentInstance, computed } from 'vue';
// import searchVue from './search.vue';
export default {
    // components: { searchVue },
    props: {
        filters: Object
    },
    emits: {
        filters: Object,
        deleteFilters: Object
    },
    setup(props) {
        const store = useStore();
        const { emit } = getCurrentInstance();

        const diasDeLaSemana = [
            "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
        ];


        // store.dispatch(`category/${Constant.INITIALIZE_CATEGORY}`);
        store.dispatch(`deportes/${Constant.INITIALIZE_DEPORTE}`);

        const state = reactive({
            // categories: computed(() => store.getters['category/GetCategories']?.map(item => item.name_category)),
            deportes: computed(() => store.getters['deportes/GetDeportes']),
            filters: { ...props.filters }
        });

        const sendFilters = () => {
            emit('filters', state.filters);
        }//sendFilters

        const deleteFilters = () => {
            state.filters.nombre = "";
            state.filters.dia = "";
            state.filters.duracionMin = 0;
            state.filters.duracionMax = 0;
            state.filters.maxPlazasMin = 0;
            state.filters.maxPlazasMax = 0;
            state.filters.precioMin = 0;
            state.filters.precioMax = 0;
            state.filters.deporteId = "";
            state.filters.offset = 0;
            state.filters.limit = 4;            
            emit('deleteFilters', state.filters);
        }//sendFilters

        const updateSearch = (search) => {
            state.filters.nombre = search;
        }

        return { state, sendFilters, deleteFilters, updateSearch, diasDeLaSemana }
    }
}
</script>

<style>
    select {
        padding: 10px;
        margin: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
    }

    input {
        padding: 10px;
        margin: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
    }

    button {
        padding: 10px;
        margin: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
        background-color: #f1f1f1;
    }
</style>
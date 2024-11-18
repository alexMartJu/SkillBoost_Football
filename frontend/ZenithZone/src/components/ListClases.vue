<template>
    <div class="container">
        <div class="row g-4 justify-content-center">
            <CardClases v-for="clase in state.clases" :key="clase.id" :clase="clase" class="col-md-3" />
        </div>
    </div>
</template>

<script>
import { reactive, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import Constant from '../Constant';
import CardClases from './CardClases.vue';

export default {
    components: {
        CardClases
    },
    setup() {
        const store = useStore();

        store.dispatch(`clases/${Constant.INITIALIZE_CLASE}`);

        const state = reactive({
            clases: computed(() => store.getters['clases/GetClases'])
        });

        return { state };
    }
};
</script>

<style>
.deportes-list {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}
</style>
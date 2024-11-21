<template>
    <main class="body-deportes-home">
        <div class="test">
            <div class="test-2">
                <p class="text-center text-white text-deportes fst-italic mb-0">LOS DEPORTES DE LA ÉLITE</p>
            </div>  
        </div>        
        <div class="row justify-content-center">
            <CardDeportes v-for="deporte in state.deportes.slice(0,8)" :key="deporte.id" :deporte="deporte" class="col-md-3" />
        </div>
    </main>

</template>

<script>
import { reactive, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import CardDeportes from './CardDeportesHome.vue';

export default {
    components: {
        CardDeportes
    },
    setup() {
        const store = useStore();

        store.dispatch(`deportes/${Constant.INITIALIZE_DEPORTE}`);

        const state = reactive({
            deportes: computed(() => store.getters['deportes/GetDeportes'])
        });

        return { state };
    }
};
</script>

<style>
.body-deportes-home{
    background-color: rgb(20, 20, 20);
}

.test {
    /* box-shadow: inset 20px 20px 0 15px #ff6600; */
    box-shadow: inset -20px -20px 0 15px rgba(0, 255, 229, 0.9);
}

.test-2 {
    /* box-shadow: inset -20px -20px 0 15px rgba(0, 255, 229, 0.9); */
    box-shadow: inset 20px 20px 0 15px #ff6600;
}

.text-deportes{
    padding: 70px;
    font-size: 60px;
    font-family: 'Roboto', sans-serif;
    text-shadow: 5px 5px 1px #ff6600;
}

.deportes-list {
    display: flex;
    flex-wrap: wrap;
}

.col-md-3 {
    margin: 0px;
    padding: 0;
}

.row{
    margin: 0;
    padding: 0;
}
</style>
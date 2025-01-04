<template>
    <div v-if="isEntrenamiento">
        <div class="entrenamiento-info" v-if="state.entrenamiento">
            <h1>{{ state.entrenamiento.nombre }}</h1>
            <h3>{{ state.entrenamiento.deporte.nombre }}</h3>
            <p>{{ state.entrenamiento.descripcion }}</p>
            <p>{{ state.entrenamiento.dia }}</p>
            <p>{{ state.entrenamiento.duracion }} minutos</p>
            <p>Plazas máximas: {{ state.entrenamiento.maxPlazas }}</p>
            <p>Precio: {{ state.entrenamiento.precio }}€</p>
            <p>Horario: {{ state.entrenamiento.horario.hora }}</p>
            <h3>Entrenador:</h3>
            <p>Nombre: {{ state.entrenamiento.entrenador.nombre }} {{ state.entrenamiento.entrenador.apellidos }}</p>
            <div>
                <h3>Lugar: {{ state.entrenamiento.pistaPrivada.nombre }}</h3>
            </div>
            <UnirseEntrenamientoButton :slug="state.entrenamiento.slug" :disabled="isSubscribed" />
        </div>
        <div v-else>
            <p>Cargando información de entrenamiento...</p>
        </div>
    </div>
    <!-- SI ES DETAILS PISTA -->
    <div v-else>
        <div class="pista-info" v-if="state.pista">
            <h1>{{ state.pista.nombre }}</h1>
            <!-- <h2>{{ state.pista.slug }}</h2> -->
            <div class="form-group col-md-6 mt-3">
                <label for="datepicker">Selecciona una fecha:</label>
                <input type="date" id="datepicker" v-model="selectedDate" class="form-control" />
                <div v-if="selectedDate" class="form-group mt-3">
                    <label for="horarios">Selecciona un horario:</label>
                    <select name="horarios" id="horarios" class="form-control" v-model="selectedHorario">
                        <option value="mañana" selected>Mañana</option>
                        <option value="mediodia">Mediodia</option>
                        <option value="tarde">Tarde</option>
                        <option value="noche">Noche</option>
                    </select>
                </div>
                <button @click="logDate" class="btn btn-primary mt-2">Reservas pista</button>
            </div>
        </div>
        <div v-else>
            <p>Cargando información de la pista...</p>
        </div>
    </div>
</template>

<script>
import { reactive } from 'vue';
import UnirseEntrenamientoButton from './buttons/UnirseEntrenamientoButton.vue';
import { computed } from 'vue';
import entrenamientosService from '@/services/client/entrenamientos.service';
import { useStore } from 'vuex';
import { ref } from 'vue';
import reservasService from '@/services/client/reservas.service';

export default {
    props: {
        isEntrenamiento: {
            type: Boolean,
            Required: true
        },
        state: {
            type: Object,
            Required: true
        }
    },

    components: {
        UnirseEntrenamientoButton
    },

    setup(props) {
        const store = useStore();

        // APUNTARSE ENTRENAMIENTOS
        const currentUser = reactive({
            isUser: computed(() => store.getters['user/GetIsAuth']),
        });

        const suscribedEntrenamientos = reactive(new Set());

        const checkAlreadySuscribed = async () => {
            const { data } = await entrenamientosService.GetSuscribedEntrenamientos();
            suscribedEntrenamientos.clear();
            data.forEach(entrenamiento => suscribedEntrenamientos.add(entrenamiento.slug));
        };

        if (currentUser.isUser) {
            checkAlreadySuscribed();
        }

        const isSubscribed = computed(() => {
            return props.state.entrenamiento.slug && suscribedEntrenamientos.has(props.state.entrenamiento.slug);
        });


        // RESERVA PISTAS
        const selectedDate = ref('');
        const selectedHorario = ref('');

        const logDate = async () => {
            if (selectedDate.value) {
                const data = {
                    slugPista: props.state.pista.slug,
                    hora: selectedHorario.value,
                    fecha: selectedDate.value
                };

                console.log(data);
                await reservasService.CreateReserva(data);
            } else {
                console.warn('No se ha seleccionado ninguna fecha');
            }
        };

        return { suscribedEntrenamientos, isSubscribed, checkAlreadySuscribed, logDate, selectedDate, selectedHorario };
    }


}
</script>

<style>
body {
    margin-top: 80px;
    background-color: rgb(245, 245, 245);
    font-family: 'Roboto', sans-serif;
}
</style>
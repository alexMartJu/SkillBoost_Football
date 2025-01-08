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
                <input type="date" id="datepicker" v-model="selectedDate" class="form-control" :min="minDate" />
                <div v-if="selectedDate" class="form-group mt-3">
                    <label for="horarios">Selecciona un horario:</label>
                    <select name="horarios" id="horarios" class="form-control" v-model="selectedHorario">
                        <option v-for="horario in horarios" :key="horario" :value="horario"
                            :disabled="isHorarioReserved(horario)">
                            {{ horario.charAt(0).toUpperCase() + horario.slice(1) }}
                        </option>
                    </select>
                </div>

                <button @click="logDate" :disabled="isReserved" class="btn btn-primary mt-2">
                    {{ isReserved ? 'Horario reservado' : 'Reservar pista' }}
                </button>
                <div v-if="reservaSuccess" class="alert alert-success mt-3" role="alert">
                    Reserva realizada con éxito.
                </div>
                <div v-if="reservaError" class="alert alert-danger mt-3" role="alert">
                    Error al realizar la reserva. Inténtalo de nuevo.
                </div>
            </div>
        </div>
        <div v-else>
            <p>Cargando información de la pista...</p>
        </div>
    </div>
</template>

<script>
import { reactive, watchEffect } from 'vue';
import UnirseEntrenamientoButton from './buttons/UnirseEntrenamientoButton.vue';
import { computed } from 'vue';
import entrenamientosService from '@/services/client/entrenamientos.service';
import { useStore } from 'vuex';
import { ref } from 'vue';
import reservasService from '@/services/client/reservas.service';
import { useRouter } from 'vue-router';

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
        // const router = useRouter();
        const currentUser = reactive({
            isUser: computed(() => store.getters['user/GetIsAuth']),
        });
        const horarios = ['mañana', 'mediodia', 'tarde', 'noche'];

        // APUNTARSE ENTRENAMIENTOS
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
        const reservaSuccess = ref(false);
        const reservaError = ref(false);

        const logDate = async () => {
            reservaSuccess.value = false;
            reservaError.value = false;
            if (selectedDate.value) {
                const data = {
                    slugPista: props.state.pista.slug,
                    hora: selectedHorario.value,
                    fecha: selectedDate.value
                };

                try {
                    await reservasService.CreateReserva(data);
                    reservaSuccess.value = true;
                } catch (error) {
                    reservaError.value = true;
                }
            } else {
                console.warn('No se ha seleccionado ninguna fecha');
            }
        };

        const suscribedReservas = reactive(new Set());

        const checkAlreadyReserved = async () => {
            const { data } = await reservasService.GetReservas();
            console.log(data);
            suscribedReservas.clear();
            data.forEach(reserva => suscribedReservas.add(`${reserva.slug}-${reserva.fecha}-${reserva.hora}`));
            console.log(suscribedReservas);
        };

        if (currentUser.isUser) {
            checkAlreadyReserved();
        }

        const isReserved = computed(() => {
            if (currentUser.isUser) return false;
            return props.state.pista.slug && suscribedReservas.has(`${props.state.pista.slug}-${selectedDate.value}-${selectedHorario.value}`);
        });

        const isHorarioReserved = (horario) => {
            if (!props.state.pista.slug || !selectedDate.value) return false;
            return suscribedReservas.has(`${props.state.pista.slug}-${selectedDate.value}-${horario}`);
        };

        watchEffect(() => { });

        const minDate = computed(() => {
            const today = new Date();
            const year = today.getFullYear();
            const month = String(today.getMonth() + 1).padStart(2, '0'); // Mes en formato MM
            const day = String(today.getDate()).padStart(2, '0'); // Día en formato DD
            return `${year}-${month}-${day}`;
        });

        return {
            horarios, suscribedEntrenamientos, isSubscribed, isReserved, isHorarioReserved, checkAlreadySuscribed,
            checkAlreadyReserved, logDate, selectedDate, selectedHorario, reservaSuccess, reservaError, currentUser,
            minDate
        };
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
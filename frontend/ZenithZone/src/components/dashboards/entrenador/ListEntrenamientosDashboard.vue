<template>
  <div class="list-entrenamientos">
    <h2>Lista de Entrenamientos</h2>
    <!-- Mostrar las tarjetas de los entrenamientos filtrados -->
    <div v-for="entrenamiento in filteredEntrenamientos" :key="entrenamiento.id" class="entrenamiento-card">
      <CardEntrenamientosDashboard :entrenamiento="entrenamiento" />

      <!-- Botón para alternar detalles de alumnos -->
      <button @click="toggleDetails(entrenamiento.id)" class="details-button">
        {{ showDetails[entrenamiento.id] ? 'Ocultar Alumnos' : 'Ver Alumnos' }}
      </button>

      <!-- Lista de alumnos (se muestra al abrir detalles) -->
      <div v-if="showDetails[entrenamiento.id]" class="alumnos-list">
        <h3>Lista de Alumnos</h3>
        <ul>
          <li v-for="alumno in entrenamiento.profiles" :key="alumno.id" class="alumno-item">
            <img
              :src="getProfileImageUrl(alumno.image)"
              alt="Imagen del alumno"
              class="alumno-image"
            />
            <span>{{ alumno.nombre }} {{ alumno.apellidos }} ({{ alumno.edad }} años)</span>
            <button @click="viewAlumno(alumno.id)" class="view-alumno-button">Ver Gráfica</button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import Constant from '../../../Constant';
import CardEntrenamientosDashboard from './CardEntrenamientosDashboard.vue';

export default {
  components: {
    CardEntrenamientosDashboard,
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const entrenadorLogueado = 'Luis Fernández'; // Cambiar según el entrenador logueado

    // Inicializar datos en Vuex
    store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_ENTRENAMIENTO}`);

    // Estado reactivo
    const state = reactive({
      entrenamientos: computed(() => store.getters['entrenadorDashboard/GetEntrenamientos']),
    });

    const showDetails = reactive({}); // Control de detalles visibles

    // Filtrar entrenamientos por el entrenador logueado
    const filteredEntrenamientos = computed(() =>
      state.entrenamientos.filter(
        (entrenamiento) =>
          `${entrenamiento.entrenador.nombre} ${entrenamiento.entrenador.apellidos}` ===
          entrenadorLogueado
      )
    );

    // Alternar detalles de alumnos
    const toggleDetails = (entrenamientoId) => {
      showDetails[entrenamientoId] = !showDetails[entrenamientoId];
    };

    // Obtener la URL de la imagen de perfil
    const getProfileImageUrl = (image) => {
      return image ? `/assets/profile/${image}` : '/assets/profile/default.png';
    };

    // Navegar al detalle de un alumno
    const viewAlumno = (ProfileId) => {
      router.push({ name: 'AlumnoDetail', params: { profileId: ProfileId } });
    };

    return {
      state,
      filteredEntrenamientos,
      toggleDetails,
      showDetails,
      getProfileImageUrl,
      viewAlumno,
    };
  },
};
</script>

<style scoped>
.list-entrenamientos {
  margin-top: 20px;
}

.entrenamiento-card {
  border: 1px solid #ddd;
  padding: 15px;
  margin-bottom: 10px;
  border-radius: 5px;
}

.details-button {
  margin-top: 10px;
  padding: 5px 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.details-button:hover {
  background-color: #0056b3;
}

.alumnos-list {
  margin-top: 10px;
  padding: 10px;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.alumno-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.alumno-image {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-right: 10px;
}

.view-alumno-button {
  margin-left: auto;
  padding: 5px 10px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.view-alumno-button:hover {
  background-color: #218838;
}
</style>

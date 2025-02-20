<template>
  <div class="container py-5">
    <h2 class="display-6 mb-4 text-primary">Crear Entrenamiento</h2>

    <!-- Pistas Privadas Selection -->
    <div class="card shadow-sm mb-5">
      <div class="card-body">
        <h3 class="h4 mb-4">Selecciona una Pista Privada</h3>
        <div class="d-flex flex-wrap gap-2">
          <button
            v-for="pista in pistasPrivadasFiltradas"
            :key="pista.pistas_id"
            class="btn"
            :class="[selectedPista === pista.pistas_id ? 'btn-primary' : 'btn-outline-primary']"
            @click="selectPista(pista)"
          >
            {{ pista.nombre }}
          </button>
        </div>
      </div>
    </div>

    <!-- Horarios Table -->
    <div v-if="selectedPista" class="card shadow-sm mb-5">
      <div class="card-body">
        <h3 class="h4 mb-4">Selecciona un Horario</h3>
        <div class="table-responsive">
          <table class="table table-hover">
            <thead class="table-light">
              <tr>
                <th scope="col">Horario/Día</th>
                <th v-for="(dia, index) in diasSemana" :key="index" scope="col">{{ dia }}</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="horario in horariosBase" :key="horario.id">
                <td class="fw-bold">{{ horario.hora }}</td>
                <td
                  v-for="(dia, index) in diasSemana"
                  :key="index"
                  :class="{ 'bg-light': isHorarioOcupado(dia, horario.id) }"
                >
                  <button
                    class="btn btn-sm w-100"
                    :class="[isHorarioOcupado(dia, horario.id) ? 'btn-secondary' : 'btn-outline-success']"
                    @click="selectHorario(dia, horario)"
                    :disabled="isHorarioOcupado(dia, horario.id)"
                  >
                    {{ isHorarioOcupado(dia, horario.id) ? 'Ocupado' : 'Disponible' }}
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <!-- Resumen de selección -->
    <div v-if="selectedHorario" class="card shadow-sm mb-4">
      <div class="card-body">
        <h4 class="h5 mb-3">Horario Seleccionado</h4>
        <div class="d-flex gap-3 align-items-center">
          <span class="badge bg-primary">
            <i class="bi bi-calendar-date me-1"></i>
            {{ selectedHorario.dia }}
          </span>
          <span class="badge bg-primary">
            <i class="bi bi-clock me-1"></i>
            {{ horariosBase.find(h => h.id === selectedHorario.horario)?.hora }}
          </span>
          
        </div>
      </div>
    </div>

    <!-- Create Form -->
    <div v-if="selectedHorario" class="card shadow-sm">
      <div class="card-body">
        <h3 class="h4 mb-4">Detalles del Entrenamiento</h3>
        <form @submit.prevent="createEntrenamiento" class="row g-4">
          <div class="col-md-6">
            <label class="form-label">Nombre</label>
            <input type="text" class="form-control" v-model="nuevoEntrenamiento.nombre" required />
          </div>
          <div class="col-12">
            <label class="form-label">Descripción</label>
            <textarea class="form-control" rows="3" v-model="nuevoEntrenamiento.descripcion" required></textarea>
          </div>
          <div class="col-md-4">
            <label class="form-label">Duración (min)</label>
            <input type="number" class="form-control" v-model="nuevoEntrenamiento.duracion" min="30" max="120" required />
          </div>
          <div class="col-md-4">
            <label class="form-label">Plazas</label>
            <input type="number" class="form-control" v-model="nuevoEntrenamiento.plazas" required />
          </div>
          <div class="col-md-4">
            <label class="form-label">Precio €</label>
            <input type="number" class="form-control" v-model="nuevoEntrenamiento.precio" required />
          </div>
          <div class="col-12">
            <button type="submit" class="btn btn-primary px-5">Crear Entrenamiento</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
<script>
import { ref ,computed, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import Constant from '../../../Constant'; // Importamos las constantes

export default {
  setup() {
    const store = useStore();

    // Estado reactivo para almacenar todas las pistas privadas
    const pistasPrivadas = computed(() => store.state.entrenadorDashboard.pistasPrivadas);
    
    // Estado reactivo para las pistas filtradas por deporte
    const pistasPrivadasFiltradas = computed(() => {
      const entrenador = store.state.entrenadorDashboard.entrenador;
      const deporteId = entrenador?.usuario?.deporte_id;
      console.log('Deporte ID del usuario:', deporteId);
      console.log('Pistas privadas:', pistasPrivadas.value);
      if (Array.isArray(pistasPrivadas.value)) {
        return pistasPrivadas.value.filter(pistaPrivada =>
          pistaPrivada.deportes.some(deporte => deporte.id === deporteId)
        );
      } else {
        console.error("pistasPrivadas no es un array:", pistasPrivadas.value);
        return []; // Retorna un array vacío en caso de que no sea un array
      }
    });

    // Estado reactivo para los horarios y el entrenamiento
    const selectedPista = ref(null);
    const horariosBase = ref([
      { id: 1, hora: "Mañana" },
      { id: 2, hora: "Mediodía" },
      { id: 3, hora: "Tarde" },
      { id: 4, hora: "Noche" },
    ]);
    const diasSemana = ref(["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"]);

    const horariosOcupados = reactive([]);
    const selectedHorario = ref(null);
    const nuevoEntrenamiento = reactive({
      nombre: "",
      descripcion: "",
      duracion: 60,
      max_plazas: 20,
      precio: 15,
      deporte_id: store.state.entrenadorDashboard.entrenador?.usuario?.deporte_id || null,
      horario_id: null,
      pista_privada_id: null,
      dia: null,
    });

    // Cargar las pistas privadas cuando el componente se monte
    onMounted(() => {
      store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_PISTA_PRIVADA}`);
   
    });

    // Cargar los horarios disponibles para la pista privada seleccionada
    const loadHorarios = async () => {
      if (!selectedPista.value) return;
      console.log("selectedpista"+selectedPista.value);
      await store.dispatch(`entrenadorDashboard/${Constant.FETCH_ENTRENAMIENTOS_OCUPADOS}`, selectedPista.value);
      const ocupados = store.state.entrenadorDashboard.horariosOcupados;
      console.log("ocupados?"+ocupados);
      horariosOcupados.splice(0, horariosOcupados.length, ...ocupados);
    };


    // Verificar si el horario está ocupado

    const isHorarioOcupado = (dia, horarioId) => {
      return horariosOcupados.some(ocupado => ocupado.dia === dia && ocupado.horario_id === horarioId);
    };


    // Seleccionar una pista privada
    const selectPista = (pista) => {
      // Resetear formulario y visibilidad al seleccionar una pista diferente
      resetFormulario();
      resetFormularioVisibilidad();

      selectedPista.value = pista.pistas_id;
      nuevoEntrenamiento.pista_privada_id = pista.pistas_id;
      loadHorarios(); 
    };

    // Seleccionar un horario
    const selectHorario = (dia, horario) => {
      if (isHorarioOcupado(dia, horario.id)) return; // Evitar seleccionar horarios ocupados
      selectedHorario.value = { dia, horario: horario.id }; // Asigna un objeto con dia y horario.id
      nuevoEntrenamiento.dia = dia;
      nuevoEntrenamiento.horario_id = horario.id;
    };
    // Función para crear un nuevo entrenamiento
    const createEntrenamiento = async () => {
      try {
        await store.dispatch(`entrenadorDashboard/${Constant.CREATE_ONE_ENTRENAMIENTO}`, nuevoEntrenamiento);
        alert("Entrenamiento creado exitosamente");
        resetFormulario();
       resetFormularioVisibilidad();
      } catch (error) {
        console.error("Error al crear entrenamiento:", error);
        alert("Hubo un problema al crear el entrenamiento.");
      }
    };
    const resetFormulario = () => {
  // Restablecer el objeto nuevoEntrenamiento a su valor inicial
  nuevoEntrenamiento.nombre = "";
  nuevoEntrenamiento.descripcion = "";
  nuevoEntrenamiento.duracion = 60;
  nuevoEntrenamiento.max_plazas = 20;
  nuevoEntrenamiento.precio = 15;
  nuevoEntrenamiento.deporte_id = store.state.entrenadorDashboard.entrenador.usuario.deporte_id;
  nuevoEntrenamiento.horario_id = null;
  nuevoEntrenamiento.pista_privada_id = null;
  nuevoEntrenamiento.dia = null;

  // Restablecer las selecciones
  selectedPista.value = null;
  selectedHorario.value = null;
};

// Función para resetear la visibilidad de las secciones del formulario
const resetFormularioVisibilidad = () => {
  // Ocultar las secciones del formulario que dependen de las selecciones
  selectedPista.value = null; // Pista seleccionada vuelve a estar vacía
  selectedHorario.value = null; // Horario seleccionado vuelve a estar vacío
  nuevoEntrenamiento.horario_id = null;
  nuevoEntrenamiento.dia = null;
}
    return {
      pistasPrivadasFiltradas,
      selectPista,
      selectedPista,
      horariosBase,
      diasSemana,
      isHorarioOcupado,
      selectHorario,
      selectedHorario,
      createEntrenamiento,
      nuevoEntrenamiento,
    };
  }
};
</script>

<style scoped>
.btn-outline-primary:hover {
  transform: translateY(-1px);
  transition: transform 0.2s;
}

.card {
  border: none;
  border-radius: 12px;
}

.table th {
  font-weight: 600;
}

.form-control:focus {
  box-shadow: none;
  border-color: #0d6efd;
}
</style>
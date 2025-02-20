<template>
  <div class="container py-5">
    <h2 class="display-6 mb-4 text-primary">Entrenamientos</h2>
    
    <!-- Pending Entrenamientos -->
    <div class="mb-5">
      <h3 class="h4 text-warning">Pendientes de Aprobación</h3>
      <div class="row g-4">
        <div class="col-md-6 col-lg-4" v-for="entrenamiento in getEntranamientosByStatus('pending')" :key="entrenamiento.id">
          <div class="card h-100 shadow-sm border-warning">
            <div class="card-body">
              <CardEntrenamientosDashboard :entrenamiento="entrenamiento" />
              <div class="d-flex gap-2 mt-3">
                <button @click="toggleDetails(entrenamiento.id)" class="btn btn-outline-primary btn-sm">
                  {{ showDetails[entrenamiento.id] ? 'Ocultar' : 'Ver Alumnos' }}
                </button>
                <button @click="deleteEntrenamiento(entrenamiento.id)" class="btn btn-outline-danger btn-sm">
                  <i class="bi bi-trash"></i>
                </button>
              </div>
            </div>

            <div v-if="showDetails[entrenamiento.id]" class="card-footer bg-light border-0">
              <h6 class="text-muted mb-3">Alumnos Inscritos</h6>
              <div class="list-group list-group-flush">
                <div v-for="alumno in entrenamiento.profiles" :key="alumno.id" 
                     class="list-group-item d-flex align-items-center gap-3 bg-transparent">
                  <img :src="alumno.image" 
                       class="rounded-circle" 
                       width="40" 
                       height="40"
                       alt="Perfil">
                  <div class="flex-grow-1">
                    <h6 class="mb-0">{{ alumno.nombre }} {{ alumno.apellidos }}</h6>
                    <small class="text-muted">{{ alumno.edad }} años</small>
                  </div>
                  <button @click="viewAlumno(alumno.id)" 
                          class="btn btn-sm btn-primary">
                    <i class="bi bi-graph-up"></i>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Accepted Entrenamientos -->
    <div class="mb-5">
      <h3 class="h4 text-success">Aceptados</h3>
      <div class="row g-4">
        <div class="col-md-6 col-lg-4" v-for="entrenamiento in getEntranamientosByStatus('accepted')" :key="entrenamiento.id">
          <div class="card h-100 shadow-sm border-success">
            <div class="card-body">
              <CardEntrenamientosDashboard :entrenamiento="entrenamiento" />
              <div class="d-flex gap-2 mt-3">
                <button @click="toggleDetails(entrenamiento.id)" class="btn btn-outline-primary btn-sm">
                  {{ showDetails[entrenamiento.id] ? 'Ocultar' : 'Ver Alumnos' }}
                </button>
                <button @click="deleteEntrenamiento(entrenamiento.id)" class="btn btn-outline-danger btn-sm">
                  <i class="bi bi-trash"></i>
                </button>
              </div>
            </div>

            <div v-if="showDetails[entrenamiento.id]" class="card-footer bg-light border-0">
              <h6 class="text-muted mb-3">Alumnos Inscritos</h6>
              <div class="list-group list-group-flush">
                <div v-for="alumno in entrenamiento.profiles" :key="alumno.id" 
                     class="list-group-item d-flex align-items-center gap-3 bg-transparent">
                  <img :src="alumno.image" 
                       class="rounded-circle" 
                       width="40" 
                       height="40"
                       alt="Perfil">
                  <div class="flex-grow-1">
                    <h6 class="mb-0">{{ alumno.nombre }} {{ alumno.apellidos }}</h6>
                    <small class="text-muted">{{ alumno.edad }} años</small>
                  </div>
                  <button @click="viewAlumno(alumno.id)" 
                          class="btn btn-sm btn-primary">
                    <i class="bi bi-graph-up"></i>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Denied Entrenamientos -->
    <div class="mb-5">
      <h3 class="h4 text-danger">Rechazados</h3>
      <div class="row g-4">
        <div class="col-md-6 col-lg-4" v-for="entrenamiento in getEntranamientosByStatus('denied')" :key="entrenamiento.id">
          <div class="card h-100 shadow-sm border-danger">
            <div class="card-body">
              <CardEntrenamientosDashboard :entrenamiento="entrenamiento" />
              <div class="d-flex gap-2 mt-3">
                <button @click="toggleDetails(entrenamiento.id)" class="btn btn-outline-primary btn-sm">
                  {{ showDetails[entrenamiento.id] ? 'Ocultar' : 'Ver Alumnos' }}
                </button>
                <button @click="deleteEntrenamiento(entrenamiento.id)" class="btn btn-outline-danger btn-sm">
                  <i class="bi bi-trash"></i>
                </button>
              </div>
            </div>

            <div v-if="showDetails[entrenamiento.id]" class="card-footer bg-light border-0">
              <h6 class="text-muted mb-3">Alumnos Inscritos</h6>
              <div class="list-group list-group-flush">
                <div v-for="alumno in entrenamiento.profiles" :key="alumno.id" 
                     class="list-group-item d-flex align-items-center gap-3 bg-transparent">
                  <img :src="alumno.image" 
                       class="rounded-circle" 
                       width="40" 
                       height="40"
                       alt="Perfil">
                  <div class="flex-grow-1">
                    <h6 class="mb-0">{{ alumno.nombre }} {{ alumno.apellidos }}</h6>
                    <small class="text-muted">{{ alumno.edad }} años</small>
                  </div>
                  <button @click="viewAlumno(alumno.id)" 
                          class="btn btn-sm btn-primary">
                    <i class="bi bi-graph-up"></i>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, reactive, computed, watch } from 'vue';
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
    
    onMounted(() => {
      console.log("Despachando la acción para cargar el entrenador...");
      store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_ENTRENADOR}`).then(() => {
          console.log("Entrenador cargado con éxito");
      }).catch(error => {
        console.error("Error al cargar el entrenador:", error);
      });
    });

    store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_ENTRENAMIENTO}`);

    const state = reactive({
      entrenamientos: computed(() => store.getters['entrenadorDashboard/GetEntrenamientos']),
    });

    const showDetails = reactive({});
    const entrenadorId = reactive({ value: null });
    
    watch(
      () => store.state.entrenadorDashboard.entrenador?.usuario,
      (newEntrenador) => {
        if (newEntrenador) {
          entrenadorId.value = newEntrenador.id;
          console.log("Entrenador ID cargado:", entrenadorId.value);
        }
      },
      { immediate: true } 
    );

    const filteredEntrenamientos = computed(() => {
      if (!entrenadorId.value) return [];
      return state.entrenamientos.filter((entrenamiento) => 
        entrenamiento.entrenador.id === entrenadorId.value
      );
    });

    const getEntranamientosByStatus = (status) => {
      return filteredEntrenamientos.value.filter(
        (entrenamiento) => entrenamiento.status === status
      );
    };

    const toggleDetails = (entrenamientoId) => {
      showDetails[entrenamientoId] = !showDetails[entrenamientoId];
    };

    const viewAlumno = (ProfileId) => {
      router.push({ name: 'AlumnoDetail', params: { profileId: ProfileId } });
    };

    const deleteEntrenamiento = async (entrenamientoId) => {
      const confirmDelete = confirm("¿Estás seguro de que deseas eliminar este entrenamiento?");
      if (confirmDelete) {
        try {
          await store.dispatch(`entrenadorDashboard/${Constant.DELETE_ONE_ENTRENAMIENTO}`, entrenamientoId);
          alert("Entrenamiento eliminado exitosamente.");
          await store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_ENTRENAMIENTO}`);
        } catch (error) {
          console.error("Error al eliminar el entrenamiento:", error);
          alert("Hubo un problema al intentar eliminar el entrenamiento.");
        }
      }
    };

    return {
      state,
      filteredEntrenamientos,
      toggleDetails,
      showDetails,
      viewAlumno,
      deleteEntrenamiento,
      getEntranamientosByStatus
    };
  },
};
</script>

<style scoped>  
.rounded-circle{
  object-fit: cover;
}
</style>
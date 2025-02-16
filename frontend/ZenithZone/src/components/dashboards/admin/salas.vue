<template>
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="display-6 mb-4 text-primary">Gestión de Salas</h2>

        <!-- Create Room Form -->
        <div class="card shadow-sm mb-5">
          <div class="card-body p-4">
            <form @submit.prevent="crearSala">
              <div class="row g-4">
                <div class="col-md-6">
                  <div class="form-floating">
                    <input type="text" class="form-control" id="nombre" v-model="nuevaSala.nombre" required>
                    <label for="nombre">Nombre de la Sala</label>
                  </div>
                </div>

                <div class="col-md-6">
                  <div class="form-floating">
                    <input type="text" class="form-control" id="tamaño" v-model="nuevaSala.tamaño" required>
                    <label for="tamaño">Tamaño</label>
                  </div>
                </div>

                <div class="col-md-6">
                  <div class="form-floating">
                    <input type="text" class="form-control" id="ubicacion" v-model="nuevaSala.ubicacion" required>
                    <label for="ubicacion">Ubicación</label>
                  </div>
                </div>

                <div class="col-md-6">
                  <div class="form-floating">
                    <select class="form-select" id="entrenador" v-model="nuevaSala.entrenador_id" required>
                      <option value="" disabled>Seleccione un entrenador</option>
                      <option v-for="entrenador in entrenadoresDisponibles" 
                              :key="entrenador.id" 
                              :value="entrenador.id">
                        {{ entrenador.nombre }}
                      </option>
                    </select>
                    <label for="entrenador">Entrenador</label>
                  </div>
                </div>

                <div class="col-12 text-end">
                  <button type="submit" class="btn btn-primary px-4">
                    <i class="bi bi-plus-circle me-2"></i>Crear Sala
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>

        <!-- Rooms List -->
        <div class="card shadow-sm">
          <div class="card-header bg-white py-3">
            <h5 class="card-title mb-0 text-primary">Salas Existentes</h5>
          </div>
          <div class="table-responsive">
            <table class="table table-hover align-middle mb-0">
              <thead class="table-light">
                <tr>
                  <th><i class="bi bi-card-text me-2"></i>Nombre</th>
                  <th><i class="bi bi-arrows-angle-expand me-2"></i>Tamaño</th>
                  <th><i class="bi bi-geo-alt me-2"></i>Ubicación</th>
                  <th><i class="bi bi-person-badge me-2"></i>Entrenador</th>
                  <th class="text-end"><i class="bi bi-gear me-2"></i>Acciones</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="sala in GetSalas.data" :key="sala.id">
                  <td class="fw-medium">{{ sala.nombre }}</td>
                  <td>{{ sala.tamaño }}</td>
                  <td>{{ sala.ubicacion }}</td>
                  <td>{{ obtenerNombreEntrenador(sala.entrenador_id) }}</td>
                  <td class="text-end">
                    <button @click="eliminarsala(sala.id)" 
                            class="btn btn-outline-danger btn-sm">
                      <i class="bi bi-trash"></i>
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

  <script>
import { mapActions, mapGetters } from "vuex";
import Constant from "../../../Constant";
export default {
  data() {
    return {
      nuevaSala: {
        nombre: "",
        tamaño: "",
        ubicacion: "",
        entrenador_id: "",
      },
    };
  },
  computed: {
    ...mapGetters("adminDashboard", ["GetSalas", "GetEntrenadores"]),
  
    entrenadoresDisponibles() {
        const entrenadoresAsignados = this.GetSalas.data.map(sala => sala.entrenador_id);   
        return this.GetEntrenadores.data.filter(entrenador => 
        !entrenadoresAsignados.includes(entrenador.id)
        );
    }
  },
  
  methods: {
    ...mapActions("adminDashboard", {
      initializeSalas: Constant.INITIALIZE_SALAS,
      createSala: Constant.CREATE_ONE_SALA,
      initializeEntrenadores: Constant.INITIALIZE_ENTRENADOR,
      eliminarSala: Constant.DELETE_ONE_SALA,
    }),
    async crearSala() {
      try {
        await this.createSala(this.nuevaSala);
        this.nuevaSala = {
          nombre: "",
          tamaño: "",
          ubicacion: "",
          entrenador_id: "",
        };
        await this.initializeSalas();
      } catch (error) {
        console.error("Error al crear la sala:", error);
      }
    },
     obtenerNombreEntrenador(entrenador_id) {
        
        const entrenador = this.GetEntrenadores.data.find(e => e.id === entrenador_id);
        return entrenador ? entrenador.nombre : "Desconocido";
     },
     async eliminarsala(salaId){
        if (confirm("¿Estás seguro de que quieres eliminar esta sala?")) {
      try {
        await this.eliminarSala(salaId);
        await this.initializeSalas(); 
      } catch (error) {
        console.error("Error al eliminar la sala:", error);
      }
    }
     }
  },
  created() {
    this.initializeSalas();
    this.initializeEntrenadores();
  },
};
</script>

<style scoped>

</style>
<template>
    <div class="salas-page">
      <h2>Gestión de Salas</h2>
  
      <!-- Formulario para crear una sala -->
      <form @submit.prevent="crearSala" class="form-crear-sala">
        <div class="form-group">
          <label for="nombre">Nombre de la Sala:</label>
          <input
            type="text"
            id="nombre"
            v-model="nuevaSala.nombre"
            class="form-control"
            required
          />
        </div>
  
        <div class="form-group">
          <label for="tamaño">Tamaño:</label>
          <input
            type="text"
            id="tamaño"
            v-model="nuevaSala.tamaño"
            class="form-control"
            required
          />
        </div>
  
        <div class="form-group">
          <label for="ubicacion">Ubicación:</label>
          <input
            type="text"
            id="ubicacion"
            v-model="nuevaSala.ubicacion"
            class="form-control"
            required
          />
        </div>
  
        <div class="form-group">
          <label for="entrenador">Entrenador:</label>
          <select
            id="entrenador"
            v-model="nuevaSala.entrenador_id"
            class="form-control"
            required
          >
            <option disabled value="">Seleccione un entrenador</option>
            <option v-for="entrenador in entrenadoresDisponibles" :key="entrenador.id" :value="entrenador.id">
      {{ entrenador.nombre }}
    </option>
          </select>
        </div>
  
        <button type="submit" class="btn btn-success">Crear Sala</button>
      </form>
  
      <!-- Listado de salas -->
      <div class="listado-salas">
        <h3>Salas Existentes</h3>
        <table class="table table-striped">
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Tamaño</th>
              <th>Ubicación</th>
              <th>Entrenador</th>
              <th>Borrar</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="sala in GetSalas.data" :key="sala.id">
              <td>{{ sala.nombre }}</td>
              <td>{{ sala.tamaño }}</td>
              <td>{{ sala.ubicacion }}</td>
              <td>{{ obtenerNombreEntrenador(sala.entrenador_id) }}</td>
              <td><button @click="eliminarsala(sala.id)" class="btn btn-danger">borrar</button></td>
            </tr>
          </tbody>
        </table>
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
.salas-page {
  margin-top: 20px;
}
.form-crear-sala {
  margin-bottom: 30px;
}
.listado-salas {
  margin-top: 20px;
}
</style>
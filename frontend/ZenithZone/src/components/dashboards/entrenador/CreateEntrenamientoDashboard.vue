<template>
  <div class="create-entrenamiento">
    <h2>Crear Nuevo Entrenamiento</h2>
    <form @submit.prevent="crearEntrenamiento">
      <div class="mb-3">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" id="nombre" v-model="entrenamiento.nombre" class="form-control" required
          placeholder="Ej. Yoga Avanzado" />
      </div>
      <div class="mb-3">
        <label for="descripcion" class="form-label">Descripción</label>
        <textarea id="descripcion" v-model="entrenamiento.descripcion" class="form-control" required
          placeholder="Descripción del entrenamiento"></textarea>
      </div>
      <div class="mb-3">
        <label for="duracion" class="form-label">Duración (minutos)</label>
        <input type="number" id="duracion" v-model="entrenamiento.duracion" class="form-control" required min="1"
          placeholder="Ej. 60" />
      </div>
      <div class="mb-3">
        <label for="max_plazas" class="form-label">Máximo de Plazas</label>
        <input type="number" id="max_plazas" v-model="entrenamiento.max_plazas" class="form-control" required min="1"
          placeholder="Ej. 20" />
      </div>
      <div class="mb-3">
        <label for="precio" class="form-label">Precio (€)</label>
        <input type="number" id="precio" v-model="entrenamiento.precio" class="form-control" required step="1" min="0"
          placeholder="Ej. 25" />
      </div>
      <div class="mb-3">
        <label for="deporte_id" class="form-label">Deporte</label>
        <select id="deporte_id" v-model="entrenamiento.deporte_id" class="form-select" required>
          <option disabled value="">Selecciona un deporte</option>
          <option value="1">Yoga</option>
          <option value="2">Pilates</option>
          <option value="3">CrossFit</option>
          <option value="4">Zumba</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary">Crear</button>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../../Constant';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();
    const entrenamiento = ref({
      nombre: '',
      descripcion: '',
      duracion: '',
      max_plazas: '',
      precio: '',
      deporte_id: ''
    });

    const crearEntrenamiento = async () => {
      if (!entrenamiento.value.deporte_id) {
        console.log('Falta id_deporte');
        return;
      }
      try {
        await store.dispatch(`entrenadorDashboard/${Constant.CREATE_ONE_ENTRENAMIENTO}`, entrenamiento.value);
        router.push('/entrenadorDashboard'); // Redirigir al dashboard después de crear al entrenamiento
      } catch (error) {
        console.error('Error al crear el entrenamiento:', error);
      }
    };

    return {
      entrenamiento,
      crearEntrenamiento
    };
  }
};
</script>

<style scoped>
.create-entrenamiento form {
  max-width: 600px;
  margin: auto;
}
</style>

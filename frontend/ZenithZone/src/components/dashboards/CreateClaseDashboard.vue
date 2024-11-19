<template>
  <div class="create-clase">
    <h2>Crear Nueva Clase</h2>
    <form @submit.prevent="crearClase">
      <div class="mb-3">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" id="nombre" v-model="clase.nombre" class="form-control" required
          placeholder="Ej. Yoga Avanzado" />
      </div>
      <div class="mb-3">
        <label for="descripcion" class="form-label">Descripción</label>
        <textarea id="descripcion" v-model="clase.descripcion" class="form-control" required
          placeholder="Descripción de la clase"></textarea>
      </div>
      <div class="mb-3">
        <label for="duracion" class="form-label">Duración (minutos)</label>
        <input type="number" id="duracion" v-model="clase.duracion" class="form-control" required min="1"
          placeholder="Ej. 60" />
      </div>
      <div class="mb-3">
        <label for="max_plazas" class="form-label">Máximo de Plazas</label>
        <input type="number" id="max_plazas" v-model="clase.max_plazas" class="form-control" required min="1"
          placeholder="Ej. 20" />
      </div>
      <div class="mb-3">
        <label for="precio" class="form-label">Precio (€)</label>
        <input type="number" id="precio" v-model="clase.precio" class="form-control" required step="1" min="0"
          placeholder="Ej. 25" />
      </div>
      <div class="mb-3">
        <label for="deportes_id" class="form-label">Deporte</label>
        <select id="deportes_id" v-model="clase.deportes_id" class="form-select" required>
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
import Constant from '../../Constant';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();
    const clase = ref({
      nombre: '',
      descripcion: '',
      duracion: '',
      max_plazas: '',
      precio: '',
      deportes_id: ''
    });

    const crearClase = async () => {
      if (!clase.value.deportes_id) {
        console.log('Falta id_deporte');
        return;
      }
      try {
        await store.dispatch(`monitorDashboard/${Constant.CREATE_ONE_CLASE}`, clase.value);
        router.push('/monitorDashboard'); // Redirigir al dashboard después de crear la clase
      } catch (error) {
        console.error('Error al crear la clase:', error);
      }
    };

    return {
      clase,
      crearClase
    };
  }
};
</script>

<style scoped>
.create-clase form {
  max-width: 600px;
  margin: auto;
}
</style>

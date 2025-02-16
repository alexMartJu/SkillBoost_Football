<template>
  <div class="container py-4">
    <div class="row g-4">
      <div class="col-12 col-md-6 col-lg-4" v-for="deporte in deportes" :key="deporte.id">
        <CardDeportes
          :deporte="deporte"
          @editarDeporte="editarDeporte"
          @eliminarDeporte="eliminarDeporte"
          class="h-100"
        />
      </div>
    </div>
  </div>
</template>

<script>
import CardDeportes from './CardDeportes.vue'; 

export default {
  name: 'ListDeportes',
  components: {
    CardDeportes
  },
  props: {
    deportes: Array,
  },
  methods: {
    editarDeporte(deporte) {
      this.$emit('editarDeporte', deporte); 
    },
    eliminarDeporte(deporteId) {
      this.$emit('eliminarDeporte', deporteId); 
    },
  },
};
</script>

<style scoped>
.container {
  background-color: #f8f9fa;
}

.col-12 {
  transition: transform 0.3s ease;
}

.col-12:hover {
  transform: translateY(-5px);
}
</style>
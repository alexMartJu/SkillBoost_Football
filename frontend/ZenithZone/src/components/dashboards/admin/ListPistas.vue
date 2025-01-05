
<template>
  <div class="pista-list">
    <CardPistas
      v-for="pista in pistas"
      :key="pista.id"
      :pista="pista"
      :isPrivate ="isPrivate"
      @eliminarPista="eliminarPista"
    />
  </div>
</template>

<script>
import CardPistas from '@/components/dashboards/admin/CardPistas.vue';

export default {
  name: 'ListPistas',
  components: {
    CardPistas
  },
  props: {
    pistas: Array,
    isPrivate: Boolean,
  },
  methods: {
    editarPista(pista) {
      console.log("editar", pista);
      this.$router.push({ name: 'adminEditarPista', params: { slug: pista.slug } });
    },
    eliminarPista(pistaId) {
      this.$emit('eliminarPista', pistaId);
    },
  },
};
</script>
<style scoped>
/* Estilo para listas de pistas */
.pista-list {
  display: flex;
  flex-wrap: wrap; /* Permite que los elementos se ajusten en varias filas */
  gap: 20px; /* Espaciado entre los elementos */
  justify-content: space-between; /* Asegura que las columnas ocupen el espacio disponible */
}

.pista-list > div {
  background-color: #fff;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: calc(33.33% - 20px); /* 3 elementos por fila en pantallas grandes */
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
}

.pista-list > div:hover {
  transform: translateY(-5px); /* Efecto de elevación al pasar el mouse */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

/* Responsividad: 2 columnas en pantallas medianas (menos de 768px) */
@media (max-width: 768px) {
  .pista-list > div {
    width: calc(50% - 20px); /* 2 elementos por fila */
  }
}

/* Responsividad: 1 columna en pantallas pequeñas (menos de 480px) */
@media (max-width: 480px) {
  .pista-list > div {
    width: 100%; /* 1 elemento por fila */
  }
}
</style>

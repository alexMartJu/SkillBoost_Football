  <template>
    <div class="container">
      <div class="row g-4">
        <div class="col-12 col-md-6 col-lg-4" v-for="pista in pistas" :key="pista.id">
          <CardPistas :pista="pista" :isPrivate="isPrivate" @eliminarPista="eliminarPista" class="h-100" />
        </div>
      </div>
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
.container {
  padding: 2rem 0;
}

.card {
  border: none;
  border-radius: 0.5rem;
  box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
  transition: all 0.3s ease;
}

.card:hover {
  transform: translateY(-0.25rem);
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}
</style>

<template>
  <div class="crear-admin container text-center">
    <h2 class="mb-4">Crear Deportes y Pistas</h2>
    
    <div class="btn-group mb-4" role="group" aria-label="Selector de vista">
      <button 
        @click="activeView = 'deportes'" 
        :class="['btn', activeView === 'deportes' ? 'btn-primary' : 'btn-outline-primary']"
        style="width: 120px"
      >
        Deportes
      </button>
      <button 
        @click="activeView = 'pistas'" 
        :class="['btn', activeView === 'pistas' ? 'btn-primary' : 'btn-outline-primary']"
        style="width: 120px"
      >
        Pistas
      </button>
    </div>

    <!-- Form Deportes -->
    <div v-if="activeView === 'deportes'">
      <FormDeportes 
        :formMode="formMode"
        :currentDeporteSlug="currentDeporteSlug"
        :deporteData="currentDeporteData" 
      />
    </div>

    <!-- Form Pistas -->
    <div v-if="activeView === 'pistas'">
      <FormPistas 
        :formMode="formMode"
        :currentPistaSlug="currentPistaSlug"
        :deportes="deportes"
        :pistaData="pistaData"
      />
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import FormDeportes from './FormDeportes.vue';
import FormPistas from './FormPistas.vue';

export default {
  name: 'CrearAdmin',
  components: {
    FormDeportes,
    FormPistas
  },
  data() {
    return {
      activeView: 'deportes',
      formMode: 'create',
      currentDeporteSlug: null,
      currentPistaSlug: null,
      pistaData: {},
      currentDeporteData: {}
    };
  },
  computed: {
    ...mapGetters({
      deportes: 'adminDashboard/GetDeportes'
    })
  },
  created() {
    this.checkSlug();
  },
  watch: {
    '$route.params.slug': 'checkSlug',
    '$route.params.type': 'checkSlug'
  },
  methods: {
    checkSlug() {
      const { type, slug } = this.$route.params;
      if (type && slug) {
        if (type === 'deporte') {
          this.currentDeporteSlug = slug;
          this.formMode = 'edit';
          this.loadDeporteData(this.currentDeporteSlug);
        } else if (type === 'pista') {
          this.currentPistaSlug = slug;
          this.formMode = 'edit';
          this.loadPistaData(this.currentPistaSlug);
        }
      }
    },
    async loadDeporteData(slug) {
      try {
        const deporteData = await this.$store.dispatch('adminDashboard/FetchDeporteBySlug', slug);
        if (deporteData) {
          this.currentDeporteData = deporteData;
        }
      } catch (error) {
        console.error('Error fetching deporte data:', error);
      }
    },
    async loadPistaData(slug) {
      try {
        const pistaData = await this.$store.dispatch('adminDashboard/FetchPistaBySlug', slug);
        if (pistaData) {
          this.pistaData = pistaData;
        }
      } catch (error) {
        console.error('Error fetching pista data:', error);
      }
    }
  }
};
</script>

<style scoped>
.crear-admin {
  padding: 2rem 0;
}

.btn-group {
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.btn {
  padding: 0.75rem 2rem;
  font-weight: 500;
  transition: all 0.3s ease;
}
</style>
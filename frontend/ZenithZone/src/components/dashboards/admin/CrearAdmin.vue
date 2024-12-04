<template>
    <div class="create-admin">
      <!-- Deporte Form -->
      <FormDeportes v-if="formMode === 'create' || currentDeporteSlug"
                   :formMode="formMode"
                   :currentDeporteSlug="currentDeporteSlug"
                   :deporteData="currentDeporteData" />
      
      <!-- Pista Form -->
      <FormPistas v-if="formMode === 'create' || currentPistaSlug"
                 :formMode="formMode"
                 :currentPistaSlug="currentPistaSlug"
                 :deportes="deportes" 
                 :pistaData="pistaData"/>
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
        formMode: 'create', // Indica si estamos en modo creación o actualización
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
      console.log(this.$route.params.slug+"slug");
      console.log(JSON.stringify(this.$route.params)+"tipo");
      if (type && slug) {
        if (type === 'deporte') {
          // Si es un deporte
          this.currentDeporteSlug = slug;
          this.formMode = 'edit'; // Modo edición
          this.loadDeporteData(this.currentDeporteSlug); // Cargar datos del deporte
        } else if (type === 'pista') {
          // Si es una pista
          console.log(this.$route.params.slug+"slugyslug");
          this.currentPistaSlug = slug;
          this.formMode = 'edit'; // Modo edición
          this.loadPistaData(this.currentPistaSlug); // Cargar datos de la pista
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
            // this.currentPistaSlug = pistaData.slug;
            this.pistaData = pistaData;
          }
        } catch (error) {
          console.error('Error fetching pista data:', error);
        }
      }
    }
  };
  </script>

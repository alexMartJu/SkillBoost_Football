<template>
    <div class="pistas-privadas">
      <h2>Pistas Privadas</h2>
      <div v-if="pistasPrivadas && pistasPrivadas.length">
        <h3>Listado de Pistas Privadas</h3>
        <ListPistas
          :pistas="pistasPrivadas"
          @editarPista="editarPista"
          @eliminarPista="eliminarPista"
        />
      </div>
      <div v-else>
        <p>No hay pistas privadas disponibles.</p>
      </div>
  
      <FormPistasPrivadas
        :formMode="formMode"
        :currentPistaSlug="currentPistaSlug"
        :deportes="deportes"
        :pistaData="pistaData"
        :isPrivate="true"
        @pistaGuardada="refrescarPistas"
      />
    </div>
  </template>
  
  <script>
  import { mapGetters } from 'vuex';
  import ListPistas from '@/components/dashboards/admin/ListPistas.vue';
  import FormPistasPrivadas from '@/components/dashboards/admin/FormPistasPrivadas.vue';
  import Constant from '../../../Constant';
  export default {
    name: 'PistasPrivadas',
    components: {
      ListPistas,
      FormPistasPrivadas,
    },
    data() {
      return {
        formMode: 'create',
        currentPistaSlug: null,
        pistaData: {},
      };
    },
    computed: {
      ...mapGetters({
        deportes: 'adminDashboard/GetDeportes',
        pistasPrivadas: 'adminDashboard/GetPistasPrivadas',     
      }),
    },
    created() {
      this.inicializar();
    },
    methods: {
      inicializar() {
        this.$store.dispatch(`adminDashboard/${Constant.INITIALIZE_DEPORTE}`);
        this.$store.dispatch(`adminDashboard/${Constant.INITIALIZE_PISTA_PRIVADA}`);
      },
      editarPista(pista) {
        this.formMode = 'edit';
        this.currentPistaSlug = pista.slug;
        this.loadPistaData(pista.slug);
      },
      async loadPistaData(slug) {
        try {
          const pistaData = await this.$store.dispatch(`adminDashboard/${Constant.FETCH_PISTA_PRIVADA_BY_SLUG}`, slug);
          if (pistaData) {
            this.pistaData = pistaData;
          }
        } catch (error) {
          console.error('Error fetching pista privada data:', error);
        }
      },
      async eliminarPista(pistaSlug) {
        if (confirm('¿Estás seguro de que deseas eliminar esta pista privada?')) {
          try {
            await this.$store.dispatch(`adminDashboard/${Constant.DELETE_ONE_PISTA_PRIVADA}`, pistaSlug);
            this.refrescarPistas();
          } catch (error) {
            console.error('Error al eliminar la pista privada:', error);
          }
        }
      },
      refrescarPistas() {
        this.$store.dispatch(`adminDashboard/${Constant.CREATE_ONE_PISTA_PRIVADA}`);
        this.formMode = 'create';
        this.currentPistaSlug = null;
        this.pistaData = {};
      },
    },
  };
  </script>
  
  <style scoped>
  .pistas-privadas {
    padding: 20px;
  }
  
  h2 {
    font-size: 2.5em;
    margin-bottom: 20px;
  }
  
  h3 {
    font-size: 2em;
    margin-top: 20px;
    color: #4CAF50;
  }
  </style>
  
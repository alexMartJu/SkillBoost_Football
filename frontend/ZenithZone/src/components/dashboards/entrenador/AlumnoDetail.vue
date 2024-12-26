<template>
    <div class="alumno-detail" v-if="profile">
      <h1>{{ profile.nombre }} {{ profile.apellidos }}</h1>
  
      <!-- Perfil del alumno -->
      <div class="profile-info">
        <img :src="getProfileImageUrl(profile.image)" alt="Imagen del alumno" class="alumno-image" />
        <div class="info">
          <p>Edad: {{ profile.edad }} años</p>
          <p>Número de Socio: {{ profile.numerosocio }}</p>
        </div>
      </div>
  
      <h2>Progreso del Alumno</h2>
      <!-- La gráfica será lo más grande en la pantalla -->
      <div class="grafica-container">
        <GraficaAlumno :profileId="profile.id" />
      </div>
    </div>
  
    <div v-else>
      <p>Cargando perfil...</p>
    </div>
  </template>
  
  <script>
  import { ref, computed, onMounted } from 'vue';
  import { useStore } from 'vuex';
  import GraficaAlumno from './GraficaAlumno.vue';
  import Constant from '../../../Constant';
  
  export default {
    name: 'AlumnoDetail',
    components: {
      GraficaAlumno,
    },
    computed: {
      // Obtener el perfil del alumno desde Vuex
      profile() {
        return this.$store.getters['entrenadorDashboard/GetProfile'];
      }
    },
    methods: {
      getProfileImageUrl(image) {
        return image ? `/assets/profile/${image}` : '/assets/profile/default.png';
      },
  
      // Obtener el perfil del alumno desde Vuex
      async fetchProfile() {
        const { profileId } = this.$route.params;
        await this.$store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_PROFILE}`, profileId);
      },
    },
    async mounted() {
      await this.fetchProfile();
      if (!this.profile || !this.profile.id) {
        console.error('Profile ID is undefined or missing.');
      }
    },
  };
  </script>
  
  <style scoped>
  .alumno-detail {
    text-align: center;
  }
  
  .profile-info {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 20px;
    margin-bottom: 20px;
  }
  
  .alumno-image {
    width: 100px; /* Reducimos el tamaño de la imagen */
    height: 100px;
    border-radius: 50%;
    object-fit: cover;
}

.info {
  text-align: left;
}

.grafica-container {
  width: 80%; /* Hacemos la gráfica más ancha */
  margin: 0 auto;
  padding: 20px;
}

h2 {
  font-size: 24px;
  margin-bottom: 20px;
}

p {
  font-size: 16px;
}
</style>
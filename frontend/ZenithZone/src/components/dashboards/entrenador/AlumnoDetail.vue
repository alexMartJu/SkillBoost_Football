<template>
  <div class="container py-5">
    <h2 class="display-6 mb-4 text-primary">Perfil del Alumno</h2>
    
    <div class="card shadow-sm mb-4" v-if="profile">
      <div class="card-body">
        <div class="d-flex align-items-center gap-4">
          <img :src="profile.image" 
               class="rounded-circle" 
               width="120" 
               height="120"
               alt="Perfil">
          <div>
            <h3 class="h4 mb-2">{{ profile.nombre }} {{ profile.apellidos }}</h3>
            <div class="d-flex gap-3">
              <span class="badge bg-light text-dark">
                <i class="bi bi-person-vcard me-1"></i>
                Socio #{{ profile.numerosocio }}
              </span>
              <span class="badge bg-light text-dark">
                <i class="bi bi-calendar-event me-1"></i>
                {{ profile.edad }} años
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="card shadow-sm">
      <div class="card-body">
        <h4 class="h5 mb-4">Evolución del Rendimiento</h4>
        <GraficaAlumno :profileId="profile.id" />
      </div>
    </div>

    <div v-if="!profile" class="text-center py-5">
      <div class="spinner-border text-primary" role="status">
        <span class="visually-hidden">Cargando...</span>
      </div>
      <p class="mt-3">Cargando perfil...</p>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue';
import GraficaAlumno from './GraficaAlumno.vue';
import Constant from '../../../Constant';

export default defineComponent({
    name: 'AlumnoDetail',
    components: {
        GraficaAlumno,
    },
    computed: {
        profile() {
            return this.$store.getters['entrenadorDashboard/GetProfile'];
        }
    },
    async beforeMount() {
        await this.fetchProfile();
    },
    methods: {
        async fetchProfile() {
            const { profileId } = this.$route.params;
            if (profileId) {
                await this.$store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_PROFILE}`, profileId);
            }
        }
    },
    watch: {
        '$route.params.profileId': {
            immediate: true,
            handler: 'fetchProfile'
        }
    }
});
</script>

<style scoped>
.container {
  background-color: #f8f9fa;
}

.card {
  transition: transform 0.2s;
}

.card:hover {
  transform: translateY(-2px);
}

.badge {
  font-size: 0.9rem;
  padding: 0.5rem 0.8rem;
}

.bi {
  font-size: 1.1rem;
}

.rounded-circle{
  object-fit: cover;
}
</style>
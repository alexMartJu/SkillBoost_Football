<template>
  <div class="container py-5">
    <h1 class="display-5 fw-bold text-primary mb-4">Gestión de Reservas</h1>

    <div class="alert alert-dismissible fade show" 
         :class="mensaje?.tipo === 'exito' ? 'alert-success' : 'alert-danger'" 
         v-if="mensaje">
      {{ mensaje.texto }}
    </div>

    <!-- Reservas Actuales -->
    <div class="card shadow-sm mb-5">
      <div class="card-header bg-white">
        <h2 class="h4 mb-0 text-primary">Reservas Activas</h2>
      </div>
      <div class="card-body p-0">
        <div class="list-group list-group-flush">
          <div v-for="reserva in reservasFuturas" :key="reserva.id" 
               class="list-group-item list-group-item-action">
            <div class="d-flex justify-content-between align-items-center">
              <div>
                <h6 class="mb-1">{{ reserva.profile.nombre }} {{ reserva.profile.apellidos }}</h6>
                <div class="small text-muted">
                  <i class="bi bi-calendar me-2"></i>{{ formatFecha(reserva.fecha) }}
                  <i class="bi bi-geo-alt ms-3 me-2"></i>{{ reserva.pista.nombre }}
                  <i class="bi bi-clock ms-3 me-2"></i>{{ reserva.horario.hora }}
                </div>
              </div>
              <button @click="borrarReserva(reserva.id)" 
                      class="btn btn-outline-danger btn-sm">
                <i class="bi bi-trash"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Historial -->
    <div class="card shadow-sm">
      <div class="card-header bg-white">
        <h2 class="h4 mb-0 text-primary">Historial</h2>
      </div>
      <div class="card-body p-0">
        <div class="list-group list-group-flush">
          <div v-for="reserva in reservasPasadas" :key="reserva.id" 
               class="list-group-item list-group-item-action">
            <div class="d-flex justify-content-between align-items-center">
              <div>
                <h6 class="mb-1">{{ reserva.profile.nombre }} {{ reserva.profile.apellidos }}</h6>
                <div class="small text-muted">
                  <i class="bi bi-calendar me-2"></i>{{ formatFecha(reserva.fecha) }}
                  <i class="bi bi-geo-alt ms-3 me-2"></i>{{ reserva.pista.nombre }}
                  <i class="bi bi-clock ms-3 me-2"></i>{{ reserva.horario.hora }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
  import Constant from '@/Constant';
  import { mapActions, mapGetters } from 'vuex';
  
  export default {
    name: "Reservas",
    computed: {
      ...mapGetters('adminDashboard', {
        reservas: 'GetReservas',
      }),
      reservasFuturas() {
        return this.reservas.filter(reserva => new Date(reserva.fecha) >= new Date());
      },
      reservasPasadas() {
        return this.reservas.filter(reserva => new Date(reserva.fecha) < new Date());
      },
    },
    methods: {
      ...mapActions('adminDashboard', {
        cargarReservas: Constant.INITIALIZE_RESERVA,
        eliminarReserva: Constant.DELETE_ONE_RESERVA,
      }),
      async borrarReserva(reservaId) {
        try {
            
            await this.eliminarReserva(reservaId);
           
            this.mensaje = {
            tipo: 'exito',
            texto: 'Reserva eliminada exitosamente.', 
            };
        } catch (error) {
          
            this.mensaje = {
            tipo: 'error', 
            texto: 'Error al eliminar la reserva.', 
            };
        }
    },
      formatFecha(fecha) {
        const date = new Date(fecha);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
      },
    },
    async mounted() {
      console.log("Llamando a cargarReservas...");
      await this.cargarReservas();  
      console.log("Reservas cargadas:", this.reservas);
    },
  };
  </script>
  
<style scoped>
.card {
  border: none;
  border-radius: 1rem;
}

.card-header {
  border-bottom: 1px solid rgba(0,0,0,.125);
}

.list-group-item {
  transition: all 0.2s ease;
}

.list-group-item:hover {
  background-color: #f8f9fa;
}

.btn-outline-danger {
  border-radius: 0.5rem;
}
</style>
  
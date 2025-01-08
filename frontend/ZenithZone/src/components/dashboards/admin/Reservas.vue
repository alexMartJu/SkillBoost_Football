<template>
    <div class="reservas-container">
      <h1>Reservas</h1>

      <div v-if="mensaje" :class="['mensaje', mensaje.tipo]">
      {{ mensaje.texto }}
    </div>
  
      <div class="section">
        <h2>Reservas </h2>
        <ul class="reservas-list">
          <li v-for="reserva in reservasFuturas" :key="reserva.id" class="reserva-item">
            <div class="reserva-info">
              <div class="reserva-nombre">
                <span>{{ reserva.profile.nombre }} {{ reserva.profile.apellidos }}</span>
              </div>
              <div class="reserva-detalles">
                <span class="reserva-fecha">{{ formatFecha(reserva.fecha) }}</span>
                <span class="reserva-pista">{{ reserva.pista.nombre }}</span>
                <span class="reserva-hora">{{ reserva.horario.hora }}</span>
              </div>
            </div>
            <button @click="borrarReserva(reserva.id)" class="eliminar-btn">Eliminar</button>
          </li>
        </ul>
      </div>
  
      <div class="section">
        <h2>Historial</h2>
        <ul class="reservas-list">
          <li v-for="reserva in reservasPasadas" :key="reserva.id" class="reserva-item">
            <div class="reserva-info">
              <div class="reserva-nombre">
                <span>{{ reserva.profile.nombre }} {{ reserva.profile.apellidos }}</span>
              </div>
              <div class="reserva-detalles">
                <span class="reserva-fecha">{{ formatFecha(reserva.fecha) }}</span>
                <span class="reserva-pista">{{ reserva.pista.nombre }}</span>
                <span class="reserva-hora">{{ reserva.horario.hora }}</span>
              </div>
            </div>
          </li>
        </ul>
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
  .reservas-container {
    font-family: 'Arial', sans-serif;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  h1 {
    text-align: center;
    color: #333;
  }
  
  .section {
    margin-top: 20px;
  }
  
  h2 {
    color: #2c3e50;
    border-bottom: 2px solid #3498db;
    padding-bottom: 8px;
    font-size: 1.5rem;
  }
  
  .reservas-list {
    list-style-type: none;
    padding: 0;
  }
  
  .reserva-item {
    background-color: #fff;
    padding: 15px;
    margin-bottom: 10px;
    border-radius: 6px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: space-between;
    align-items: center;
    transition: background-color 0.3s ease;
  }
  
  .reserva-item:hover {
    background-color: #f1f1f1;
  }
  
  .reserva-info {
    display: flex;
    flex-direction: column;
    width: 80%;
  }
  
  .reserva-nombre {
    font-size: 1.2rem;
    font-weight: bold;
    color: #333;
    margin-bottom: 5px;
  }
  
  .reserva-detalles {
    display: flex;
    justify-content: space-between;
    font-size: 1rem;
    color: #7f8c8d;
  }
  
  .reserva-fecha, .reserva-pista, .reserva-hora {
    margin-right: 10px;
  }
  
  .eliminar-btn {
    padding: 8px 16px;
    background-color: #e74c3c;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .eliminar-btn:hover {
    background-color: #c0392b;
  }
  
  .eliminar-btn:focus {
    outline: none;
  }
  </style>
  
<template>
    <div class="row justify-content-center">
        <div v-for="(plan, index) in suscripciones" :key="plan.slug" class="col-md-4 mb-4">
            <div class="card h-100 shadow-sm plan-card" :class="{ 'popular': plan.slug === 'desarrollo-avanzado' }">
                <div v-if="plan.slug === 'desarrollo-avanzado'" class="ribbon">
                    <span>Popular</span>
                </div>
                <div class="card-header text-center py-4" :class="getHeaderClass(index)">
                    <h3 class="my-0 fw-bold">{{ plan.nombre }}</h3>
                </div>
                <div class="card-body d-flex flex-column">
                    <h1 class="card-title pricing-card-title text-center mb-4">
                        {{ formatPrice(plan.precio) }}
                        <small class="text-muted fw-light">/mes</small>
                    </h1>

                    <ul class="list-unstyled mt-3 mb-4">
                        <li class="mb-2" v-if="plan.ventaja1">
                            <i class="bi bi-check-circle-fill text-success me-2"></i>
                            {{ plan.ventaja1 }}
                        </li>
                        <li class="mb-2" v-if="plan.ventaja2">
                            <i class="bi bi-check-circle-fill text-success me-2"></i>
                            {{ plan.ventaja2 }}
                        </li>
                        <li class="mb-2" v-if="plan.ventaja3">
                            <i class="bi bi-check-circle-fill text-success me-2"></i>
                            {{ plan.ventaja3 }}
                        </li>
                        <li class="mb-2" v-if="plan.ventaja4">
                            <i class="bi bi-check-circle-fill text-success me-2"></i>
                            {{ plan.ventaja4 }}
                        </li>
                    </ul>

                    <div class="mt-auto">
                        <button type="button" class="w-100 btn btn-lg" :class="getButtonClass(index)"
                            @click="seleccionarPlan(plan)">
                            Suscribirse
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import Swal from 'sweetalert2';
import { useRouter } from 'vue-router';

export default {
  name: 'PlanesCards',
  props: {
    suscripciones: {
      type: Array,
      required: true
    }
  },
  emits: ['seleccionarPlan'],
  setup(props, { emit }) {
    const store = useStore();
    const router = useRouter();

    const formatPrice = (price) => {
      return new Intl.NumberFormat('es-ES', {
        style: 'currency',
        currency: 'EUR'
      }).format(price);
    };

    const getHeaderClass = (index) => {
      const classes = [
        'bg-primary text-white',
        'bg-success text-white',
        'bg-info text-white'
      ];
      return classes[index % classes.length];
    };

    const getButtonClass = (index) => {
      const classes = [
        'btn-outline-primary',
        'btn-success',
        'btn-outline-info'
      ];
      return classes[index % classes.length];
    };

    const seleccionarPlan = (plan) => {
      const isAuth = store.getters['user/GetIsAuth'];
      const userType = store.getters['user/GetUserType'];
      
      if (!isAuth) {
        //Usuario no autenticado
        Swal.fire({
          title: 'Acceso denegado',
          text: 'Debes iniciar sesión para realizar un pago',
          icon: 'warning',
          confirmButtonText: 'Ir a iniciar sesión',
          cancelButtonText: 'Cancelar',
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          showCancelButton: true
        }).then((result) => {
          if (result.isConfirmed) {
            router.push({ name: 'login' });
          }
        });
      } else if (userType && userType.toLowerCase() !== 'jugador' && userType.toLowerCase() !== 'tutor') {
        //Usuario autenticado pero no es jugador ni tutor
        Swal.fire({
          title: 'Acceso denegado',
          text: 'Tus pagos los gestionan los clubes u organizaciones',
          icon: 'info',
          confirmButtonText: 'Entendido',
          confirmButtonColor: '#3085d6',
        });
      } else {
        //Usuario autorizado, emitir evento
        emit('seleccionarPlan', plan);
      }
    };

    return {
      formatPrice,
      getHeaderClass,
      getButtonClass,
      seleccionarPlan
    };
  }
}
</script>

<style scoped>
.plan-card {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    border-radius: 10px;
    overflow: hidden;
    position: relative;
}

.plan-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
}

.popular {
    transform: scale(1.05);
    z-index: 1;
    border: 2px solid #28a745;
}

.popular:hover {
    transform: scale(1.05) translateY(-10px);
}

.ribbon {
    position: absolute;
    right: -5px;
    top: -5px;
    z-index: 1;
    overflow: hidden;
    width: 100px;
    height: 100px;
    text-align: right;
}

.ribbon span {
    font-size: 0.8rem;
    font-weight: bold;
    color: #fff;
    text-align: center;
    line-height: 30px;
    transform: rotate(45deg);
    width: 130px;
    display: block;
    background: #28a745;
    box-shadow: 0 3px 10px -5px rgba(0, 0, 0, 1);
    position: absolute;
    top: 20px;
    right: -30px;
}

.ribbon span::before {
    content: "";
    position: absolute;
    left: 0px;
    top: 100%;
    z-index: -1;
    border-left: 3px solid #1a6e2e;
    border-right: 3px solid transparent;
    border-bottom: 3px solid transparent;
    border-top: 3px solid #1a6e2e;
}

.ribbon span::after {
    content: "";
    position: absolute;
    right: 0px;
    top: 100%;
    z-index: -1;
    border-left: 3px solid transparent;
    border-right: 3px solid #1a6e2e;
    border-bottom: 3px solid transparent;
    border-top: 3px solid #1a6e2e;
}
</style>
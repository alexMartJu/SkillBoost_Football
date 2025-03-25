<template>
  <div class="container-fluid">
    <div class="row justify-content-center">
      <div class="col-12 col-md-10 col-lg-8 col-xl-6">
        <!-- Header -->
        <div class="text-center mb-4">
          <h3 class="fw-bold">Finaliza tu suscripción</h3>
          <p class="text-muted">Plan seleccionado: <span class="fw-medium">{{ suscripcion?.nombre }}</span></p>
        </div>

        <!-- Loading state -->
        <div v-if="loading && !suscripcion" class="text-center py-5">
          <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Cargando...</span>
          </div>
          <p class="mt-3 text-muted">Cargando información del plan...</p>
        </div>

        <div v-else-if="suscripcion">
          <!-- Payment Summary -->
          <div class="card border-0 shadow-sm rounded-3 mb-4">
            <div class="card-body p-3 p-md-4">
              <div class="d-flex justify-content-between align-items-center mb-3">
                <div>
                  <h5 class="mb-1">{{ suscripcion.nombre }}</h5>
                  <p class="text-muted mb-0 small">Suscripción mensual</p>
                </div>
                <h5 class="fw-bold text-primary mb-0">{{ formatPrice(suscripcion.precio) }}</h5>
              </div>

              <hr class="my-3">

              <div class="d-flex justify-content-between">
                <span>Total a pagar</span>
                <span class="fw-bold">{{ formatPrice(suscripcion.precio) }}</span>
              </div>
            </div>
          </div>

          <!-- Payment Methods -->
          <h5 class="fw-medium mb-3 fs-6">Selecciona tu método de pago</h5>

          <!-- Nav tabs -->
          <ul class="nav nav-pills nav-fill bg-light rounded-3 p-1 mb-3" id="paymentTabs" role="tablist">
            <li class="nav-item" role="presentation">
              <button class="nav-link active rounded-3 py-2" id="card-tab" data-bs-toggle="tab"
                data-bs-target="#card-panel" type="button" role="tab" aria-controls="card-panel" aria-selected="true">
                <i class="bi bi-credit-card me-2"></i>Tarjeta
              </button>
            </li>
            <li class="nav-item" role="presentation">
              <button class="nav-link rounded-3 py-2" id="paypal-tab" data-bs-toggle="tab"
                data-bs-target="#paypal-panel" type="button" role="tab" aria-controls="paypal-panel"
                aria-selected="false">
                <i class="bi bi-paypal me-2"></i>PayPal
              </button>
            </li>
          </ul>

          <!-- Tab content -->
          <div class="tab-content" id="paymentTabsContent">
            <!-- Credit Card Panel -->
            <div class="tab-pane fade show active" id="card-panel" role="tabpanel" aria-labelledby="card-tab">
              <div class="card border-0 shadow-sm rounded-3">
                <div class="card-body p-3 p-md-4">
                  <div class="mb-4">
                    <div id="card-element" class="form-control p-3 bg-light border"></div>
                    <div id="card-errors" class="text-danger mt-2 small"></div>
                  </div>

                  <button id="stripe-btn" class="btn btn-primary w-100 py-2 rounded-3" @click="processStripePayment"
                    :disabled="processingPayment">
                    <div class="d-flex align-items-center justify-content-center">
                      <div v-if="processingPayment" class="spinner-border spinner-border-sm me-2" role="status"></div>
                      <i v-else class="bi bi-lock-fill me-2"></i>
                      <span>Pagar {{ formatPrice(suscripcion.precio) }}</span>
                    </div>
                  </button>
                </div>
              </div>
            </div>

            <!-- PayPal Panel -->
            <div class="tab-pane fade" id="paypal-panel" role="tabpanel" aria-labelledby="paypal-tab">
              <div class="card border-0 shadow-sm rounded-3">
                <div class="card-body p-3 p-md-4">
                  <div id="paypal-button-container" class="mt-2"></div>
                </div>
              </div>
            </div>
          </div>

          <!-- Footer -->
          <div class="d-flex justify-content-between align-items-center mt-4">
            <button class="btn btn-outline-secondary rounded-3" @click="volver">
              <i class="bi bi-arrow-left me-2"></i>Volver
            </button>

            <div class="d-flex align-items-center text-success">
              <i class="bi bi-shield-lock-fill me-2"></i>
              <span class="small text-muted">Pago 100% seguro</span>
            </div>
          </div>
        </div>

        <!-- Error state -->
        <div v-else class="text-center py-5">
          <div class="alert alert-danger" role="alert">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            No se pudo cargar la información del plan. Por favor, inténtalo de nuevo.
          </div>
          <button class="btn btn-outline-secondary mt-3" @click="volver">
            <i class="bi bi-arrow-left me-2"></i>Volver a planes
          </button>
        </div>
      </div>
    </div>

    <!-- Toast Container -->
    <div class="toast-container position-fixed bottom-0 end-0 p-3">
      <!-- Success Toast -->
      <div id="successToast" class="toast align-items-center text-white bg-success border-0" role="alert"
        aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
          <div class="toast-body">
            <i class="bi bi-check-circle-fill me-2"></i>
            ¡Pago completado con éxito! Redirigiendo...
          </div>
          <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"
            aria-label="Close"></button>
        </div>
      </div>

      <!-- Error Toast -->
      <div id="errorToast" class="toast align-items-center text-white bg-danger border-0" role="alert"
        aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
          <div class="toast-body">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            Error al procesar el pago. Por favor, inténtalo de nuevo.
          </div>
          <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"
            aria-label="Close"></button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useStore } from 'vuex';
import { loadStripe } from '@stripe/stripe-js';
import PaymentService from '@/services/client/payment.service';
import { Toast } from 'bootstrap';
import Constant from '@/Constant';
import Swal from 'sweetalert2';

export default {
  name: 'ProcesoPagoDirecto',
  props: {
    slug: {
      type: String,
      required: true
    }
  },
  setup(props) {
    const router = useRouter();
    const route = useRoute();
    const store = useStore();

    const loading = ref(true);
    const processingPayment = ref(false);
    const suscripcion = ref(null);

    let stripe = null;
    let cardElement = null;
    let successToast = null;
    let errorToast = null;

    //Verificar si el usuario puede acceder al proceso de pago
    const verificarAcceso = () => {
      const isAuth = store.getters['user/GetIsAuth'];
      const userType = store.getters['user/GetUserType'];

      if (!isAuth) {
        //Usuario no autenticado
        Swal.fire({
          title: 'Acceso denegado',
          text: 'Debes iniciar sesión para realizar un pago',
          icon: 'warning',
          confirmButtonText: 'Ir a iniciar sesión'
        }).then((result) => {
          if (result.isConfirmed) {
            router.push({ name: 'login' });
          } else {
            router.push({ name: 'planes' });
          }
        });
        return false;
      } else if (userType && userType.toLowerCase() !== 'jugador' && userType.toLowerCase() !== 'tutor') {
        //Usuario autenticado pero no es jugador ni tutor
        Swal.fire({
          title: 'Acceso denegado',
          text: 'Tus pagos los gestionan los clubes u organizaciones',
          icon: 'info',
          confirmButtonText: 'Entendido'
        }).then(() => {
          router.push({ name: 'planes' });
        });
        return false;
      }

      return true;
    };

    const formatPrice = (price) => {
      return new Intl.NumberFormat('es-ES', {
        style: 'currency',
        currency: 'EUR'
      }).format(price);
    };

    //Mostrar el toast de éxito
    const showSuccessToast = () => {
      if (successToast) {
        successToast.show();
      }
    };

    //Mostrar el toast de error
    const showErrorToast = () => {
      if (errorToast) {
        errorToast.show();
      }
    };

    //Volver a la lista de planes
    const volver = () => {
      router.push({ name: 'planes' });
    };

    //Cargar la suscripción según el slug
    const cargarSuscripcion = async () => {
      loading.value = true;
      try {
        //Primero aseguramos que las suscripciones estén cargadas
        await store.dispatch(`suscripciones/${Constant.INITIALIZE_SUSCRIPCION}`);

        //Obtenemos todas las suscripciones
        const suscripciones = store.getters['suscripciones/GetSuscripciones'];

        //Buscamos la suscripción que coincida con el slug
        const suscripcionEncontrada = suscripciones.find(s => s.slug === props.slug);

        if (suscripcionEncontrada) {
          suscripcion.value = suscripcionEncontrada;
        } else {
          console.error('Suscripción no encontrada');
        }
      } catch (error) {
        console.error('Error al cargar la suscripción:', error);
      } finally {
        loading.value = false;
      }
    };

    //Inicializar Stripe
    const initializeStripe = async () => {
      //Cargar la clave pública de Stripe
      stripe = await loadStripe(import.meta.env.VITE_APP_STRIPE_PUBLIC_KEY);

      //Crear un elemento de tarjeta
      const elements = stripe.elements();
      cardElement = elements.create('card', {
        style: {
          base: {
            color: '#495057',
            fontFamily: 'system-ui, -apple-system, "Segoe UI", Roboto, sans-serif',
            fontSmoothing: 'antialiased',
            fontSize: '16px',
            '::placeholder': {
              color: '#6c757d'
            }
          },
          invalid: {
            color: '#dc3545',
            iconColor: '#dc3545'
          }
        }
      });

      //Montar el elemento de tarjeta
      cardElement.mount('#card-element');

      //Manejar errores de tarjeta
      cardElement.on('change', (event) => {
        const displayError = document.getElementById('card-errors');
        if (displayError) {
          displayError.textContent = event.error ? event.error.message : '';
        }
      });
    };

    //Inicializar Paypal
    const initializePayPal = () => {
      if (!window.paypal) {
        console.error('PayPal SDK not loaded');
        return;
      }

      //Boton de pago con PayPal
      window.paypal.Buttons({
        style: {
          layout: 'vertical',
          color: 'blue',
          shape: 'rect',
          label: 'paypal',
          tagline: false
        },
        fundingSource: window.paypal.FUNDING.PAYPAL,
        createOrder: async () => {
          try {
            processingPayment.value = true;
            //Llamar al backend para crear el pago
            const response = await PaymentService.initiatePayment('paypal', suscripcion.value.precio);
            return response.data.id; //Para PayPal, el id del pago es el id de la orden
          } catch (error) {
            console.error('Error creating PayPal order:', error);
            processingPayment.value = false;

            //Registrar la incidencia
            await store.dispatch(`incidencias/${Constant.CREATE_INCIDENCIA}`, {
              tipo: 'ERROR_CREACION_ORDEN_PAYPAL',
              descripcion: `Error al crear orden de PayPal: ${error.message || 'Error desconocido'}. Plan: ${suscripcion.value?.nombre || 'Desconocido'}`,
              metodoPago: 'paypal',
              referenciaExterna: 'error_creacion_orden'
            });

            showErrorToast();
            throw error;
          }
        },
        onApprove: async (data) => {
          try {
            //Llamar al backend para capturar el pago
            const captureResponse = await PaymentService.capturePayPalPayment(data.orderID);

            if (captureResponse.data.status === 'COMPLETED') {
              await savePagoToMainBackend('paypal', data.orderID);
              showSuccessToast();

              //Redirigir después de un breve retraso
              setTimeout(() => {
                router.push('/dashboard');
              }, 2000);
            } else {
              console.error('Payment not completed');

              //Registrar la incidencia
              await store.dispatch(`incidencias/${Constant.CREATE_INCIDENCIA}`, {
                tipo: 'ERROR_PAGO_INCOMPLETO_PAYPAL',
                descripcion: `El pago con PayPal no se completó. Estado: ${captureResponse.data.status}. Plan: ${suscripcion.value.nombre}`,
                metodoPago: 'paypal',
                referenciaExterna: data.orderID
              });

              showErrorToast();
            }
          } catch (error) {
            console.error('Error capturing PayPal payment:', error);

            //Registrar la incidencia
            await store.dispatch(`incidencias/${Constant.CREATE_INCIDENCIA}`, {
              tipo: 'ERROR_CAPTURA_PAGO_PAYPAL',
              descripcion: `Error al capturar pago de PayPal: ${error.message || 'Error desconocido'}. Plan: ${suscripcion.value.nombre}`,
              metodoPago: 'paypal',
              referenciaExterna: data.orderID
            });

            showErrorToast();
          } finally {
            processingPayment.value = false;
          }
        },
        onError: async (err) => {
          console.error('PayPal error:', err);
          processingPayment.value = false;

          //Registrar la incidencia
          await store.dispatch(`incidencias/${Constant.CREATE_INCIDENCIA}`, {
            tipo: 'ERROR_PAYPAL',
            descripcion: `Error en el proceso de pago con PayPal: ${err.message || 'Error desconocido'}. Plan: ${suscripcion.value.nombre}`,
            metodoPago: 'paypal',
            referenciaExterna: 'error_paypal'
          });

          showErrorToast();
        }
      }).render('#paypal-button-container');
    };

    const processStripePayment = async () => {
      if (!stripe || !cardElement) {
        console.error('Stripe not initialized');
        showErrorToast();
        return;
      }

      try {
        processingPayment.value = true;

        //Llamar al backend para obtener el cliente secreto
        const response = await PaymentService.initiatePayment('stripe', suscripcion.value.precio);
        const { clientSecret } = response.data;

        //Confirm payment
        const result = await stripe.confirmCardPayment(clientSecret, {
          payment_method: {
            card: cardElement,
            billing_details: {
              name: 'Cliente'
            }
          }
        });

        if (result.error) {
          console.error(result.error.message);

          //Registrar la incidencia
          await store.dispatch(`incidencias/${Constant.CREATE_INCIDENCIA}`, {
            tipo: 'ERROR_PAGO_STRIPE',
            descripcion: `Error al procesar pago con Stripe: ${result.error.message}. Plan: ${suscripcion.value.nombre}`,
            metodoPago: 'stripe',
            referenciaExterna: result.error.payment_intent?.id || 'unknown'
          });

          showErrorToast();
        } else if (result.paymentIntent.status === 'succeeded') {
          //Guardar pago en el backend principal
          await savePagoToMainBackend('stripe', result.paymentIntent.id);
          showSuccessToast();

          //Redirigir después de un breve retraso
          setTimeout(() => {
            router.push('/dashboard');
          }, 2000);
        }
      } catch (error) {
        console.error('Error processing Stripe payment:', error);

        //Registrar la incidencia
        await store.dispatch(`incidencias/${Constant.CREATE_INCIDENCIA}`, {
          tipo: 'ERROR_PAGO_STRIPE',
          descripcion: `Error en el proceso de pago con Stripe: ${error.message || 'Error desconocido'}. Plan: ${suscripcion.value?.nombre || 'Desconocido'}`,
          metodoPago: 'stripe',
          referenciaExterna: 'error_proceso'
        });

        showErrorToast();
      } finally {
        processingPayment.value = false;
      }
    };

    //Guardar pago en el backend principal
    const savePagoToMainBackend = async (metodoPago, referenciaExterna) => {
      try {
        await PaymentService.savePago({
          suscripcionSlug: suscripcion.value.slug,
          monto: suscripcion.value.precio,
          metodoPago: metodoPago,
          referenciaExterna: referenciaExterna
        });
      } catch (error) {
        console.error('Error saving payment to backend:', error);
        showErrorToast();
        throw error;
      }
    };

    const loadPaymentScripts = () => {
      const paypalScript = document.createElement('script');
      paypalScript.src = `https://www.paypal.com/sdk/js?client-id=${import.meta.env.VITE_APP_PAYPAL_CLIENT_ID}&currency=EUR&disable-funding=credit,card`;
      paypalScript.onload = () => initializePayPal();
      document.head.appendChild(paypalScript);
    };

    //Inicializar Toast
    const initializeToasts = () => {
      successToast = new Toast(document.getElementById('successToast'), {
        delay: 3000,
        autohide: true
      });

      errorToast = new Toast(document.getElementById('errorToast'), {
        delay: 5000,
        autohide: true
      });
    };

    //Observar cambios en el slug para recargar la suscripción
    watch(() => props.slug, (newSlug) => {
      if (newSlug) {
        cargarSuscripcion();
      }
    });

    onMounted(async () => {
      //Verificar acceso antes de cargar cualquier cosa
      if (!verificarAcceso()) {
        return;
      }

      //Cargar la suscripción
      await cargarSuscripcion();

      //Solo inicializar Stripe y PayPal si se cargó la suscripción
      if (suscripcion.value) {
        //Inicializar Stripe
        await initializeStripe();

        //Cargar PayPal Script
        loadPaymentScripts();
      }

      //Initialize toasts de Bootstrap
      initializeToasts();
    });

    return {
      loading,
      processingPayment,
      suscripcion,
      formatPrice,
      processStripePayment,
      volver
    };
  }
}
</script>


<style scoped>
#card-element:focus-within {
  border-color: #86b7fe;
  box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
}

.toast {
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}
</style>

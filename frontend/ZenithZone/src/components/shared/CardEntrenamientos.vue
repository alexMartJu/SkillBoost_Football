<template>
    <section class="mb-3">
        <div class="row g-3">
            <section class="col-md-12">
                <div class="card">
                    <div class="card-body d-flex justify-content-between">
                        <div>
                            <p><strong>Nombre:</strong> {{ entrenamiento.nombre }}</p>
                            <p><strong>Descripción:</strong> {{ entrenamiento.descripcion }}</p>
                            <p><strong>Duración:</strong> {{ entrenamiento.duracion }} minutos</p>
                            <p><strong>Plazas totales:</strong> {{ entrenamiento.maxPlazas }}</p>
                            <p><strong>Precio:</strong> {{ entrenamiento.precio }}€</p>
                        </div>
                        <div class="d-flex flex-column align-items-end justify-content-between">
                            <button class="btn btn-primary" @click="details(entrenamiento.slug)">
                                Más info
                            </button>
                            <button v-if="!isProfile" class="btn btn-success" @click="unirseEntrenamiento">
                                Apuntarse
                            </button>
                            <button v-if="isProfile" class="btn btn-danger" @click="cancelarEntrenamiento">
                                Cancelar
                            </button>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </section>
</template>

<script>
import profileService from '@/services/client/profile.service';
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';
import Swal from 'sweetalert2';
import Constant from '@/Constant';

export default {
    props: {
        entrenamiento: {
            type: Object,
            required: true
        },
        isProfile: {
            type: Boolean,
            required: true,
            default: false
        }
    },

    setup(props) {
        const store = useStore();
        const router = useRouter();

        const details = (slug) => {
            router.push({ name: 'detailsEntrenamiento', params: { slug } });
        }

        const cancelarEntrenamiento = async () => {
            const result = await Swal.fire({
                title: '¿Quieres dejar el entrenamiento?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí'
            });

            if (result.isConfirmed) {
                await profileService.CancelarEntrenamiento(props.entrenamiento.slug);
                Swal.fire(
                    'Cancelado',
                    'Tu entrenamiento ha sido cancelado.',
                    'success'
                );
                store.dispatch(`profile/${Constant.INITIALIZE_ENTRENAMIENTO_PROFILE}`);
            }
        }

        const unirseEntrenamiento = async () => {
            const result = await Swal.fire({
                title: '¿Quieres apuntarte a este entrenamiento?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí'
            });

            if (result.isConfirmed) {
                try {
                    await profileService.UnirseEntrenamiento(props.entrenamiento.slug);
                    Swal.fire(
                        'Unido',
                        'Te has apuntado al entrenamiento.',
                        'success'
                    );
                } catch (e) {
                    Swal.fire(
                        'Error',
                        'Ya estás apuntado al entrenamiento.',
                        'error'
                    );
                }

            }
        }

        return { details, cancelarEntrenamiento, unirseEntrenamiento }
    }
}
</script>

<style scoped>
.card {
    position: relative;
    overflow: hidden;
    /* Evitar que la imagen se desborde */
}

.card:hover {
    cursor: pointer;
    background-color: rgb(249, 250, 250);
}
</style>

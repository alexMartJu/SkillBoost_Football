<template>
    <v-dialog v-model="dialogVisible" max-width="600px">
        <v-card class="rounded-lg" style="background-color: white !important;">
            <v-card-title class="text-white bg-success d-flex align-items-center">
                <v-icon class="me-2">mdi-chart-line</v-icon>
                Evaluación de Rendimiento
                <v-spacer></v-spacer>
                <v-btn icon @click="closeDialog" color="white">
                    <v-icon>mdi-close</v-icon>
                </v-btn>
            </v-card-title>

            <v-card-text class="pa-4">
                <div v-if="loading" class="text-center my-4">
                    <v-progress-circular indeterminate color="success"></v-progress-circular>
                </div>

                <div v-else-if="!evaluacion" class="text-center my-4">
                    <v-icon size="64" color="grey">mdi-alert-circle-outline</v-icon>
                    <p class="mt-3 text-grey">No se encontró la evaluación</p>
                </div>

                <div v-else>
                    <div class="mb-4">
                        <h6 class="text-subtitle-2 text-grey">Entrenamiento</h6>
                        <h5 class="text-h6">{{ nombreEntrenamiento }}</h5>
                    </div>

                    <div class="mb-4">
                        <h6 class="text-subtitle-2 text-grey">Fecha de Evaluación</h6>
                        <p>{{ formatDate(evaluacion.fechaEvaluacion) }}</p>
                    </div>

                    <div class="mb-4">
                        <h6 class="text-subtitle-2 text-grey">Puntuación</h6>
                        <div class="d-flex align-items-center">
                            <v-progress-linear :value="evaluacion.puntuacion"
                                :color="getProgressBarColor(evaluacion.puntuacion)" height="10"
                                class="flex-grow-1 me-3"></v-progress-linear>
                            <span class="font-weight-bold" :class="getScoreTextClass(evaluacion.puntuacion)">
                                {{ evaluacion.puntuacion }}/100
                            </span>
                        </div>
                    </div>

                    <div class="mb-4">
                        <div class="d-flex align-items-center mb-2">
                            <v-icon color="info" class="me-2">mdi-comment-text-outline</v-icon>
                            <h6 class="text-subtitle-2 text-grey mb-0">Comentarios del Entrenador</h6>
                        </div>
                        <v-card class="comment-card pa-3" variant="outlined" color="info-lighten-5">
                            <p class="mb-0">{{ evaluacion.comentarios || 'Sin comentarios' }}</p>
                        </v-card>
                    </div>

                    <div>
                        <div class="d-flex align-items-center mb-2">
                            <v-icon color="warning" class="me-2">mdi-lightbulb-outline</v-icon>
                            <h6 class="text-subtitle-2 text-grey mb-0">Áreas de Mejora</h6>
                        </div>
                        <v-card class="improvement-card pa-3" variant="outlined" color="warning-lighten-5">
                            <p class="mb-0">{{ evaluacion.areasMejora || 'No se han especificado áreas de mejora' }}</p>
                        </v-card>
                    </div>
                </div>
            </v-card-text>

            <v-card-actions class="pa-4 pt-0">
                <v-spacer></v-spacer>
                <v-btn color="success" @click="closeDialog"><span class="button-close">Cerrar</span></v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import { ref, watch } from 'vue';

export default {
    name: 'EvaluacionRendimientoModal',
    props: {
        evaluacion: {
            type: Object,
            default: null
        },
        nombreEntrenamiento: {
            type: String,
            default: 'Entrenamiento'
        },
        loading: {
            type: Boolean,
            default: false
        },
        modelValue: {
            type: Boolean,
            default: false
        }
    },
    emits: ['update:modelValue'],
    setup(props, { emit }) {
        const dialogVisible = ref(false);

        // Sincronizar el estado del diálogo con el v-model
        watch(() => props.modelValue, (newValue) => {
            dialogVisible.value = newValue;
        });

        watch(dialogVisible, (newValue) => {
            emit('update:modelValue', newValue);
        });

        const formatDate = (dateString) => {
            if (!dateString) return 'Fecha no disponible';

            const date = new Date(dateString);
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const day = String(date.getDate()).padStart(2, '0');

            return `${year}-${month}-${day}`;
        };

        const getProgressBarColor = (score) => {
            if (score >= 90) return 'success';
            if (score >= 70) return 'info';
            if (score >= 50) return 'warning';
            return 'error';
        };

        const getScoreTextClass = (score) => {
            if (score >= 90) return 'text-success';
            if (score >= 70) return 'text-info';
            if (score >= 50) return 'text-warning';
            return 'text-error';
        };

        const closeDialog = () => {
            dialogVisible.value = false;
        };

        return {
            dialogVisible,
            formatDate,
            getProgressBarColor,
            getScoreTextClass,
            closeDialog
        };
    }
};
</script>

<style scoped>
/* Sobrescribir los estilos de la barra de progreso */
:deep(.v-progress-linear__background) {
    opacity: 1 !important;
}

:deep(.v-progress-linear__determinate) {
    opacity: 1 !important;
}

:deep(.v-progress-linear__indeterminate) {
    opacity: 1 !important;
}

/* Estilos para las tarjetas de comentarios y mejoras */
.comment-card {
    border-left: 4px solid #17a2b8;
    background-color: #e6f7fa;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.improvement-card {
    border-left: 4px solid #ffc107;
    background-color: #fff9e6 ;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.button-close{
    color: #000;
}
</style>
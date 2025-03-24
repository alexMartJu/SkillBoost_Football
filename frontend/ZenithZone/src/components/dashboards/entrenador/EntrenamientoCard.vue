<template>
    <div class="card h-100 shadow-sm border-0 entrenamiento-card">
        <!-- Indicador de estado -->
        <div class="card-status-indicator" :class="statusClass"></div>

        <!-- Imagen del entrenamiento -->
        <div class="card-img-container">
            <img v-if="entrenamiento.images && entrenamiento.images.length > 0" :src="entrenamiento.images[0].url"
                class="card-img-top" :alt="entrenamiento.nombre">
            <div v-else class="card-img-placeholder d-flex align-items-center justify-content-center bg-light">
                <i class="bi bi-image text-muted" style="font-size: 2rem;"></i>
            </div>
        </div>

        <!-- Contenido de la tarjeta -->
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-start mb-2">
                <h5 class="card-title mb-0 text-truncate">{{ entrenamiento.nombre }}</h5>
                <span class="badge" :class="badgeClass">{{ statusText }}</span>
            </div>

            <p class="card-text small text-muted mb-3">{{ truncateText(entrenamiento.descripcion, 100) }}</p>

            <div class="details-list">
                <div class="detail-item">
                    <i class="bi bi-geo-alt me-2 text-primary"></i>
                    <span>{{ entrenamiento.horarioPista?.pista?.nombre || 'Sin pista asignada' }}</span>
                </div>
                <div class="detail-item">
                    <i class="bi bi-calendar-event me-2 text-primary"></i>
                    <span>{{ formatDate(entrenamiento.horarioPista?.fechaInicio) }}</span>
                </div>
                <div class="detail-item">
                    <i class="bi bi-clock me-2 text-primary"></i>
                    <span>{{ formatTime(entrenamiento.horarioPista?.fechaInicio) }} - {{
                        formatTime(entrenamiento.horarioPista?.fechaFin) }}</span>
                </div>
                <div class="detail-item">
                    <i class="bi bi-people me-2 text-primary"></i>
                    <span>{{ entrenamiento.maxPlazas }} plazas</span>
                </div>
            </div>
        </div>

        <!-- Pie de la tarjeta -->
        <div class="card-footer bg-white border-0 pt-0">
            <div class="d-flex justify-content-between align-items-center">
                <div class="tecnificacion-badge">
                    <span class="badge bg-light text-dark">
                        {{ entrenamiento.tecnificacion?.nombre }}
                    </span>
                    <span class="badge bg-light text-dark ms-1">
                        {{ entrenamiento.subtipoTecnificacion?.nombre }}
                    </span>
                </div>

                <div class="d-flex gap-2">
                    <button class="btn btn-sm btn-outline-primary" @click="verDetalles">
                        <i class="bi bi-eye me-1"></i> Ver detalles
                    </button>
                    <button v-if="estado === 'approved'" class="btn btn-sm btn-primary" @click="verAlumnos">
                        <i class="bi bi-people-fill me-1"></i> Alumnos
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { format } from 'date-fns';
import { es } from 'date-fns/locale';

export default {
    name: 'EntrenamientoCard',
    props: {
        entrenamiento: {
            type: Object,
            required: true
        },
        estado: {
            type: String,
            required: true
        }
    },
    computed: {
        statusClass() {
            return {
                'status-pending': this.estado === 'pending',
                'status-approved': this.estado === 'approved',
                'status-denied': this.estado === 'denied'
            };
        },
        badgeClass() {
            return {
                'bg-warning': this.estado === 'pending',
                'bg-success': this.estado === 'approved',
                'bg-danger': this.estado === 'denied'
            };
        },
        statusText() {
            const statusMap = {
                'pending': 'Pendiente',
                'approved': 'Aprobado',
                'denied': 'Denegado',
                'active': 'Activo',
                'completed': 'Completado'
            };
            return statusMap[this.estado] || 'Desconocido';
        }
    },
    methods: {
        formatDate(dateString) {
            if (!dateString) return 'Fecha no disponible';
            try {
                const date = new Date(dateString);
                return format(date, 'dd MMM yyyy', { locale: es });
            } catch (error) {
                console.error('Error al formatear fecha:', error);
                return 'Fecha inválida';
            }
        },
        formatTime(dateString) {
            if (!dateString) return '--:--';
            try {
                const date = new Date(dateString);
                return format(date, 'HH:mm');
            } catch (error) {
                console.error('Error al formatear hora:', error);
                return '--:--';
            }
        },
        verDetalles() {
            this.$router.push({
                name: 'detailsEntrenamiento',
                params: { slug: this.entrenamiento.slug }
            });
        },
        verAlumnos() {
            this.$router.push({
                name: 'entrenadorAlumnos',
                params: { slug: this.entrenamiento.slug }
            });
        },
        truncateText(text, length) {
            if (!text) return '';
            if (text.length <= length) return text;
            return text.substring(0, length) + '...';
        }
    }
};
</script>

<style scoped>
.entrenamiento-card {
    transition: transform 0.2s, box-shadow 0.2s;
    position: relative;
    overflow: hidden;
}

.entrenamiento-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
}

.card-status-indicator {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 4px;
}

.status-pending {
    background-color: var(--bs-warning);
}

.status-approved {
    background-color: var(--bs-success);
}

.status-denied {
    background-color: var(--bs-danger);
}

.card-img-container {
    height: 160px;
    overflow: hidden;
}

.card-img-top {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.card-img-placeholder {
    height: 100%;
}

.details-list {
    margin-bottom: 1rem;
}

.detail-item {
    display: flex;
    align-items: center;
    margin-bottom: 0.5rem;
    font-size: 0.875rem;
}

.tecnificacion-badge {
    font-size: 0.75rem;
}

.card-footer {
    padding-top: 0.75rem;
    padding-bottom: 0.75rem;
}
</style>
<template>
    <div class="container mt-5">
      <!-- Player Profile (Football Card Style) -->
      <div v-if="!isCoach" class="row g-0">
        <div class="col-lg-5">
          <div class="player-card shadow">
            <div class="player-card-header">
              <div class="position-badge" v-if="profile.posicionPreferida">{{ profile.posicionPreferida }}</div>
              <div class="club-badge" v-if="profile.clubOrigen">
                <span>{{ profile.clubOrigen }}</span>
              </div>
            </div>
            <div class="player-image-container">
              <img :src="profile.image" alt="Perfil" class="player-image">
            </div>
            <div class="player-info text-center">
              <h2 class="player-name">{{ profile.nombre }} {{ profile.apellidos }}</h2>
              <div class="player-number">{{ profile.numeroSocio }}</div>
            </div>
          </div>
        </div>
        
        <div class="col-lg-7">
          <div class="player-details ms-lg-4 mt-4 mt-lg-0">
            <div class="card shadow-sm border-0 h-100">
              <div class="card-body p-4">
                <div class="d-flex justify-content-between align-items-start mb-4">
                  <h3 class="text-primary fw-bold mb-0">Información Personal</h3>
                  <button v-if="isCurrentUser" class="btn btn-sm btn-outline-primary" @click="redirects.edit">
                    <i class="bi bi-pencil-square me-1"></i> Editar
                  </button>
                </div>
                
                <div class="row g-3">
                  <div class="col-md-6">
                    <div class="info-item">
                      <span class="info-label">Edad</span>
                      <span class="info-value">{{ profile.edad }} años</span>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="info-item">
                      <span class="info-label">Posición</span>
                      <span class="info-value">{{ profile.posicionPreferida || 'No especificada' }}</span>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="info-item">
                      <span class="info-label">Club</span>
                      <span class="info-value">{{ profile.clubOrigen || 'No especificado' }}</span>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="info-item">
                      <span class="info-label">Entrenamientos</span>
                      <span class="info-value">{{ profile.entrenamientosDisponibles || 0 }}</span>
                    </div>
                  </div>
                  <div class="col-12">
                    <div class="info-item">
                      <span class="info-label">Biografía</span>
                      <p class="info-value bio-text">{{ profile.bio || 'Sin biografía' }}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Coach Profile -->
      <div v-else class="coach-profile">
        <div class="card border-0 shadow-lg">
          <div class="row g-0">
            <div class="col-md-4">
              <div class="coach-image-container h-100 d-flex align-items-center justify-content-center p-4">
                <img :src="profile.image" alt="Entrenador" class="coach-image">
              </div>
            </div>
            <div class="col-md-8">
              <div class="card-body p-4 p-md-5">
                <div class="d-flex justify-content-between align-items-start mb-4">
                  <div>
                    <h1 class="coach-name">{{ profile.nombre }} {{ profile.apellidos }}</h1>
                    <div class="coach-title">Entrenador <span class="badge bg-primary ms-2">{{ profile.especialidad }}</span></div>
                  </div>
                  <button v-if="isCurrentUser" class="btn btn-outline-primary" @click="redirects.editEntrenador">
                    <i class="bi bi-pencil-square me-2"></i>Editar Perfil
                  </button>
                </div>
  
                <div class="coach-number mb-4">
                  <span class="label">Nº Entrenador:</span>
                  <span class="value">{{ profile.numeroEntrenador }}</span>
                </div>
  
                <div class="coach-bio mb-4">
                  <p>{{ profile.bio || 'Sin biografía' }}</p>
                </div>
  
                <div class="row g-3">
                  <div class="col-md-6">
                    <div class="coach-stat">
                      <div class="stat-label">Experiencia</div>
                      <div class="stat-value">{{ profile.experienciaAnios }} años</div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="coach-stat">
                      <div class="stat-label">Edad</div>
                      <div class="stat-value">{{ profile.edad }} años</div>
                    </div>
                  </div>
                  <div class="col-12">
                    <div class="coach-stat">
                      <div class="stat-label">Certificaciones</div>
                      <div class="stat-value">{{ profile.certificaciones || 'No especificadas' }}</div>
                    </div>
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
  import router from '@/router/router';
  
  export default {
    props: {
      profile: {
        type: Object,
        required: true,
      },
      isCurrentUser: {
        type: Boolean,
        required: true
      }
    },
  
    setup(props) {
      const redirects = {
        edit: () => router.push({ name: 'profileEdit' }),
        editEntrenador: () => router.push({ name: 'profileEntrenadorEdit' }),
      };
  
      const isCoach = !!props.profile.numeroEntrenador;
  
      return { redirects, isCoach }
    }
  }
  </script>
  
  <style scoped>
  /* Common Styles */
  .info-item {
    margin-bottom: 1rem;
  }
  
  .info-label {
    display: block;
    font-size: 0.85rem;
    color: #6c757d;
    font-weight: 500;
    margin-bottom: 0.25rem;
  }
  
  .info-value {
    font-size: 1.1rem;
    font-weight: 500;
    color: #343a40;
  }
  
  .bio-text {
    line-height: 1.6;
  }
  
  /* Player Card Styles */
  .player-card {
    background: linear-gradient(135deg, #ffffff 0%, #f8f9fa 100%);
    border-radius: 16px;
    overflow: hidden;
    position: relative;
    height: 100%;
    border: 1px solid rgba(0,0,0,0.05);
    transition: transform 0.3s ease;
  }
  
  .player-card:hover {
    transform: translateY(-5px);
  }
  
  .player-card-header {
    padding: 1rem;
    position: relative;
    background: linear-gradient(135deg, #0d6efd 0%, #0a58ca 100%);
    height: 60px;
  }
  
  .position-badge {
    position: absolute;
    top: 1rem;
    left: 1rem;
    background-color: rgba(255, 255, 255, 0.9);
    color: #0d6efd;
    font-weight: bold;
    padding: 0.25rem 0.75rem;
    border-radius: 20px;
    font-size: 0.9rem;
  }
  
  .club-badge {
    position: absolute;
    top: 1rem;
    right: 1rem;
    background-color: rgba(255, 255, 255, 0.9);
    color: #0d6efd;
    font-weight: bold;
    padding: 0.25rem 0.75rem;
    border-radius: 20px;
    font-size: 0.9rem;
  }
  
  .player-image-container {
    padding: 1.5rem;
    display: flex;
    justify-content: center;
    background-color: #f8f9fa;
  }
  
  .player-image {
    width: 200px;
    height: 200px;
    object-fit: cover;
    border-radius: 50%;
    border: 5px solid white;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  }
  
  .player-info {
    padding: 1.5rem;
    background-color: white;
  }
  
  .player-name {
    font-size: 1.5rem;
    font-weight: 700;
    margin-bottom: 0.5rem;
    color: #212529;
  }
  
  .player-number {
    font-size: 1.1rem;
    color: #0d6efd;
    font-weight: 600;
  }
  
  .player-details {
    height: 100%;
  }
  
  /* Coach Profile Styles */
  .coach-profile {
    margin-bottom: 2rem;
  }
  
  .coach-image-container {
    background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
  }
  
  .coach-image {
    width: 220px;
    height: 220px;
    object-fit: cover;
    border-radius: 50%;
    border: 5px solid white;
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s ease;
  }
  
  .coach-image:hover {
    transform: scale(1.05);
  }
  
  .coach-name {
    font-size: 2.2rem;
    font-weight: 700;
    color: #212529;
    margin-bottom: 0.25rem;
  }
  
  .coach-title {
    font-size: 1.2rem;
    color: #6c757d;
    margin-bottom: 1.5rem;
  }
  
  .coach-number {
    display: inline-block;
    padding: 0.5rem 1rem;
    background-color: #f8f9fa;
    border-radius: 8px;
  }
  
  .coach-number .label {
    font-weight: 600;
    color: #6c757d;
    margin-right: 0.5rem;
  }
  
  .coach-number .value {
    color: #0d6efd;
    font-weight: 600;
  }
  
  .coach-bio {
    font-size: 1.1rem;
    line-height: 1.6;
    color: #495057;
  }
  
  .coach-stat {
    background-color: #f8f9fa;
    padding: 1rem;
    border-radius: 8px;
    height: 100%;
  }
  
  .stat-label {
    font-size: 0.9rem;
    color: #6c757d;
    margin-bottom: 0.5rem;
    font-weight: 500;
  }
  
  .stat-value {
    font-size: 1.2rem;
    font-weight: 600;
    color: #212529;
  }
  </style>
  
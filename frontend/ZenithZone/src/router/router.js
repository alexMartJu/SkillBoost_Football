import { createRouter, createWebHistory } from 'vue-router';
import AuthGuards from '../services/guards/AuthGuard';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // HOME
    {
      path: "/",
      redirect: { name: "home" }
    },
    {
      path: "/home",
      name: "home",
      component: () => import('../views/client/Home.vue')
    },

    // RECURSOS
    {
      path: "/recursos",
      component: () => import('../views/client/Recursos.vue'),
      children: [
        {
          path: "",
          name: "recursos",
          redirect: "/recursos/subtipos"
        },
        {
          path: "subtipos",
          name: "recursosSubtipos",
          component: () => import('../components/recursos/recursosSubtiposTecnificacion.vue'),
          children: [
            {
              path: ":slug",
              name: "TecnificacionSlug",
              component: () => import('../components/recursos/recursosSubtiposTecnificacion.vue')
            }
          ]
        },
        {
          path: "pistas",
          name: "recursosPistas",
          component: () => import('../components/recursos/recursosPistas.vue')
        },
        {
          path: "entrenadores",
          name: "recursosEntrenadores",
          component: () => import('../components/recursos/recursosEntrenadores.vue')
        }
      ]
    },

    // ENTRENA
    {
      path: "/entrena",
      name: "entrena",
      component: () => import('../views/client/Entrena.vue'),
    },
    // Ruta específica para filtros de entrenamientos
    {
      path: "/entrena/:filters",
      name: "entrenaFilter",
      component: () => import('../views/client/Entrena.vue'),
    },

    // DETAILS
    {
      path: "/entrenamiento/:slug",
      name: "detailsEntrenamiento",
      component: () => import('../views/client/Details.vue')
    },
    {
      path: "/pista/:slug",
      name: "detailsPista",
      component: () => import('../views/client/Details.vue')
    },
    {
      path: "/subtipo/:slug",
      name: "detailsSubtipoTecnificacion",
      component: () => import('../views/client/Details.vue')
    },

    //APOYO
    {
      path: "/apoyo",
      name: "apoyo",
      component: () => import('../views/client/Apoyo.vue')
    },
    {
      path: "/apoyo/:slug",
      name: "organizacionDetail",
      component: () => import('../components/apoyo/OrganizacionDetail.vue'),
      props: true
    },

    //PLANES
    {
      path: "/planes",
      component: () => import('../views/client/Planes.vue'),
      children: [
        {
          path: "",
          name: "planes",
          component: () => import('../components/planes/PlanesList.vue')
        },
        {
          path: "pago/:slug",
          name: "procesosPago",
          component: () => import('../components/pagos/ProcesoPago.vue'),
          props: true,
          beforeEnter: AuthGuards.paymentGuard
        }
      ]
    },

    // LOGIN
    {
      path: "/login",
      name: "login",
      component: () => import('../views/client/Login.vue'),
      beforeEnter: AuthGuards.noAuthGuard, meta: { requiresAuth: true }
    },
    {
      path: "/register",
      name: "register",
      component: () => import('../views/client/Register.vue'),
      beforeEnter: AuthGuards.noAuthGuard, meta: { requiresAuth: true }
    },

    // PROFILE
    {
      path: '/profile/:numeroSocio',
      name: 'profile',
      component: () => import('@/views/client/ProfileView.vue'),
      meta: {
        title: 'Perfil de Jugador',
        public: true // Accesible sin estar logueado
      }
    },
    {
      path: '/entrenador/:numeroentrenador',
      name: 'profileEntrenador',
      component: () => import('@/views/client/ProfileView.vue'),
      meta: {
        title: 'Perfil de Entrenador',
        public: true // Accesible sin estar logueado
      }
    },

    // DASHBOARD ENTRENADOR
    {
      path: "/dashboardEntrenador",
      name: "DashboardEntrenador",
      component: () => import('../views/dashboards/EntrenadorDashboard.vue'),
      beforeEnter: AuthGuards.authGuardEntrenador, 
      meta: { requiresAuth: true },
      redirect: { name: 'entrenadorMisEntrenamientos' },
      children: [
        {
          path: "listar",
          name: "entrenadorListarPistas",
          component: () => import('../components/dashboards/entrenador/ListPistas.vue'),
          beforeEnter: AuthGuards.authGuardEntrenador
        },
        {
          path: "horarios-pista",
          name: "entrenadorHorariosPista",
          component: () => import('../components/dashboards/entrenador/HorarioPista.vue'),
          beforeEnter: AuthGuards.authGuardEntrenador
        },
        {
          path: "crear-entrenamiento",
          name: "entrenadorCrearEntrenamiento",
          component: () => import('../components/dashboards/entrenador/CreateEntrenamientoDashboard.vue'),
          beforeEnter: AuthGuards.authGuardEntrenador,
          props: true
        },
        {
          path: "mis-entrenamientos",
          name: "entrenadorMisEntrenamientos",
          component: () => import('../components/dashboards/entrenador/MisEntrenamientos.vue'),
          beforeEnter: AuthGuards.authGuardEntrenador
        },
        {
          path: "mi-calendario",
          name: "entrenadorMiCalendario",
          component: () => import('../components/dashboards/entrenador/MiCalendario.vue'),
          beforeEnter: AuthGuards.authGuardEntrenador
        },
        {
          path: "entrenamientos/:slug/alumnos",
          name: "entrenadorAlumnos",
          component: () => import('../components/dashboards/entrenador/AlumnosEntrenamiento.vue'),
          beforeEnter: AuthGuards.authGuardEntrenador,
          props: true
        },
        {
          path: "alumnos/:numeroSocio/evaluacion/:slug",
          name: "entrenadorEvaluacionAlumno",
          component: () => import('../components/dashboards/entrenador/EvaluacionAlumno.vue'),
          beforeEnter: AuthGuards.authGuardEntrenador,
          props: true
        },
        {
          path: "alumnos/:numeroSocio/graficas",
          name: "entrenadorGraficasAlumno",
          component: () => import('../components/dashboards/entrenador/GraficasAlumno.vue'),
          beforeEnter: AuthGuards.authGuardEntrenador,
          props: true
        },
      ]
    },

    // DASHBOARD ADMIN
    {
      path: "/dashboardAdmin",
      name: "DashboardAdmin",
      component: () => import('../views/dashboards/AdminDashboard.vue'),
      beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true },
      redirect: { name: 'adminEntrenamientosPendientes' },
      children: [
        {
          path: "tecnificaciones",
          name: "adminTecnificaciones",
          component: () => import('../components/dashboards/admin/tecnificaciones/ListTecnificaciones.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "tecnificaciones/crear",
          name: "adminCrearTecnificacion",
          component: () => import('../components/dashboards/admin/tecnificaciones/FormTecnificaciones.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "tecnificaciones/editar/:slug",
          name: "adminEditarTecnificacion",
          component: () => import('../components/dashboards/admin/tecnificaciones/FormTecnificaciones.vue'),
          beforeEnter: AuthGuards.authGuardAdmin,
          props: true
        },
        {
          path: "subtipos-tecnificaciones",
          name: "adminSubtiposTecnificaciones",
          component: () => import('../components/dashboards/admin/subtiposTecnificacion/ListSubtiposTecnificaciones.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "subtipos-tecnificaciones/crear",
          name: "adminCrearSubtipoTecnificacion",
          component: () => import('../components/dashboards/admin/subtiposTecnificacion/FormSubtiposTecnificaciones.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "subtipos-tecnificaciones/editar/:slug",
          name: "adminEditarSubtipoTecnificacion",
          component: () => import('../components/dashboards/admin/subtiposTecnificacion/FormSubtiposTecnificaciones.vue'),
          beforeEnter: AuthGuards.authGuardAdmin,
          props: true
        },
        {
          path: "pistas",
          name: "adminPistas",
          component: () => import('../components/dashboards/admin/pistas/ListPistas.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "pistas/crear",
          name: "adminCrearPista",
          component: () => import('../components/dashboards/admin/pistas/FormPistas.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "pistas/editar/:slug",
          name: "adminEditarPista",
          component: () => import('../components/dashboards/admin/pistas/FormPistas.vue'),
          beforeEnter: AuthGuards.authGuardAdmin,
          props: true
        },
        {
          path: "usuarios",
          name: "adminUsuarios",
          component: () => import('../components/dashboards/admin/usuarios/ListUsuarios.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "usuarios/tutores",
          name: "adminTutores",
          component: () => import('../components/dashboards/admin/usuarios/ListTutores.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "usuarios/jugadores-club",
          name: "adminJugadoresClub",
          component: () => import('../components/dashboards/admin/usuarios/ListJugadoresClub.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "usuarios/jugadores",
          name: "adminJugadores",
          component: () => import('../components/dashboards/admin/usuarios/ListJugadores.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "usuarios/jugadores-sociales",
          name: "adminJugadoresSociales",
          component: () => import('../components/dashboards/admin/usuarios/ListJugadoresSociales.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "suscripciones",
          name: "adminSuscripciones",
          component: () => import('../components/dashboards/admin/suscripciones/ListSuscripciones.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "organizaciones",
          name: "adminOrganizaciones",
          component: () => import('../components/dashboards/admin/organizaciones/ListOrganizaciones.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "organizaciones/crear",
          name: "adminCrearOrganizacion",
          component: () => import('../components/dashboards/admin/organizaciones/FormOrganizaciones.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "usuarios/registro",
          name: "adminRegistroUsuarios",
          component: () => import('../components/dashboards/admin/usuarios/RegisterUsers.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "entrenadores",
          name: "adminEntrenadores",
          component: () => import('../components/dashboards/admin/entrenadores/ListEntrenadores.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "entrenamientos-pendientes",
          name: "adminEntrenamientosPendientes",
          component: () => import('../components/dashboards/admin/entrenamientos/PendingEntrenamientos.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        }

      ]
    },
    {
      path: "/:pathMatch(.*)*", //Ruta para cualquier ruta que no esté definida
      redirect: "/",
    },
  ],
  scrollBehavior(to, from, savedPosition) { // Para que al cambiar de vista, la página se desplace a la parte superior
    return { 
      top: 0,
      behavior: 'instant'
    }
  }
})

export default router
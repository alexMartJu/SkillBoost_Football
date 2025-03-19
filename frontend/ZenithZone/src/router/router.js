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
      path: "/profile/:numeroSocio",
      name: "profile",
      component: () => import('../views/client/Profile.vue'),
      children: [
        {
          path: "info",
          name: "profileInfo",
          component: () => import('../views/client/Profile.vue'),
        },
        {
          path: "graficas",
          name: "profileGraficas",
          component: () => import('../components/profile/ProfileGraficas.vue'),
        },
        {
          path: "reservas",
          name: "profileReservas",
          component: () => import('../components/profile/ProfileReservas.vue'),
        },
        {
          path: "entrenamientos",
          name: "profileEntrenamientos",
          component: () => import('../components/profile/ProfileEntrenamientos.vue'),
        },
      ]
    },
    // Editar perfil. Se pone aparte para que no salga como una pestaña en el perfil
    {
      path: "/profile/:numeroSocio/editar",
      name: "profileEdit",
      component: () => import('../components/profile/ProfileEdit.vue'),
    },

    // Profile Entrenador
    {
      path: "/entrenador/:numeroentrenador",
      name: "profileEntrenador",
      component: () => import('../views/client/Profile.vue'),
      children: [
        {
          path: "info",
          name: "profileEntrenadorInfo",
          component: () => import('../components/entrenador/Profile.vue'),
        },
      ]
    },
    // Editar perfil. Se pone aparte para que no salga como una pestaña en el perfil
    {
      path: "/entrenador/:numeroentrenador/editar",
      name: "profileEntrenadorEdit",
      component: () => import('../components/profile/ProfileEdit.vue'),
    },

    // DASHBOARD ENTRENADOR
    {
      path: "/dashboardEntrenador",
      name: "DashboardEntrenador",
      component: () => import('../views/dashboards/EntrenadorDashboard.vue'),  // Vista principal del entrenador
      beforeEnter: AuthGuards.authGuardEntrenador, meta: { requiresAuth: true },
      redirect: { name: 'entrenadorListarEntrenamientos' },
      children: [
        {
          path: "listar",  // Si está vacío, se mostrará por defecto la vista de Listar Entrenamientos
          name: "entrenadorListarEntrenamientos",
          component: () => import('../components/dashboards/entrenador/ListEntrenamientosDashboard.vue'),  // Vista para listar entrenamientos
          beforeEnter: AuthGuards.authGuardEntrenador
        },
        {
          path: "crear-entrenamiento",
          name: "entrenadorCrearEntrenamiento",
          component: () => import('../components/dashboards/entrenador/CreateEntrenamientoDashboard.vue'),  // Vista para crear un nuevo entrenamiento
          beforeEnter: AuthGuards.authGuardEntrenador
        },
        {
          path: '/alumno/:profileId',
          name: "AlumnoDetail",
          component: () => import('../components/dashboards/entrenador/AlumnoDetail.vue'),  // Vista para crear un nuevo entrenamiento
          beforeEnter: AuthGuards.authGuardEntrenador
        },

      ]
    },

    // DASHBOARD ADMIN
    {
      path: "/dashboardAdmin",
      name: "DashboardAdmin",
      component: () => import('../views/dashboards/AdminDashboard.vue'),
      beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true },
      redirect: { name: 'adminListar' },
      children: [
        {
          path: "listar",
          name: "adminListar",
          component: () => import('../components/dashboards/admin/ListarAdmin.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "crear",
          name: "adminCrear",
          component: () => import('../components/dashboards/admin/CrearAdmin.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "entrenador/register",
          name: "RegisterTrainer",
          component: () => import('../components/dashboards/admin/RegisterTrainer.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: "entrenador",
          name: "ListEntrenadores",
          component: () => import('../components/dashboards/admin/ListEntrenadores.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: 'editar/:type/:slug',
          name: 'adminEditar',
          component: () => import('../components/dashboards/admin/CrearAdmin.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: 'entrenamientos',
          name: 'ListEntrenamientos',
          component: () => import('../components/dashboards/admin/ListEntrenamientos.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: 'salas',
          name: 'ListCreateSalas',
          component: () => import('../components/dashboards/admin/salas.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: 'pistasprivadas',
          name: 'ListCreatepistasprivadas',
          component: () => import('../components/dashboards/admin/pistasprivadas.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },
        {
          path: 'Reservas',
          name: 'Reservas',
          component: () => import('../components/dashboards/admin/Reservas.vue'),
          beforeEnter: AuthGuards.authGuardAdmin
        },

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
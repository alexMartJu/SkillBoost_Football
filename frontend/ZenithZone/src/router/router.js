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

    // INSTALACIONES
    {
      path: "/instalaciones",
      name: "instalaciones",
      component: () => import('../views/client/Instalaciones.vue'),
      children: [
        {
          path: ":slug",
          name: "InstalacionesSlug",
          component: () => import('../views/client/Instalaciones.vue')
        }
      ]
    },

    // SERVICIOS
    {
      path: "/servicios",
      name: "servicios",
      component: () => import('../views/client/Servicios.vue'),
      children: [
        {
          path: "deportes",
          name: "serviciosDeportes",
          component: () => import('../components/servicios/serviciosDeportes.vue')
        },
        {
          path: "entrenamientos",
          name: "serviciosEntrenamientos",
          component: () => import('../components/servicios/serviciosEntrenamientos.vue'),
          children: [
            {
              path: ":filters",
              name: "serviciosEntrenamientosFilter",
              component: () => import('../components/servicios/serviciosEntrenamientos.vue'),
            },
            {
              path: "details/:slug",
              name: "detailsEntrenamiento",
              component: () => import('../components/servicios/detailsEntrenamiento.vue')
            }
          ]
        },
        {
          path: "graficas",
          name: "serviciosGraficas",
          component: () => import('../components/servicios/serviciosGraficas.vue')
        },
      ]
    },

    // ENTRENADORES
    {
      path: "/entrenadores",
      name: "entrenadores",
      component: () => import('../views/client/Entrenadores.vue')
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
          path: "/",
          name: "profileInfo",
          component: () => import('../components/profile/Profile.vue'),
        },
        {
          path: "/editar",
          name: "profileEdit",
          component: () => import('../components/profile/ProfileEdit.vue'),
        },
        {
          path: "/graficas",
          name: "profileGraficas",
          component: () => import('../components/profile/Graficas.vue'),
        },
        {
          path: "/reservas",
          name: "profileReservas",
          component: () => import('../components/profile/ProfileReservas.vue'),
        },
        {
          path: "/entrenamientos",
          name: "profileEntrenamientos",
          component: () => import('../components/profile/ProfileEntrenamientos.vue'),
        },
      ]
    },

    // Profile Entrenador
    {
      path: "/entrenador/:numeroEntrenador",
      name: "profileEntrenador",
      component: () => import('../views/client/ProfileEntrenador.vue'),
      children: [
        {
          path: "/",
          name: "entrenadorInfo",
          component: () => import('../components/entrenador/Profile.vue'),
        },
        {
          path: "/editar",
          name: "profileEdit",
          component: () => import('../components/entrenador/ProfileEdit.vue'),
        },
      ]
    },

    // DASHBOARD ENTRENADOR
    {
      path: "/dashboardEntrenador",
      name: "DashboardEntrenador",
      component: () => import('../views/dashboards/EntrenadorDashboard.vue'),  // Vista principal del entrenador
      beforeEnter: AuthGuards.authGuardEntrenador, meta: { requiresAuth: true },
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
        }
      ]
    },

    // DASHBOARD ADMIN
    {
      path: "/dashboardAdmin",
      name: "DashboardAdmin",
      component: () => import('../views/dashboards/AdminDashboard.vue'),
      beforeEnter: AuthGuards.authGuardAdmin, meta: { requiresAuth: true },
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

      ]
    },
  ]
})

export default router
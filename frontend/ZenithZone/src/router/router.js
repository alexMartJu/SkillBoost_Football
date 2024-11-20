import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: { name: "home" }
    },
    {
      path: "/home",
      name: "home",
      component: () => import('../views/client/Home.vue')
    },
    {
      path: "/instalaciones",
      name: "instalaciones",
      component: () => import('../views/client/Instalaciones.vue')
    },
    {
      path: "/entrenadorDashboard",
      name: "entrenadorDashboard",
      component: () => import('../views/dashboards/EntrenadorDashboard.vue'),  // Vista principal del entrenador
      children: [
        {
          path: "",  // Si está vacío, se mostrará por defecto la vista de Listar Entrenamientos
          name: "entrenadorListarEntrenamientos",
          component: () => import('../components/dashboards/entrenador/ListEntrenamientosDashboard.vue'),  // Vista para listar entrenamientos
        },
        {
          path: "crear-entrenamiento",
          name: "entrenadorCrearEntrenamiento",
          component: () => import('../components/dashboards/entrenador/CreateEntrenamientoDashboard.vue'),  // Vista para crear un nuevo entrenamiento
        }
      ]
    },
    {
      path: "/admin",
      name: "adminDashboard",
      component: () => import('../views/dashboards/AdminDashboard.vue')
    },
  ]
})

export default router
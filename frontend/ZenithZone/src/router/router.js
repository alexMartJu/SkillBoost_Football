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
      path: "/monitorDashboard",
      name: "monitorDashboard",
      component: () => import('../views/dashboards/MonitorDashboard.vue'),  // Vista principal del monitor
      children: [
        {
          path: "",  // Si está vacío, se mostrará por defecto la vista de Listar Clases
          name: "monitorListarClases",
          component: () => import('../components/dashboards/ListClasesDashboard.vue'),  // Vista para listar clases
        },
        {
          path: "crear-clase",
          name: "monitorCrearClase",
          component: () => import('../components/dashboards/CreateClaseDashboard.vue'),  // Vista para crear una nueva clase
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
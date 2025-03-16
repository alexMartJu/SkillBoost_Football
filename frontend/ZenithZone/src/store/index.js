import Vuex from "vuex";
import { deportes } from "./modules/client/deportes";
import { adminDashboard } from './modules/dashboards/adminDashboard';
import { entrenamientos } from "./modules/client/entrenamientos";
import { pistas } from "./modules/client/pistas";
import { entrenadorDashboard } from "./modules/dashboards/entrenadorDashboard";
import { user } from "./modules/client/user";
import { profile } from "./modules/client/profile";
import { entrenadores } from "./modules/client/entrenadores";
import { reservas } from "./modules/client/reservas";
import { tecnificaciones } from "./modules/client/tecnificaciones";
import { subtiposTecnificacion } from "./modules/client/subtiposTecnificacion";

export default Vuex.createStore({
    modules: {
        deportes: deportes,
        adminDashboard: adminDashboard,
        entrenamientos: entrenamientos,
        pistas: pistas,
        entrenadorDashboard: entrenadorDashboard,
        user: user,
        profile: profile,
        entrenadores: entrenadores,
        reservas: reservas,
        tecnificaciones: tecnificaciones, 
        subtiposTecnificacion: subtiposTecnificacion
    }
});
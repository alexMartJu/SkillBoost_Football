import Vuex from "vuex";
import { deportes } from "./modules/client/deportes";
import { adminDashboard } from './modules/dashboards/adminDashboard';
import { entrenamientos } from "./modules/client/entrenamientos";
import { pistas } from "./modules/client/pistas";
import { entrenadorDashboard } from "./modules/dashboards/entrenadorDashboard";

export default Vuex.createStore({
    modules: {
        deportes: deportes,
        adminDashboard: adminDashboard,
        entrenamientos: entrenamientos,
        pistas: pistas,
        entrenadorDashboard: entrenadorDashboard
    }
});
import Vuex from "vuex";
import { deportes } from "./modules/client/deportes";
import { adminDashboard } from './modules/dashboards/adminDashboard';
import { clases } from "./modules/client/clases";
import { pistas } from "./modules/client/pistas";
import { monitorDashboard } from "./modules/dashboards/monitorDashboard";

export default Vuex.createStore({
    modules: {
        deportes: deportes,
        adminDashboard: adminDashboard,
        clases: clases,
        pistas: pistas,
        monitorDashboard: monitorDashboard
    }
});
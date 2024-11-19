import Vuex from "vuex";
import { deportes } from "./modules/client/deportes";
import { adminDashboard } from './modules/dashboards/adminDashboard';

export default Vuex.createStore({
    modules: {
        deportes: deportes,
        adminDashboard: adminDashboard
    }
});
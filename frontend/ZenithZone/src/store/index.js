import Vuex from "vuex";
import { deportes } from "./modules/client/deportes";
import { clases } from "./modules/client/clases";
import { pistas } from "./modules/client/pistas";

export default Vuex.createStore({
    modules: {
        deportes: deportes,
        clases: clases,
        pistas: pistas
    }
});
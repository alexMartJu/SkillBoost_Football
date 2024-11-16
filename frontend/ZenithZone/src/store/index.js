import Vuex from "vuex";
import { deportes } from "./modules/client/deportes";

export default Vuex.createStore({
    modules: {
        deportes: deportes
    }
});
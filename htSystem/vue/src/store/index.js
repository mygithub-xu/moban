import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex) 

import menu from './modules/menu'
import avatar from './modules/avatar'

const store = new Vuex.Store({
    modules: {
        menu,
        avatar
    }
});

export default store;
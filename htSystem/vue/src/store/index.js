import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex) 

import menu from './modules/menu'
import userInfo from './modules/userInfo'

const store = new Vuex.Store({
    modules: {
        menu,
        userInfo
    }
});

export default store;
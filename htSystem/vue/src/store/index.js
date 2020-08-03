import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex) 

import menu from './modules/menu'
import userInfo from './modules/userInfo'
import tabRouter from './modules/tabRouter'

const store = new Vuex.Store({
    modules: {
        menu,
        userInfo,
        tabRouter
    }
});

export default store;
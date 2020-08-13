import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex) 

import menu from './modules/menu'
import userInfo from './modules/userInfo'
import tabRouter from './modules/tabRouter'
import addRouter from './modules/addRouter'

const store = new Vuex.Store({
    modules: {
        menu,
        userInfo,
        tabRouter,
        addRouter
    }
});

export default store;
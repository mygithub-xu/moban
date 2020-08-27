import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex) 

import userInfo from './modules/userInfo'
import tabRouter from './modules/tabRouter'
import addRouter from './modules/addRouter'
import layoutParam from './modules/layoutParam'

const store = new Vuex.Store({
    modules: {
        userInfo,
        tabRouter,
        addRouter,
        layoutParam
    }
});

export default store;
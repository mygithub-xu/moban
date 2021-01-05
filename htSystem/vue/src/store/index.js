import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex) 

import userInfo from './modules/userInfo'
import tabRouter from './modules/tabRouter'
import addRouter from './modules/addRouter'
import layoutParam from './modules/layoutParam'
import globalSize from './modules/globalSize'

const store = new Vuex.Store({
    modules: {
        userInfo,
        tabRouter,
        addRouter,
        layoutParam,
        globalSize
    }
});

export default store;
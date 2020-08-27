import Vue from 'vue'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'


import { Button, Input , Form} from 'element-ui';
const Elements = [Button, Input ,Form];
Elements.forEach(key => {
  Vue.use(key, {
    size: 'small'
  });
});


Vue.use(Element)

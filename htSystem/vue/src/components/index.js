// 存放一些全局的组件
import sidebar2 from './layout/otherLayoutCom/sidebar2.vue';
import AppMain2 from './layout/otherLayoutCom/AppMain2.vue';
import menuTree2 from './layout/otherLayoutCom/menuTree2.vue';
import menuTree2Hor from './layout/otherLayoutCom/menuTree2Hor.vue';
import breadcrumb2 from './layout/otherLayoutCom/breadcrumb2.vue';
import headTabs from './layout/otherLayoutCom/headTabs.vue';
import userSetting from './layout/otherLayoutCom/userSetting.vue';
import customPopBox from './layout/otherLayoutCom/customPopBox.vue';
import rightHeader from './layout/otherLayoutCom/rightHeader.vue';
import leftHeader from './layout/otherLayoutCom/leftHeader.vue';
import scrollViewOverDiv from './layout/otherLayoutCom/scrollView.vue';

import treeSelect from './common/treeSelect/treeSelect.vue';//树形
import pagination from './common/pagination/index';//分页
import treeMultiSelect from './common/treeSelect/treeMultiSelect';//分页
import upload from './common/upload/upload';//头像组件
import uploadMore from './common/upload/uploadMore';//图片上传组件
import photoShow from './common/upload/photoShow';
import editor from './common/vueEditor/VueEditor';
import currencyInput from './common/input/currencyInput';
import uploadFile from './common/upload/uploadFile';



//全局注册icon-svg

const components = [
  sidebar2,
  AppMain2,
  menuTree2,
  menuTree2Hor,
  breadcrumb2,
  headTabs,
  userSetting,
  customPopBox,
  rightHeader,
  leftHeader,
  scrollViewOverDiv,
  

  treeSelect,
  treeMultiSelect,
  upload,
  pagination,
  uploadMore,
  photoShow,
  editor,
  currencyInput,
  uploadFile,
];

export default function(Vue, opts = {}) {
  components.map(component => {
    Vue.component(component.name, component);
  });
};
// 存放一些全局的组件
import sidebar from './layout/otherLayoutCom/sidebar.vue';
import AppMain from './layout/otherLayoutCom/AppMain.vue';
import menuTree from './layout/otherLayoutCom/menuTree.vue';
import menuTreeHor from './layout/otherLayoutCom/menuTreeHor.vue';
import headTabs from './layout/otherLayoutCom/headTabs.vue';
import customPopBox from './layout/otherLayoutCom/customPopBox.vue';
import layoutHeader from './layout/otherLayoutCom/layoutHeader.vue';
import layoutBody from './layout/otherLayoutCom/layoutBody.vue';
import layoutHeaderLeft from './layout/otherLayoutCom/layoutHeaderLeft.vue';
import layoutHeaderCenter from './layout/otherLayoutCom/layoutHeaderCenter.vue';
import layoutHeaderRight from './layout/otherLayoutCom/layoutHeaderRight.vue';
import layoutSetting from './layout/otherLayoutCom/layoutSetting.vue';


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
  sidebar,
  AppMain,
  menuTree,
  menuTreeHor,
  layoutSetting,
  headTabs,
  customPopBox,
  layoutHeader,
  layoutBody,
  layoutHeaderLeft,
  layoutHeaderCenter,
  layoutHeaderRight,

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
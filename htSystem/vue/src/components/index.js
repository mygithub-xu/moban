// 存放一些全局的组件
import sidebar2 from './layout/otherLayoutCom/sidebar2.vue';
import AppMain2 from './layout/otherLayoutCom/AppMain2.vue';
import menuTree2 from './layout/otherLayoutCom/menuTree2.vue';
import menuTree2Hor from './layout/otherLayoutCom/menuTree2Hor.vue';
import breadcrumb2 from './layout/otherLayoutCom/breadcrumb2.vue';
import headTabs from './layout/otherLayoutCom/headTabs.vue';
import userSetting from './layout/otherLayoutCom/userSetting.vue';
import customPopBox from './layout/otherLayoutCom/customPopBox.vue';


import menuTree from './common/menuTree.vue';
import Hamburger from './common/Hamburger.vue';//折叠样式图标
import breadcrumb from './common/Breadcrumb.vue';//面包屑
import screenfull from './common/Screenfull.vue';//全屏
import sizeSelect from './common/SizeSelect.vue';//布局大小组件
import treeSelect from './treeSelect/treeSelect.vue';//树形
import pagination from './pagination/index';//分页
import treeMultiSelect from './treeSelect/treeMultiSelect';//分页
import upload from './upload/upload';//头像组件
import uploadMore from './upload/uploadMore';//图片上传组件
import photoShow from './upload/photoShow';
import editor from './vueEditor/VueEditor';
import currencyInput from './input/currencyInput';
import uploadFile from './upload/uploadFile';



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
  
  uploadFile,

  menuTree,
  Hamburger,
  breadcrumb,
  screenfull,
  sizeSelect,
  treeSelect,
  treeMultiSelect,
  upload,
  pagination,
  uploadMore,
  photoShow,
  editor,
  currencyInput
];

export default function(Vue, opts = {}) {
  components.map(component => {
    Vue.component(component.name, component);
  });
};
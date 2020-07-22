// 存放一些全局的组件
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



//全局注册icon-svg

const components = [
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
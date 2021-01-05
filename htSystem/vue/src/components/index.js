// 存放一些全局的组件
import editComp from '@comp/common/editComp.vue';//树形
import treeSelect from '@comp/common/treeSelect/treeSelect.vue';//树形
import pagination from '@comp/common/pagination/index';//分页
import treeMultiSelect from '@comp/common/treeSelect/treeMultiSelect';//分页
import upload from '@comp/common/upload/upload';//头像组件
import uploadMore from '@comp/common/upload/uploadMore';//图片上传组件
import photoShow from '@comp/common/upload/photoShow';
import editor from '@comp/common/vueEditor/VueEditor';
import currencyInput from '@comp/common/input/currencyInput';
import uploadFile from '@comp/common/upload/uploadFile';//导入组件
import exportcom from '@comp/common/upload/exportcom';//导出组件
import NumberInput from '@comp/common/input/NumberInput';//导出组件
import queryItem from '@comp/common/UI/queryItem';//导出组件

//全局注册icon-svg

const components = [
  treeSelect,
  treeMultiSelect,
  upload,
  pagination,
  uploadMore,
  photoShow,
  editor,
  currencyInput,
  uploadFile,
  editComp,
  exportcom,
  NumberInput,
  queryItem
];

export default function(Vue, opts = {}) {
  components.map(component => {
    Vue.component(component.name, component);
  });
};
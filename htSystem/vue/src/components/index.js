
// 存放一些全局的组件
import editComp from '@/components/common/editComp.vue';//树形
import treeSelect from '@/components/common/treeSelect/treeSelect.vue';//树形
import pagination from '@/components/common/pagination/index';//分页
import treeMultiSelect from '@/components/common/treeSelect/treeMultiSelect';//分页
import uploadHeader from '@/components/common/upload/uploadHeader';//头像组件
import uploadMore from '@/components/common/upload/uploadMore';//图片上传组件
import photoShow from '@/components/common/upload/photoShow';
import editor from '@/components/common/vueEditor/VueEditor';
import currencyInput from '@/components/common/input/currencyInput';

import uploadFile from '@/components/common/upload/uploadFile';//导入组件
import upload from '@/components/common/upload/index';//上传组件
import exportcom from '@/components/common/upload/exportcom';//导出组件
import NumberInput from '@/components/common/input/NumberInput';//导出组件
import queryItem from '@/components/common/UI/queryItem';//查询ui组件

//全局注册icon-svg

const components = [
  upload,
  treeSelect,
  treeMultiSelect,
  uploadHeader,
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
]

export default function(Vue, opts = {}) {
  components.map(component => {
    Vue.component(component.name, component);
  })
}
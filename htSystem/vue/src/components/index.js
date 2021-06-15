
// 存放一些全局的组件
import editComp from '@/components/common/editComp.vue'//编辑ui
import treeSelect from '@/components/common/treeSelect/treeSelect.vue'//树形
import pagination from '@/components/common/pagination/index'//分页
import treeMultiSelect from '@/components/common/treeSelect/treeMultiSelect'//树形选择器
import uploadHeader from '@/components/common/upload/uploadHeader'//头像编辑上传组件
import uploadMore from '@/components/common/upload/uploadMore'//图片上传组件
import photoShow from '@/components/common/upload/photoShow'
import editor from '@/components/common/vueEditor/VueEditor'//富文本组价
import currencyInput from '@/components/common/input/currencyInput'// 金额组件

import uploadFile from '@/components/common/upload/uploadFile';//上传文件
import upload from '@/components/common/upload/index'//上传组件

import NumberInput from '@/components/common/input/NumberInput'//数字组件
import queryItem from '@/components/common/UI/queryItem'//查询区域ui组件

import jaImport from '@/components/common/import/index'// 纯前端导入组件

import jaExport from '@/components/common/export/index'// 纯前端导出组件
import exportcom from '@/components/common/export/exportcom'//后台导出组件

//全局注册icon-svg

const components = [
  jaImport,
  jaExport,

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
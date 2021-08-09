<template>
  <el-popover popper-class="myMultipleSelectPopover"
              placement="bottom"
              :title="title"
              :width="width"
              trigger="click"
              content
              v-model="visible"
              :disabled="disabled"
              default-expanded-keys
              @show="setTreeInput"
              @hide="clearTreeInput">
    <el-input placeholder
              v-model="currentLabel"
              :readonly="readonly"
              slot="reference"
              @click="openPopover"
              :disabled="disabled"
              :clearable="true"
              @clear="handleClear"
              :title="currentLabel"></el-input>
    <el-input placeholder="搜索"
              v-model="filter"></el-input>
    <el-scrollbar>
      <el-tree style="height:260px;overflow-x:auto;overflow-y:auto"
               class="filter-tree"
               :data="data2"
               :props="defaultProps"
               :filter-node-method="filterNode"
               ref="tree2"
               show-checkbox
               @check="handleCheck"
               :node-key="nodeKey"
               :check-strictly="checkStrictly"
               highlight-current></el-tree>
    </el-scrollbar>
  </el-popover>
</template>
<script>
export default {
  name: "treeMultiSelect",
  props: {
    value: {
      type: [String, Array],
      require: true
    },
    data: {
      type: Array,
      require: true
    },
    label: {
      type: String,
      required: true
    },
    title: {
      type: String,
      default: "下拉树选择框"
    },
    disabled: {
      type: Boolean,
      default: false
    },

    top: {
      type: Boolean,
      default: true
    },
    /*  itself: {
      //下拉数据是否需要滤掉它自己及子集
      type: Boolean,
      default: false
    }, */
    itselfId: {
      //编辑数据时该数据的id
      type: String,
      required: false
    },
    onlyChildren: {
      type: Boolean,
      default: false
    },
    nodeKey: {
      type: String,
      default: () => "id"
    },
    checkStrictly: {
      type: Boolean,
      default: () => false
    },
    includeHalfChecked: {
      type: Boolean,
      default: () => false
    },
    /* 自动选择下级至末级，只有在 checkStrictly 为 false 时 有效 */
    autoCheckChildrenToLeaf: {
      type: Boolean,
      default: () => false
    }
  },
  beforeCreate () { },
  created () {
    this.findLabel();
  },
  data () {
    return {
      width: "",
      visible: false,
      readonly: false,
      currentLabel: "",
      filter: "",
      hasTopData: []
    };
  },
  watch: {
    filter (val) {
      this.$refs.tree2.filter(val);
    },
    value (val) {
      if (!val || (Array.isArray(val) && val.length === 0)) {
        // clear selection
        this.handleClear();
      }
      this.findLabel();
    },
    currentLabel (val) {
      if (val == "") {
        this.$emit("input", "");
      }
    },
    hasTopData () {
      this.findLabel();
    }
  },
  computed: {
    defaultProps () {
      return {
        children: "children",
        label: this.label,
        disabled: this.disabledFn,
      };
    },
    expanded () {
      if (this.value != null && this.value !== "") {
        return [this.value];
      } else {
        return [];
      }
    },
    data2 () {
      this.hasTopData = Object.assign([], this.data);
      if (this.top) {
        var top = {
          id: "",
          parentId: "",
          children: [],
          [this.label]: "无"
        };
        this.hasTopData.unshift(top);
      }
      if (this.itselfId != null && this.itselfId != "") {
        //编辑时，过滤该数据它本身及子集
        return this.filterTreeData(this.hasTopData, this.itselfId);
      } else {
        return this.hasTopData;
      }
    }
  },
  mounted () {
    this.$nextTick(function () {
      if (this.widthValue) {
        this.width = this.widthValue;
        return;
      }
      this.width = this.$el.getBoundingClientRect().width;
    })
  },
  methods: {
    //此处控制部门中为【子公司】的数据禁用不可选
    disabledFn (data, node) {
      if (data.flag && data.flag == "1") {
        return true;
      } else {
        return false;
      }
    },
    //过滤数据
    filterTreeData (treeData, id) {
      let this_copy = this;
      let treeDataList = [];
      treeData.forEach(object => {
        if (object.id != id && object.parentId != id) {
          if (object.children != null && object.children != undefined) {
            object.children = this_copy.filterTreeData(object.children, id);
          }
          treeDataList.push(object);
        }
      })

      return treeDataList;
    },

    //*********以下1、2、两点是为了解决readonly和clearable同时用不生效的解决方法************************* */
    //1、点击选择下拉时设置输入框为只读
    setTreeInput () {
      this.readonly = true;
    },
    //2、点击下拉框不选择时，设置为可编辑，目的是提高clearable方法可以
    clearTreeInput () {
      this.readonly = false;
    },
    openPopover () {
      if (!this.disabled) {
        this.visible = true;
        this.readonly = false;
      }
    },
    filterNode (value, data) {
      if (!value) return true;
      return data[this.label].indexOf(value) !== -1;
    },
    findLabel () {
      const checkedNodes = this.$refs["tree2"]
        ? this.$refs["tree2"].getCheckedNodes(
          this.onlyChildren,
          this.includeHalfChecked
        )
        : [];
      this.currentLabel = this.getLabelTextByNodes(checkedNodes);
      this.ergodicTree(this.hasTopData, this.value);
    },
    ergodicTree (data, value) {
      data.forEach(i => {
        if (i.id == value) {
          // this.currentLabel = i[this.label];
        } else {
          if (i.children != undefined && i.children != null) {
            this.ergodicTree(i.children, value);
          }
        }
      })
    },
    getLabelTextByNodes (nodes) {
      return nodes.map(item => item && item[this.label]).join(",");
    },

    handleCheck (node, { checkedNodes, checkedKeys, halfCheckedNodes, halfCheckedKeys }) {
      // 先处理 需 带出下级至末级 的情况
      if (this.checkStrictly && this.autoCheckChildrenToLeaf) {
        const checked = checkedKeys.indexOf(node[this.nodeKey]) > -1;
        const newCheckedKeys = checkedKeys.slice(0);
        const newCheckedNodes = checkedNodes.slice(0);

        this.walk(node, item => {
          const idx = newCheckedKeys.indexOf(item[this.nodeKey]);
          if (checked && idx === -1) {
            newCheckedKeys.push(item[this.nodeKey]);
            newCheckedNodes.push(item);
          } else if (!checked && idx > -1) {
            newCheckedKeys.splice(idx, 1);
          }
        })
        this.$refs["tree2"].setCheckedKeys(newCheckedKeys);
        checkedKeys = newCheckedKeys;
        checkedNodes = newCheckedNodes;
      } else {
        const checked = checkedKeys.indexOf(node[this.nodeKey]) > -1;
        const newCheckedKeys = checkedKeys.slice(0);
        const newCheckedNodes = checkedNodes.slice(0);

        this.walk(node, item => {
          const idx = newCheckedKeys.indexOf(item[this.nodeKey]);
          if (checked && idx === -1) {
            newCheckedKeys.push(item[this.nodeKey]);
            newCheckedNodes.push(item);
          } else if (!checked && idx > -1) {
            newCheckedKeys.splice(idx, 1);
          }
        })
        this.$refs["tree2"].setCheckedKeys(newCheckedKeys);

        checkedKeys = newCheckedKeys;
        checkedNodes = newCheckedNodes;
      }

      this.$emit("selected", this.getLabelTextByNodes(checkedNodes));
      const input = checkedNodes.map(item => item && item.id);
      const inputHalf = checkedNodes.map(item => item && item.id);
      //拼接半选状态的父级id,存入后台数据库
      if (this.$refs["tree2"].getHalfCheckedNodes()) {
        this.$refs["tree2"].getHalfCheckedNodes().map(item => {
          if (item) {
            inputHalf.push(item.id)
          }
        })
      }
      this.$emit("input", Array.isArray(this.value) ? inputHalf : inputHalf.join(","),
        Array.isArray(this.projectParentId) ? input : input.join(","));

    },
    handleClear () {
      this.$refs["tree2"] && this.$refs["tree2"].setCheckedNodes([]);
      const checkedNodes = this.$refs["tree2"]
        ? this.$refs["tree2"].getCheckedNodes(
          this.onlyChildren,
          this.includeHalfChecked
        )
        : [];
      const halfCheckedNodes = this.$refs["tree2"]
        ? this.$refs["tree2"].getHalfCheckedNodes(
          this.onlyChildren,
          this.includeHalfChecked
        )
        : [];

      this.$emit("selected", this.getLabelTextByNodes(checkedNodes));
      const input = checkedNodes.map(item => item && item.id);
      const inputHalf = halfCheckedNodes.map(item => item && item.id);
      if (halfCheckedNodes) {
        halfCheckedNodes.map(item => {
          if (item) {
            inputHalf.push(item.id)
          }
        })
      }

      this.$emit("input", Array.isArray(this.value) ? inputHalf : inputHalf.join(","),
        Array.isArray(this.projectParentId) ? input : input.join(","));

    },
    walk (node, cb) {
      if (node.children) {
        node.children.forEach(item => {
          cb(item);
          this.walk(item, cb);
        })
      }
    }
  }
};
</script>
<style lang="scss" scoped>
/* .el-popover--plain {
    /* padding: 0 !important; */
/* } */
.myMultipleSelectPopover /deep/ .el-scrollbar__wrap {
  height: 200px;
  margin-top: 10px;
}
</style>

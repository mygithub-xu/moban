<template>
  <el-popover
    popper-class="myPopover"
    placement="bottom"
    :title="title"
    :width="width"
    trigger="click"
    content
    v-model="visible"
    :disabled="disabled"
    default-expanded-keys
    @show="setTreeInput"
    @hide="clearTreeInput"
  >
    <el-input
      placeholder
      v-model="currentLabel"
      :readonly="readonly"
      slot="reference"
      @click="openPopover"
      :disabled="disabled"
      :clearable="true"
      @clear="handleClear"
    ></el-input>
    <el-input placeholder="搜索" v-model="filter"></el-input>
    <el-scrollbar>
      <el-tree
        style="height:260px;overflow-x:auto;overflow-y:auto"
        class="filter-tree"
        :data="data2"
        :props="defaultProps"
        :filter-node-method="filterNode"
        ref="tree2"
        @node-click="nodeClick"
      ></el-tree>
    </el-scrollbar>
  </el-popover>
</template>
<script>
export default {
  name: "treeSelect",
  props: {
    value: {
      type: String,
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
    }
  },
  beforeCreate() {},
  created() {
    this.findLabel();
  },
  data() {
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
    filter(val) {
      this.$refs.tree2.filter(val);
    },
    value() {
      this.findLabel();
    },
    currentLabel(val) {
      if (val == "") {
        this.$emit("input", "");
      }
    },
    hasTopData() {
      this.findLabel();
    }
  },
  computed: {
    defaultProps() {
      return {
        children: "children",
        label: this.label
      };
    },
    expanded() {
      if (this.value != null && this.value !== "") {
        return [this.value];
      } else {
        return [];
      }
    },
    data2() {
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
  mounted() {
    this.$nextTick(function() {
      if (this.widthValue) {
        this.width = this.widthValue;
        return;
      }
      this.width = this.$el.getBoundingClientRect().width;
    });
  },
  methods: {
    //过滤数据
    filterTreeData(treeData, id) {
      let this_copy = this;
      let treeDataList = [];
      treeData.forEach(object => {
        if (object.id != id && object.parentId != id) {
          if (object.children != null && object.children != undefined) {
            object.children = this_copy.filterTreeData(object.children, id);
          }
          treeDataList.push(object);
        }
      });

      return treeDataList;
    },

    //*********以下1、2、两点是为了解决readonly和clearable同时用不生效的解决方法************************* */
    //1、点击选择下拉时设置输入框为只读
    setTreeInput() {
      this.readonly = true;
    },
    //2、点击下拉框不选择时，设置为可编辑，目的是提高clearable方法可以
    clearTreeInput() {
      this.readonly = false;
    },
    openPopover() {
      if (!this.disabled) {
        this.visible = true;
        this.readonly = false;
      }
    },
    filterNode(value, data) {
      if (!value) return true;
      return data[this.label].indexOf(value) !== -1;
    },
    findLabel() {
      this.currentLabel = "";
      this.ergodicTree(this.hasTopData, this.value);
    },
    ergodicTree(data, value) {
      data.forEach(i => {
        if (i.id == value) {
          this.currentLabel = i[this.label];
        } else {
          if (i.children != undefined && i.children != null) {
            this.ergodicTree(i.children, value);
          }
        }
      });
    },
    nodeClick(Object, node, array) {
      if (this.onlyChildren) {
        if (Object.children != null && Object.children.length > 0) {
          return;
        }
      }

      this.$emit("changeValue", Object.id, Object);
      this.$emit("selected", Object.projectName);
      this.$emit("selectedMerchat", Object.merchantTypeName);

      this.currentLabel = Object[this.label];
      this.$emit("input", Object.id);
      this.visible = false;
      this.readonly = false;
    },
    handleClear() {
      this.$emit("changeValue", undefined, {});
      this.$emit("selected", undefined);
      this.$emit("selectedMerchat", undefined);

      this.currentLabel = undefined;
      this.$emit("input", undefined);
      this.visible = false;
      this.readonly = false;
    }
  }
};
</script>
<style lang="scss" scoped>
/* .el-popover--plain {
    /* padding: 0 !important; */
/* } */
.myPopover /deep/ .el-scrollbar__wrap {
  height: 200px;
  margin-top: 10px;
}
</style>

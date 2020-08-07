<!--菜单配置-->
<template>
    <div class="app-container2">
        <div class="resource-register" ref="MenuListSpan">
    <!---->
    <el-row :gutter="24" style="margin-left:0;width:100%;height:100%;padding-bottom: 5px;">
      <el-col :span="5" style="padding-left:0;padding-right:7px;">
        <div style="background-color:#fff; box-shadow: rgba(102, 102, 102, 0.2) 1px 2px 1px 0px">
          <div class="title-module" style="text-align:left;border-bottom:1px solid #d1dbe5;padding-left:10px">
            菜单
          </div>
          <el-tree
          :default-expand-all="true"
          :style="{'height':height+'px'}" 
          style="overflow:auto;width:100%;" 
          ref="attrList" 
          :data="treeData" 
          :props="props1" 
          accordion 
          @node-click="handleNodeClick" 
          node-key="id"
          :default-expanded-keys="defaultExpandKeys"
          :highlight-current="isShowGaoliang">
          </el-tree>
        </div>
      </el-col>
      <el-col class="MenuListSpan19" :span="19">
        <div style="position:absolute;left:0;top:0;">
          <div class="title-module"
          style="text-align:left;border-bottom:1px solid #d1dbe5;padding-left:10px;margin:0px 0px 10px 0;">
          菜单配置
        </div>
        <div class="buttons" style="margin-bottom:10px;text-align:right;padding-right:10px">
          <el-button type="primary" size="mini" v-if="!editState"  @click="edit" >修改</el-button>
          <el-button type="danger" size="mini"  v-has="'sysMenu:delete'" v-if="!editState" @click="remove">删除</el-button>
          <el-button type="primary" size="mini" v-if="!editState"  @click="append" :disabled="!isChildren">新增</el-button>
          <el-button type="primary" size="mini"  v-has="'sysMenu:save'" v-if="editState"  @click="save('currentRes')">保存</el-button>
          <el-button type="primary" size="mini" v-if="editState" @click="cancel">取消</el-button>
        </div>
        <el-row :gutter="12">
          <el-form :model="currentRes" ref="currentRes" class="form-search" size="mini" label-width="100px"
            label-position="right" :disabled="!editState">
            <el-col :xs="12" :sm="24" :md="12" :lg="8" :xl="8" style="height:36px;line-height:36px;">
              <el-form-item label="是否含有下级" prop="isChildren">
                <el-select v-model="currentRes.isChildren" style="width:11rem" @change="handleIsChildrenOption">
                  <el-option v-for="item in isChildrenOptions" :key="item.value" :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="12" :sm="24" :md="12" :lg="8" :xl="8" style="height:36px;line-height:36px;">
              <el-form-item label="上级菜单" prop="parentId">
                <treeSelect v-model="currentRes.parentId" :readonly="!editState" :disabled="!editState" :data="treeSelectdata" label="menuName"/>
              </el-form-item>
            </el-col>
            <el-col :xs="12" :sm="24" :md="12" :lg="8" :xl="8" style="height:36px;line-height:36px;">
              <el-form-item label="编码" prop="menuCode">
                <el-input type="text" style="width:11rem" v-model="currentRes.menuCode" placeholder="编码"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="12" :sm="24" :md="12" :lg="8" :xl="8" style="height:36px;line-height:36px;">
              <el-form-item label="名称" prop="menuName">
                <el-input type="text" style="width:11rem" v-model="currentRes.menuName" placeholder="名称"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="12" :sm="24" :md="12" :lg="8" :xl="8" style="height:36px;line-height:36px;">

                  <el-form-item label="图标："  prop="icon">
                     <el-popover
                      placement="right"
                      width="200"
                      trigger="click"
                      @show="Show()"
                      :disabled='!visible'
                      popper-class="popper-class">
                      <my-icon ref="icons" v-if="visible" v-on:selected="selectedIcon" />
                      <el-input slot="reference" placeholder="请输入图标名称" v-model="currentRes.icon" style="cursor: pointer;width:11rem">
                       <template slot="prepend"><i class="fas" :class="currentRes.icon"></i></template>
                      </el-input>
                     </el-popover>
                    </el-form-item>


                <!-- </el-input>
              </el-form-item> -->
            </el-col>
            <el-col :xs="12" :sm="24" :md="12" :lg="8" :xl="8" style="height:36px;line-height:36px;">
              <el-form-item label="路径" prop="url">
                <el-input type="text" style="width:11rem" v-model="currentRes.url" placeholder="路径"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="12" :sm="24" :md="12" :lg="8" :xl="8" style="height:36px;line-height:36px;">
              <el-form-item label="启用状态" prop="status">
                <el-select style="width:11rem" v-model="currentRes.status">
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="12" :sm="24" :md="12" :lg="8" :xl="8" style="height:36px;line-height:36px;">
              <el-form-item label="创建人" prop="createUser">
                <el-input style="width:11rem" type="text" v-model="currentRes.createUser" placeholder="创建人" readonly></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="12" :sm="24" :md="12" :lg="8" :xl="8" style="height:36px;line-height:36px;">
              <el-form-item label="创建时间" prop="createTime">
                <el-input style="width:11rem" type="text" v-model="currentRes.createTime" placeholder="创建时间" readonly></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="12" :sm="24" :md="12" :lg="8" :xl="8" style="height:36px;line-height:36px;">
              <el-form-item label="备注" prop="description">
                <el-input style="width:11rem" type="text" v-model="currentRes.description" placeholder="备注" maxlength="400"></el-input>
              </el-form-item>
            </el-col>
          </el-form>
        </el-row>
        <div class="buttons" style="margin-bottom: 10px;padding-right:20px;text-align: right;">
          <el-button type="primary" size="mini" buttonCode="CD001" @click="getButtonList" :disabled="!currentRes.id||isChildren">新增按钮
          </el-button>
           <el-row class="tac" style="display:inline-block;top:5px;">
          <el-col :span="24">
            <el-dropdown trigger="click" :hide-on-click="false">
              <div class="el-icon-menu"></div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <el-checkbox v-model="btnName">按钮名称</el-checkbox>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-checkbox v-model="btnDes">备注</el-checkbox>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </el-row>
        </div>
       
        </div>
        <div class="right-table">
          <!--表格-->
          <el-table :data="buttonList" ref="multipleTable" border style="width: 100%;"  align="center" height="100%">
            <el-table-column prop="buttonCode" label="按钮编码" min-width="120" align="center" show-overflow-tooltip></el-table-column>
            <el-table-column prop="buttonName" label="按钮名称" min-width="150" align="center" v-if="btnName" show-overflow-tooltip></el-table-column>
            <el-table-column prop="buttonUrl" label="按钮权限" width="120"  align="center" show-overflow-tooltip></el-table-column>
            <el-table-column prop="status" label="状态" width="120" align="center" show-overflow-tooltip>
              <template slot-scope="scope">
                <div v-for="item in options" :key="item.value">
                  <el-tag
                    :type="item.label=='启用'?'success':'danger'"
                    v-if="scope.row.status==item.value"
                  >{{item.label}}</el-tag>
                </div>
              </template>
            </el-table-column>

            <el-table-column prop="description" label="备注 " align="center"  width="150" v-if="btnDes" show-overflow-tooltip></el-table-column>
            <el-table-column label="操作" align="center"  min-width="180" show-overflow-tooltip>
              <template slot-scope="scope">
                <!--<el-button @click="handleEdit(scope.$index,scope.row)" type="text" size="small">编辑</el-button>-->
                <el-button @click="handleDelete(scope.$index,scope.row)" class="red" v-has="'sysButton:delete'" type="text" size="small">删除
                </el-button>
                <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <!--新增dialog v-draggable="'.el-dialog'" v-draggable="{ defwidth:'600',defmargintop:'',defheight:'500',container:'.el-dialog' }" style="overflow-y:hidden;"-->
        <div class="sysMenyListWrap">
          <el-dialog
            :visible.sync="dialogFormVisible"
            width="70%"
            :modal-append-to-body="false"
            >

            <el-table :data="buttonTable" ref="buttonTable" height="360">
              <el-table-column prop="buttonCode" label="按钮编码" width="120" show-overflow-tooltip></el-table-column>
              <el-table-column prop="buttonName" label="按钮名称" width="120" show-overflow-tooltip></el-table-column>
              <el-table-column prop="description" label="备注" width="180" show-overflow-tooltip></el-table-column>
              <el-table-column label="操作" show-overflow-tooltip>
                <template slot-scope="scope">
                  <el-button @click="insertButton(scope.row)" type="text" size="small">添加</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-dialog>

          <el-dialog
              width="40%"
              title="添加按钮权限"
              :visible.sync="innerVisible"
              append-to-body>

              <el-form  :model="quanxian" :rules="buttonRules" ref="quanxian" label-width="100px" size="small" >
                <el-form-item label="按钮权限" prop="buttonUrl">
                  <el-input style="width:22rem" type="text" v-model="quanxian.buttonUrl" placeholder="按钮权限" ></el-input>
                </el-form-item>

                <el-form-item label="状态" prop="status">
                  <el-select style="width:22rem" v-model="quanxian.status">
                     <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                     </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="备注" prop="description">
                  <el-input style="width:22rem" type="text" v-model="quanxian.description" ></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" size="small" v-has="'sysButton:save'" @click="submitButton()">提交</el-button>
                  <el-button @click="cancelButton">取消</el-button>
                </el-form-item>
              </el-form>

            </el-dialog>

        </div>
        </div>
      </el-col>
    </el-row>
    <div class="right">
    </div>
  </div>
    </div>
</template>

<script>
  import axios from 'axios'

  import myIcon from '../../../components/layout/otherLayoutCom/icons.vue'

  function newButtonForm() {
    return {
      id: '',
      buttonCode: '',
      buttonName: '',
      menuId: '',
      description: ''
    }
  }

  function newCurrentRes() {
    return {
      id: '',
      parentId: '', // 父级主键
      menuCode: '',
      menuName: '',
      icon: '',
      url: '',
      status: "1",
      createUser: '',
      createTime: '',
      updateUser: '',
      updateTime: '',
      description: '',
      isChildren: '存在',
      buttonList: []
    }
  }

  export default {
    menuName: 'MenuConfig',
    components: {
      myIcon
    },
    data() {
      return {
        keysid:"",
        defaultExpandKeys: [],//默认展开节点列表
        isShowGaoliang:true,
        height:'',
        visible:false,
        btnName: true,
        btnDes: true,
        //   isIconPickerVisible: false,
        props1: {
          children: 'children',
          label: 'menuCodeName'
        },
        currentRes: newCurrentRes(),
        editState: false,
        treeData: [],
        buttonList: [], // 当前节点已选的按钮
        editForm: newButtonForm(),
        dialogFormVisible: false,
        options: [],
        isChildrenOptions: [],
        isChildren:true,
        buttonTable: [], // 可选的按钮表

        treeSelectdata:[],       //下来菜单树
        innerVisible:false,
        quanxian:{
            id: '',
            buttonCode: '',
            buttonName: '',
            menuId: '',
            buttonUrl: '',
            status: "1",
            createUser: '',
            createTime: '',
            description: '',
        },
        buttonRules:{
          buttonUrl: [
            {
              required: true,
              message: "  ",
              trigger: "blur"
            },
          ],
          status: [
            {
              required: true,
              message: "  ",
              trigger: "blur"
            },
          ],
        }

      }
    },
    created() {
    },
    activated() {
      if (this.$route.meta.idj === true) {
        Object.assign(this.$data, this.$options.data())
        this.queryTree()
      }
    },
    methods: {
      getType(type) {
        this.options = [];
        this.$http.get(this.api.dicTypeGetType + type).then(res => {
          if (res.data.code == 200) {
            if (type == 'enabled') {
              this.options = res.data.body
            } else if (type == 'isChildren') {
              this.isChildrenOptions = res.data.body
            }
          }
        })
      },

      append() {
        const vm = this;
        vm.editState = true;
        vm.visible=true;
        const parentId = vm.currentRes.id
        this.currentRes = {
          id: '', // 主键
          parentId: parentId, // 父级主键
          menuCode: '', //编码
          menuName: '', //菜单名称
          icon: '', //图标
          url: '', //菜单路径
          status: "1", //状态(是否启用) 1启用  0禁用
          createUser: '', //创建人
          createTime: '', //创建时间
          updateUser: '', //修改人
          updateTime: '', //修改时间
          description: '', //备注
          isChildren: '存在',
          buttonList: [] //按钮组
        },
        vm.keysid = "1"
      },
      /**
       * 移除节点
       * */
      remove() {
        const vm = this
        vm.keysid = "2"
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (!vm.currentRes.menuCode) {
            this.$message.error('请选中一条记录')
          } else {
            axios.delete(this.api.deleteNode + vm.currentRes.id).then((res) => {
              const ids = vm.currentRes.parentId
              if (res.data.code === '200') {
                vm.currentRes = newCurrentRes()
                this.$message({
                  message: '删除成功',
                  type: 'success'
                })
              } else {
                this.$message.error(res.data.message)
              }
              vm.queryTree(ids)
            })
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      /**
       * 编辑节点
       * */
      edit() {
        const vm = this
        vm.keysid = "2"
        if (!vm.currentRes.id) {
          this.$message.error('请选中一条记录')
        } else {
          vm.editState = true
          vm.visible=true
        }
      },
      /**
       * 保存菜单
       * */
      save(currentRes) {
        const vm = this
        const code = ''
        if(vm.keysid == "1"){
          vm.code = vm.currentRes.menuCode
        }else{
          vm.code = vm.currentRes.id
        }
        vm.editState = false
        if(this.currentRes.isChildren=="存在"){
            this.currentRes.isChildren='1'
        }else{
            this.currentRes.isChildren='0'
        }
        axios.post(this.api.saveNode, {
          ...this.currentRes
        }).then((res) => {
          if (res.data.code === '200') {
            vm.currentRes = newCurrentRes()
            this.$message.success(res.data.body);
            vm.queryTree(vm.code)
          } else {
            this.$message.error(res.data.body);
          }   
        })
        vm.queryTree(vm.code);
      },
      cancel() {
        const vm = this
        vm.editState = false
        vm.visible=false
        // vm.currentRes = {}
      },
      first_append() {

      },
      /**
       * 点击节点触发操作
       * */
      handleNodeClick(data, node) {
        this.visible=false;
        this.editState = false;
        this.currentRes.id = data.id; //菜单主键
        this.currentRes.parentId = data.parentId; //菜单父级主键
        this.currentRes.menuCode = data.menuCode; //菜单编码
        this.currentRes.menuName = data.menuName; //菜单名称
        this.currentRes.icon = data.icon; //字体图标
        this.currentRes.status = data.status; //启用状态
        this.currentRes.url = data.url; //路径
        this.currentRes.createUser = data.createUser //创建人
        this.currentRes.createTime = data.createTime //创建时间
        this.currentRes.updateUser = data.updateUser //修改人
        this.currentRes.updateTime = data.updateTime //修改时间
        this.currentRes.description = data.description //备注
        this.currentRes.isChildren = data.isChildren=='1'?'存在':'不存在'
        this.buttonList = data.buttonList //按钮组
      
        this.handleIsChildrenOption(data.isChildren)
      },
      // 查询初始化树数据
      queryTree(code) {
        
        const vm = this
        vm.$http.post(this.api.menuList).then((res) => {
          if (res.data.code === '200') {
            let newTreeData = [{
              id: null,
              menuCodeName: '功能树',
              children: res.data.body,
              isChildren:1
            }]
            vm.$set(vm, 'treeData', newTreeData)
            this.treeSelectdata =res.data.body;
            if(vm.keysid == "1"){
              this.findTreeNodeById(code,this.treeSelectdata,{})
            }else{
              this.$nextTick(function(){
              vm.$refs.attrList.setCurrentKey(code);
              vm.defaultExpandKeys=[code]
               })
            }     
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      findTreeNodeById(id, treeData, options) {
        options = options || {};
        const children = options.children = options.children || 'children';
        for (let i = 0; i < treeData.length; i++) {
          const node = treeData[i];
          if (node.menuCode == id) {
              const ids = node.parentId
              this.$nextTick(function(){
              this.$refs.attrList.setCurrentKey(ids);
              this.defaultExpandKeys=[ids]
               })
            return node;
          }
          const t = node[children] && this.findTreeNodeById(id, node[children], options);
          if (t) {
            return t;
          }
        }
        return null;
      },
      handleIsChildrenOption(value){
        //存在下级
        if(value==1){
          this.isChildren = true
        }else{
          this.isChildren = false
        }
      },
      // 查询可新增按钮
      getButtonList() {
        const vm = this
        const params = {
          condition: {},
          number: 1,
          size: 999
        }
        this.$http.post(this.api.buttonConfigQueryByPage, params).then(result => {
          if (result.data.code === '200') {
            if(result.data.body==null){
              this.$message.error("没有可新增按钮！");
              return;
            }
            vm.buttonTable = result.data.body.records
            vm.dialogFormVisible = true
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      // 删除按钮表
      handleDelete(index, row) {
        const vm = this
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.delete(this.api.deleteButton + row.id).then((res) => {
            if (res.data.code === '200') {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              vm.buttonList = vm.buttonList.filter(item => item.id !== row.id)
              vm.treeData[0].children.forEach(item => {
                if (item.id === vm.currentRes.id) {
                  item.buttonList = item.buttonList.filter(subItem => subItem.id !== row.id)
                } else if (item.children && item.children.length > 0) {
                  vm.getRootList(item.children, row.id, null)
                }
              })
            } else {
              this.$message.error(res.data.message)
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      handleEdit(row){
        this.quanxian=row;
        this.innerVisible=true;
      },
      /**
       * 添加按钮
       * */
      insertButton(row) {
        const vm = this
        
        // if (vm.buttonList.length > 0) { // 判断是否已存在相同编码的按钮
        //   const findVal = vm.buttonList.find(item => {
        //     return item.buttonCode === row.buttonCode // 定义按钮编码唯一
        //   })
        //   if (findVal) {
        //     this.$message({
        //       message: '已存在相同的按钮',
        //       type: 'warning'
        //     })
        //     return
        //   }
        // }
        
        this.quanxian=row;
        this.quanxian.id="";
        this.quanxian.menuId = vm.currentRes.id;
        this.innerVisible=true;

      },

      submitButton(){
        const vm = this
        this.$refs["quanxian"].validate(valid => {
          if (valid) {
              
                axios.post(this.api.insertButton, this.quanxian).then((res) => {
                  if (res.data.code === '200') {
                    this.$message.success(res.data.message)
                    vm.buttonList = vm.buttonList.filter(item => item.id !== this.quanxian.id)
                    const newData = res.data.body
                    vm.buttonList.push(newData)
                    // vm.treeData[0].children.forEach(item => {
                    //   if (item.id === vm.currentRes.id) {
                    //     item.buttonList = item.buttonList.filter(subItem => subItem.id !== this.quanxian.id)
                    //     item.buttonList.push(newData)
                    //   } else if (item.children && item.children.length > 0) {
                    //     vm.getRootList(item.children, this.quanxian.id, newData)
                    //   }
                    // })
                    vm.innerVisible = false;
                    vm.dialogFormVisible = false;
                  } else {
                    this.$message.error(res.data.message)
                  }
                })
              
          }
        })
        
      },
      cancelButton(){
          this.innerVisible=false;
      },
      getRootList(resData, id, newData) {
        const vm = this
        resData.forEach(item => {
          if (item.id === vm.currentRes.id) {
            item.buttonList = item.buttonList.filter(subItem => subItem.id !== id)
            if (newData) {
              item.buttonList.push(newData)
            }
          } else if (item.children && item.children.length > 0) {
            vm.getRootList(item.children, id, newData)
          }
        })
      },
      selectedIcon(data){
       let icon='icon-'
       this.currentRes.icon=icon+=data;
       document.body.click()
      },
      Show(){
        if(this.visible==true){
          this.$refs.icons.reset();
        }
        if(this.visible==false){
          return false;
        }
      }
    },
    mounted() {
      const vm = this
      vm.getType('enabled')
      vm.getType('isChildren');
      vm.queryTree();
      // vm.$nextTick(function(){
         this.height=this.$refs.MenuListSpan.clientHeight-76;

      // })
    }
  }

</script>

<style lang="scss" scoped>
.MenuListSpan19 /deep/ .el-input--mini{
 width:11rem;
}
.MenuListSpan19{
position:relative;
top:0;
left:0;
padding-left:10px;
padding-right:10px;
padding-bottom:5px;
padding-top:260px;
height:100%;
background:#fff;
box-shadow: rgba(102, 102, 102, 0.2) 1px 2px 1px 0px, rgba(102, 102, 102, 0.2) -1px -1px 1px 0px;
}
  /deep/ .el-tree--highlight-current /deep/ .el-tree-node.is-current>.el-tree-node__content {
    background-color: #f0f7ff !important;
  }
  .title-module {
    background: -webkit-linear-gradient(top, #fff, #eeeff5);
    /* For Safari 5.1 to 6.0 */
    background: -o-linear-gradient(bottom, #fff, #eeeff5);
    /* For Opera 11.1 to 12.0 */
    background: -moz-linear-gradient(bottom, #fff, #eeeff5);
    /* For Firefox 3.6 to 15 */
    background: linear-gradient(to bottom, #fff, #eeeff5);
    color: #333;
    font-weight: bold;
    font-size: 14px;
    height: 30px;
    line-height: 30px;
  }
/deep/ .el-dialog__wrapper {
    position: absolute !important;
}

/deep/ .v-modal {
    position: absolute !important;
}

/deep/ .content {
    position: relative !important;
}
.sysMenyListWrap /deep/ .el-dialog{
    height: 70%!important;
    overflow: auto;
}
/*.sysMenyListWrap .el-dialog__header {*/
    /*display: none;*/
/*}*/
</style>
<style lang="scss" scoped>
.resource-register{
  width: 100%;
  height:100%;
  box-sizing: border-box;
}
.right-table{
  width: 100%;
  height: 100%;
}
.warpCont{
  width: 100%;
  height: 100%;
}
// .el-tree{
//   height: 550px;
// }
// .right-table{
//   height: 350px;
// }
.red{
  color: #ff0000; 
}
</style>

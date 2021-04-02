// 默认导出整个接口配置
export default {
  // 账号管理
  menu: `api/system/sysMenu/getnodelist`, // 菜单 // 账号管理 // 登陆
  excelImport: `api/system/sysLoginLog/excel`,//列表查询
  sysTestExportExcel: 'test/sysTest/exportExcel',
  /********* 系统配置  start******/
  // 菜单配置
  menuList: `api/system/sysMenu/getnodelist`,
  deleteNode: `api/system/sysMenu/deletenode/`,
  saveNode: `api/system/sysMenu/savenode`,

  //文件上传
  sysFileUploadFile: `api/system/sysFile/uploadFile`,//文件上传
  sysFileUploadFile: `api/system/sysFile/uploadFileMore`,//多文件上传
  sysFileDeleteFile: `api/system/sysFile/deleteFile/`,//单个文件删除
  sysFileBatchdelete: `api/system/sysFile/batchdelete`,//批量文件删除
  sysFileQuerybycondition: `api/system/sysFile/querybycondition`,//查询文件

  // 权限管理--角色管理
  roleQuerybycondition: `api/system/sysRole/querybycondition`,//查询（分页）
  roleGetList: `api/system/sysRole/getList`,//查找所有角色（不分页）
  roleDeleteById: `api/system/sysRole/deleteById/`,//通过id删除角色
  roleSaveorupdate: `api/system/sysRole/saveorupdate`,//更新或新增
  roleBatchUpdate: `api/system/sysRole/batchUpdate`,//批量更新状态
  roleBatchDelete: `api/system/sysRole/batchDelete`,//批量删除
  roleGetButMenu: `api/system/sysRole/getButMenu`,//获取菜单与按钮树形数据
  roleGetButByRoleId: `api/system/sysRole/getButByRoleId/`,//通过角色id找到rolePremission表中对应的数据
  rolePermissionBatchsave: `api/system/sysRolePermission/batchsave/`,//获取按钮通过roleId


  //字典管理
  dicTypeSave: `api/system/sysdictype/save`,
  dicTypeQueryByPage: `api/system/sysdictype/querybypage`,
  dicTypeDelete: `api/system/sysdictype/delete/`, //{id}
  dicTypeBatchDelete: `api/system/sysdictype/bacthdelete`,
  dicTypeGetType: `api/system/sysdictype/gettype/`, //{dicType}
  dicSave: `api/system/sysdic/save`,
  dicQueryByPageAndDicTypeID: `api/system/sysdic/querybypageanddictypeid/`, //{dicTypeId}
  dicDelete: `api/system/sysdic/delete/`, //{id}
  dicBatchDelete: `api/system/sysdic/batchdelete`,
  dicQueryAll: `api/system/sysdic/queryAllDic`,
  sysdictypeGetAll: `api/system/sysdictype/getAll`,

  //按钮配置 sys_button_config
  buttonConfigSave: `api/system/sysbuttonconfig/saveOrUpdate`,
  buttonConfigQueryByPage: `api/system/sysbuttonconfig/querybycondition`,
  buttonConfigDelete: `api/system/sysbuttonconfig/delete/`, //{id}
  buttonConfigBatchDelete: `api/system/sysbuttonconfig/batchdelete`,

  //按钮管理 sys_button
  buttonList: `api/system/sysbutton/selectbuttonlist`,
  deleteButton: `api/system/sysbutton/deletebyid/`,
  updateButton: `api/system/sysbutton/updatebyid`,
  insertButton: `api/system/sysbutton/insert`,

  //账号管理 sys_user
  login: `api/system/sysUser/login`, // 登陆
  logout: `api/system/sysUser/logout`, // 登出
  userGetList: `api/system/sysUser/getList`,//获得全部账号
  userQueryByCondition: `api/system/sysUser/queryByCondition`,//查询
  userDeletebyid: `api/system/sysUser/deleteById/`,//单个删除用户
  userBatchDelete: `api/system/sysUser/bacthdelete`,//批量删除用户
  userSaveOrUpdate: `api/system/sysUser/saveOrUpdate`,//更新或新增用户
  userResetPassword: `api/system/sysUser/resetPassword/`,//重置密码
  userQueryById: `api/system/sysUser/queryById/`,//根据id查询用户
  userQueryHeadPortraitById: `api/system/sysUser/queryHeadPortraitById/`,//根据id获取解密后的图片
  userUpdateUserInfo: `api/system/sysUser/updateUserInfo`,//用户修改自己的信息
  userUpdatePasswordById: `api/system/sysUser/updatePassword`,//用户修改自己的密码


  //登入日志 sys_login_log
  LoginLogQuery: `api/system/sysLoginLog/query`, // 条件查询
  LoginLogBatchdelete: `api/system/sysLoginLog/batchdelete`, // 条件查询

  //测试代码 sys_test
  sysTestsaveOrUpdate: 'api/system/sysTest/saveOrUpdate',//post
  sysTestdeleteById: 'api/system/sysTest/deleteById/',//delete
  sysTestbatchDelete: 'api/system/sysTest/batchDelete',//post
  sysTestquery: 'api/system/sysTest/query',//post
  sysTestqueryById: 'api/system/sysTest/queryById/',//get


  //代码生成 sys_auto_table
  sysAutoTableQuerybycondition: 'api/system/sysAutoTable/querybycondition',
  sysAutoTabledeleteById: `/api/system/sysAutoTable/deleteById/`,//单个删除
  sysAutoTableBatchDelete: `/api/system/sysAutoTable/batchdelete`,//批量删除
  sysAutoTableFindByID: `/api/system/sysAutoTable/findByID/`,//编辑
  sysAutoTableCodeGeneration: `/api/system/sysAutoTable/codeGeneration/`,//代码生成
  sysAutoTableExistable: `/api/system/sysAutoTable/existable`,//检查是否存在该表
  sysAutoTableSaveOrUpdate: `/api/system/sysAutoTable/saveOrUpdate`,//保存表
  sysAutoTableSaveOrUpdateTem: `/api/system/sysAutoTable/saveOrUpdateTem`,//保存模板
  sysAutoTableFindTable: `/api/system/sysAutoTable/findTable`,//查找表


  //代码生成子类 sys_auto_field
  sysAutoFieldFindByTableID: `api/system/sysAutoField/findByTableID/`,//{id}获取表的字段数据

  //代码生成子类 sys_auto_param
  sysAutoParamSaveOrUpdate: `api/system/sysAutoParam/saveOrUpdate`,
  sysAutoParamFindByTableID: `api/system/sysAutoParam/findByTableID/`,//{id}获取表的字段数据

  /********* 系统配置  end******/

  //博客管理--start
  //博客文章
  blogBlogQueryByCondition: `api/blog/blogBlog/querybycondition`,//查询
  blogBlogDeletebyid: `api/blog/blogBlog/deleteById/`,//单个删除
  blogBlogBatchDelete: `api/blog/blogBlog/batchdelete`,//批量删除
  blogBlogSaveOrUpdate: `api/blog/blogBlog/saveOrUpdate`,//更新或新增
  blogBlogListFieldQuery: `api/blog/blogBlog/listFieldQuery`,//列表查询


  blogProjectQueryByCondition: `api/blog/blogProject/querybycondition`,//查询
  blogProjectDeletebyid: `api/blog/blogProject/deleteById/`,//单个删除
  blogProjectBatchDelete: `api/blog/blogProject/batchdelete`,//批量删除
  blogProjectSaveOrUpdate: `api/blog/blogProject/saveOrUpdate`,//更新或新增
  blogProjectListFieldQuery: `api/blog/blogProject/listFieldQuery`,//列表查询
}
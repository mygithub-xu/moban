// 默认导出整个接口配置
export default {
  // 账号管理
  //特殊权限
  specialLogin: 'api/system/sysMenu/getnodelist',

  menu: `api/system/sysMenu/getnodelist`, // 菜单 // 账号管理 // 登陆
  exceldaochu: `api/system/sysLoginLog/excel`,//列表查询

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
  
  // 账号管理 sys_user
  login: `api/system/sysUser/login`, // 登陆
  logout: `api/system/sysUser/logout`, // 登出
  userGetList: `api/system/sysUser/getList`,//获得全部账号
  userQueryByCondition: `api/system/sysUser/queryByCondition`,//查询
  userDeletebyid: `api/system/sysUser/deleteById/`,//单个删除用户
  userBatchDelete: `api/system/sysUser/bacthdelete`,//批量删除用户
  userSaveOrUpdate: `api/system/sysUser/saveOrUpdate`,//更新或新增用户
  userResetPassword :`api/system/sysUser/resetPassword/`,//重置密码
  userQueryById :`api/system/sysUser/queryById/`,//根据id查询用户
  userQueryHeadPortraitById :`api/system/sysUser/queryHeadPortraitById/`,//根据id获取解密后的图片
  userUpdateUserInfo :`api/system/sysUser/updateUserInfo`,//用户修改自己的信息
  userUpdatePasswordById :`api/system/sysUser/updatePassword`,//用户修改自己的密码


  //登入日志 sys_login_log
  LoginLogQuery: `api/system/sysLoginLog/query`, // 条件查询
  LoginLogBatchdelete: `api/system/sysLoginLog/batchdelete`, // 条件查询


  //代码生成
  sysTableQueryByCondition: `api/system/sysTable/querybycondition`,//查询
  sysTableDeletebyid: `api/system/sysTable/deleteById/`,//单个删除
  sysTableBatchDelete: `api/system/sysTable/batchdelete`,//批量删除
  sysTableSaveOrUpdate: `api/system/sysTable/saveOrUpdate`,//更新或新增
  sysTableListFieldQuery: `api/system/sysTable/listFieldQuery`,//列表查询

  /********* 系统配置  end******/

  /********* 示例代码  start******/
  sysTestQueryByCondition: `api/test/sysTest/querybycondition`,//查询
  sysTestDeletebyid: `api/test/sysTest/deleteById/`,//单个删除
  sysTestBatchDelete: `api/test/sysTest/batchdelete`,//批量删除
  sysTestSaveOrUpdate: `api/test/sysTest/saveOrUpdate`,//更新或新增
  sysTestListFieldQuery: `api/test/sysTest/listFieldQuery`,//列表查询
  sysTestGetTreeData: `api/test/sysTest/getTreeData`,//树形数据查询
  sysTestExportExcel: `api/test/sysTest/exportExcel`,//导出
  sysTestImportExcel: `api/test/sysTest/importExcel`,//导入
  


  sysTestDetailQueryByCondition: `api/test/sysTestDetail/querybycondition`,//查询
  sysTestDetailDeletebyid: `api/test/sysTestDetail/deleteById/`,//单个删除
  sysTestDetailBatchDelete: `api/test/sysTestDetail/batchdelete`,//批量删除
  sysTestDetailSaveOrUpdate: `api/test/sysTestDetail/saveOrUpdate`,//更新或新增
  sysTestDetailListFieldQuery: `api/test/sysTestDetail/listFieldQuery`,//列表查询

  /********* 示例代码  end******/
  
}
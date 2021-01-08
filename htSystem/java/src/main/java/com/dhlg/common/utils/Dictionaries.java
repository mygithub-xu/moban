package com.dhlg.common.utils;

/**
 * @program: dhlg-project-master
 * @description: 字典管理
 * @author: hgd
 * @create: 2019-03-29 14:29
 **/

public interface Dictionaries {

    //代码生成--查询区域
    String LAYOUTTYPEQUERY = "1";
    //代码生成--编辑区域
    String LAYOUTTYPETABLE= "2";
    /**
     * token的key
     */
    String ACCESS_TOKEN = "Authorization";

    String FAILED_CODE= "400";

    //保存成功
    String SAVE_SUCCESS = "保存成功";

    //保存失败
    String SAVE_FAILED = "保存失败";

    //更新成功
    String UPDATE_SUCCESS = "更新成功";

    //更新失败
    String UPDATE_FAILED = "更新失败";

    //删除成功
    String DELETE_SUCCESS = "删除成功";

    //删除失败
    String DELETE_FAILED = "删除失败";

    //该数据已被别处引用，不能删除!
    String HAS_REFERENCE = "该数据已在别处引用，不能删除!";

    //该数据已被别处引用，不能更新!
    String HAS_UPDATE = "该数据已在别处引用，不能更新!";

    //编码重复
    String CODE_REPETITION = "编码重复，请重试！";

    //名称重复
    String NAME_REPETITION = "名称重复，请重新输入！";

    //编码断号
    String CODE_IS_NULL = "编码断号了！";

    //状态更新成功
    String STATUS_UPDATE_SUCCESS = "状态更新成功！";

    //状态更新失败
    String STATUS_UPDATE_FAILED = "状态更新失败！";

    //账号名为空
    String LOGIN_USER_IS_NULL = "账号名不能为空！";

    //重置密码成功
    String RESET_PASSWORD_SUCCESS ="重置密码成功";

    //重置密码失败
    String RESET_PASSWORD_FAILED = "重置密码失败！";

    //更新密码成功
    String UPDATE_PASSWORD_SUCCESS ="更新密码成功";

    //更新密码失败
    String UPDATE_PASSWORD_FAILED = "更新密码失败！";

    //更新密码失败
    String OLD_PASSWORD_ERROR = "原密码错误！";

    //发送信息失败
    String SEND_MESSAGE_ERROR = "发送信息失败！";

    //发送信息成功
    String SEND_MESSAGE_SUCCESS= "发送信息成功！";

    //发送成功
    String SEND_SUCCESS= "发送成功！";

    //发送失败
    String SEND_ERROR= "发送失败！";

    //验证码错误
    String YZM_ERROR= "验证码错误！";

    //验证码错误
    String LOGIN_SUCCESS= "登入成功！";

    //上传成功
    String UPLOAD_SUCCESS= "上传成功！";

    //上传失败
    String UPLOAD_ERROR= "上传失败！";

    //发布失败
    String RELEASE_ERROR= "发布失败！";

    //发布成功
    String RELEASE_SUCCESS= "发布成功！";

    //用户token
    String PREFIX_USER_TOKEN = "PREFIX_USER_TOKEN_";

    //用户信息
    String PREFIX_USER_ = "PREFIX_USER_";
}

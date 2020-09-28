package com.dhlg.module.system.sysUser.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dhlg.module.system.sysMenu.entity.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Lob;

/**
 * <p>
 * 
 * </p>
 *
 * @author xu
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 手机号码
     */
    @TableField("mobile_phone")
    private String mobilePhone;



    /**
     * 邮箱地址
     */
    @TableField("email")
    private String email;

    /**
     * 权限id
     */
    @TableField("role_ids")
    private String roleIds;


    /**
     * 生日
     */
    @TableField("birthday")
    private Date birthday;

    /**
     * 身份证号
     */
    @TableField("identity_card")
    private String identityCard;


    /**
     * 加密用的盐值
     */
    @TableField("salt")
    private String salt;

    /**
     * 登录名
     */
    @TableField("login_user")
    private String loginUser;


    /**
     * 状态
     */
    @TableField("status")
    private String status;


    /**
     * 描述
     */
    @TableField("description")
    private String description;


    @TableField("head_portrait")
    private String headPortrait;

    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField("update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    @TableField(exist=false)
    private String headPortraitSrc;

    /**
     * 按钮权限列表
     */
    @TableField(exist=false)
    private Set<String> buttonUrlList;

    /**
     * 菜单数据
     */
    @TableField(exist=false)
    private List<SysMenu> menuData;

    //验证盐
    @JSONField(serialize = false)
    public String getCredentialsSalt() {
        return "whfch" + salt;
    }

    public static final String ID = "id";

    public static final String USER_NAME = "user_name";

    public static final String GENDER = "gender";

    public static final String PASSWORD = "password";

    public static final String MOBILE_PHONE = "mobile_phone";

    public static final String EMAIL = "email";

    public static final String ROLEIDS = "role_ids";

    public static final String MAJOR = "major";

    public static final String BIRTHDAY = "birthday";

    public static final String IDENTITY_CARD = "identity_card";

    public static final String SALT = "salt";

    public static final String LOGIN_USER = "login_user";

    public static final String STATUS = "status";

    public static final String DESCRIPTION = "description";

    public static final String HEAD_PORTRAIT = "head_portrait";

    public static final String CREATE_USER = "create_user";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_TIME = "update_time";

}

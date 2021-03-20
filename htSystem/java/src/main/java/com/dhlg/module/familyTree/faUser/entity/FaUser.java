package com.dhlg.module.familyTree.faUser.entity;

import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fa_user")
public class FaUser implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
        *id
        */
        @TableId("id")
        private String id;

        /**
         * 账号状态
         */
        @TableField("status")
        private String status;
        /**
         * 创建人
         */
        @TableField("create_user")
        private String createUser;
        /**
         * 微信号
         */
        @TableField("wechat_number")
        private String wechatNumber;
        /**
         * 昵称
         */
        @TableField("name")
        private String name;
        /**
         * 手机号
         */
        @TableField("phone")
        private String phone;
        /**
         * 籍贯
         */
        @TableField("address")
        private String address;
        /**
         * 性别
         */
        @TableField("gender")
        private String gender;
        /**
         * 配偶id
         */
        @TableField("spouse_id")
        private String spouseId;
        /**
         * 创建时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("create_time")
        private Date createTime;
        /**
         * 父母id
         */
        @TableField("parent_id")
        private String parentId;
        /**
         * 更新时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("update_time")
        private Date updateTime;
        /**
         * 更新人
         */
        @TableField("update_user")
        private String updateUser;
        /**
         * 真实名称
         */
        @TableField("real_name")
        private String realName;
        /**
         * 个性签名
         */
        @TableField("signature")
        private String signature;
        /**
         * 头像
         */
        @TableField("head_url")
        private String headUrl;
        /**
         * 生存状态
         */
        @TableField("live_state")
        private String liveState;
        /**
         * 密码
         */
        @TableField("password")
        private String password;
        /**
         * 密码
         */
        @TableField("home_rank")
        private String homeRank;
        /**
         * 出生日期
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("birthday_date")
        private Date birthdayDate;
        /**
         * 称谓
         */
        @TableField(exist = false)
        private String call;
}

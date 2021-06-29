package com.dhlg.module.system.sysVerificationCode.entity;

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
@TableName("sys_verification_code")
public class SysVerificationCode implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
        *id
        */
        @TableId("id")
        private String id;

        /**
         * 创建时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("create_time")
        private Date createTime;
        /**
         * 创建人
         */
        @TableField("create_user")
        private String createUser;
        /**
         * 验证码
         */
        @TableField("code")
        private String code;
        /**
         * 过期时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("expire_Time")
        private Date expire4ime;
}

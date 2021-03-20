package com.dhlg.module.familyTree.faTable.entity;

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
@TableName("fa_table")
public class FaTable implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
        *id
        */
        @TableId("id")
        private String id;

        /**
         * 描述
         */
        @TableField("remark")
        private String remark;
        /**
         * 更新时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("update_time")
        private Date updateTime;
        /**
         * 族谱名
         */
        @TableField("name")
        private String name;
        /**
         * 创建人
         */
        @TableField("create_user")
        private String createUser;
        /**
         * 创建时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("create_time")
        private Date createTime;
        /**
         * 族谱号
         */
        @TableField("code")
        private String code;
        /**
         * 更新人
         */
        @TableField("update_user")
        private String updateUser;
        /**
         * 进入族谱密码
         */
        @TableField("password")
        private String password;
        /**
         * 创建人
         */
        @TableField(exist = false)
        private String createUserName;
        /**
         * 人数
         */
        @TableField(exist = false)
        private String userNum;

}

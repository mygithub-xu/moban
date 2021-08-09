package com.dhlg.module.test.tSendMessageFile.entity;

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
@TableName("t_send_message_file")
public class TSendMessageFile implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
        *id
        */
        @TableId("id")
        private String id;

        /**
         * 需求号
         */
        @TableField("demand_number")
        private String demandNumber;
        /**
         * 更新时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("update_time")
        private Date updateTime;
        /**
         * ios版本号
         */
        @TableField("ios_version")
        private String iosVersion;
        /**
         * 更新人
         */
        @TableField("update_user")
        private String updateUser;
        /**
         * 创建人
         */
        @TableField("create_user")
        private String createUser;
        /**
         * 文件是否生成
         */
        @TableField("is_create")
        private String isCreate;
        /**
         * 需求名称
         */
        @TableField("demand_name")
        private String demandName;
        /**
         * 安卓版本号
         */
        @TableField("android_version")
        private String androidVersion;
        /**
         * 文件是否删除
         */
        @TableField("is_delete")
        private String isDelete;
        /**
         * 创建时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("create_time")
        private Date createTime;
}

package com.dhlg.module.system.phoneModel.entity;

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
@TableName("phone_model")
public class PhoneModel implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
        *id
        */
        @TableId("id")
        private String id;

        /**
         * 终端厂商名称
         */
        @TableField("terminal_manufacturer")
        private String terminalManufacturer;
        /**
         * 终端类型名称
         */
        @TableField("terminal_name_type")
        private String terminalNameType;
        /**
         * 更新人
         */
        @TableField("update_user")
        private String updateUser;
        /**
         * 终端编号
         */
        @TableField("terminal_number")
        private String terminalNumber;
        /**
         * 创建时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("create_time")
        private Date createTime;
        /**
         * 终端名称
         */
        @TableField("terminal_name")
        private String terminalName;
        /**
         * 是否找到该类型
         */
        @TableField("result_type")
        private String resultType;
        /**
         * 创建人
         */
        @TableField("create_user")
        private String createUser;
        /**
         * 终端型号
         */
        @TableField("terminal_model")
        private String terminalModel;
        /**
         * 更新时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("update_time")
        private Date updateTime;
}

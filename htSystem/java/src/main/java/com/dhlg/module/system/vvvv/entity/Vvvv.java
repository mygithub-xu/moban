package com.dhlg.module.system.vvvv.entity;

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
@TableName("")
public class Vvvv implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId("id")
    private String id;

        /**
         * 创建人
         */
        @TableField("create_user")
        private String createUser;
        /**
         * vvvv
         */
        @TableField("vvvv")
        private String vvvv;
        /**
         * 更新时间
         */
        @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
        @TableField("update_time")
        private Date updateTime;
        /**
         * 创建时间
         */
        @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
        @TableField("create_time")
        private Date createTime;
        /**
         * 更新人
         */
        @TableField("update_user")
        private String updateUser;
        /**
         * 时间区域start
         */
        @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
        @TableField(exist = false)
        private Date createTimeFrom;

        @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
        @TableField(exist = false)
        private Date createTimeTo;
}

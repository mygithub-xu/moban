package com.dhlg.module.familyTree.tableUser.entity;

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
@TableName("fa_table_user")
public class TableUser implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
        *id
        */
        @TableId("id")
        private String id;

        /**
         * 用户id
         */
        @TableField("user_id")
        private String userId;
        /**
         * 族谱表id
         */
        @TableField("table_id")
        private String tableId;
}

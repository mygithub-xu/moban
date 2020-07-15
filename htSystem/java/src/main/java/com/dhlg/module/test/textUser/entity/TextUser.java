package com.dhlg.module.test.textUser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 *
 * </p>
 *
 * @author xu
 * @since 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("text_user")
public class TextUser implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId("id")
    private String id;

    /**
     * 地址
     */
    @TableField("address")
    private String address;
    /**
     * 性别
     */
    @TableField("gender")
    private String gender;
    /**
     * 名称
     */
    @TableField("name")
    private String name;

}

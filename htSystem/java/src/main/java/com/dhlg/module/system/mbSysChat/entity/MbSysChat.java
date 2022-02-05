package com.dhlg.module.system.mbSysChat.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mb_sys_chat")
public class MbSysChat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("from_id")
    private String fromId;

    @TableField("to_id")
    private String toId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("time")
    private Date time;


    @TableField("data")
    private String data;

    @TableField("type")
    private String type;

    //状态1是未读，2是已读
    @TableField("status")
    private String status;

    @TableField(exist=false)
    private Long timeStamp;

    //counts记录未读的信息记录
    @TableField(exist=false)
    private Long noReadNum;

    @TableField(exist=false)
    @Lob
    private byte[] headPortrait;

    @TableField(exist=false)
    private String userName;

    @TableField(exist=false)
    private String headPortraitSrc;




    public static final String ID = "id";

    public static final String FROM_ID = "from_id";

    public static final String TO_ID = "to_id";

    public static final String TIME = "time";

    public static final String DATA = "data";

    public static final String TYPE = "type";

    public static final String HEADPORTRAIT = "head_portrait";

    public static final String USERNAME = "user_name";

}

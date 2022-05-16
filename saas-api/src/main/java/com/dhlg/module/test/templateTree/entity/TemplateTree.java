package com.dhlg.module.test.templateTree.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("template_tree")
public class TemplateTree implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
         * 创建时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("create_time")
        private Date createTime;
        /**
         * 更新时间
         */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("update_time")
        private Date updateTime;
        /**
         * node
         */
        @TableField("node_depth")
        private int nodeDepth;
        /**
         * 父id
         */
        @TableField("ref_parent_id")
        private String refParentId;
        /**
         * nodesoft
         */
        @TableField("node_soft")
        private String nodeSoft;
        /**
         * 更新人
         */
        @TableField("update_user")
        private String updateUser;
        /**
         * node名称
         */
        @TableField("node_name")
        private String nodeName;
        /**
         * 主id
         */
        @TableField("master_id")
        private String masterId;
        /**
         * 创建人
         */
        @TableField("create_user")
        private String createUser;
        /**
         * 节点id
         */
        @TableField("node_id")
        private String nodeId;

        /**
         * 下级节点
         */
        @TableField(exist = false)
        private List<TemplateTree> children;
}

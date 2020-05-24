package com.excmmy.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Excmmy
 * @since 2020-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Review对象", description="")
public class Review implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "外键 user_id")
    private Integer uid;

    @ApiModelProperty(value = "外键 product_id")
    private Integer pid;

    @ApiModelProperty(value = "创建时间")
    @TableField("createDate")
    private Date createDate;

    @ApiModelProperty(value = "必备字段_创建时间")
    @TableField(value = "gmtCreate", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "必备字段_修改时间")
    @TableField(value = "gmtModified", fill = FieldFill.UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "必备字段_修改时间")
    @TableField("isDelete")
    @TableLogic
    private Integer isDelete;


}

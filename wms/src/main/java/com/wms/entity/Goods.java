package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 货名
     */
    private String name;

    /**
     * 仓库
     */
    private Integer storageId;

    /**
     * 分类
     */
    private Integer goodsTypeId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 备注
     */
    private String remark;


}

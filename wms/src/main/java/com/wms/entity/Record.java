package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 货品id
     */
    private Integer goodsId;

    /**
     * 取货人/补货人
     */
    private Integer userId;

    /**
     * 操作人id
     */
    private Integer adminId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 操作时间
     */
    private LocalDateTime createTime;

    /**
     * 备注
     */
    private String remark;


}

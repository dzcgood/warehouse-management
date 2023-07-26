package com.wms.entity;

import lombok.Data;

/**
 * @author DzcGood
 * @date 2023/7/26 - 15:48
 */
@Data
public class RecordRes extends Record{

    private String userName;
    private String adminName;
    private String goodsName;
    private String storageName;
    private String goodsTypeName;

}

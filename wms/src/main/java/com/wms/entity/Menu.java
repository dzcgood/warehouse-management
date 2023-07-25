package com.wms.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 菜单名字
     */
    private String menuName;

    /**
     * 菜单级别
     */
    private String menuLevel;

    /**
     * 菜单的父code
     */
    private String menuParentCode;

    /**
     * 点击触发的函数
     */
    private String menuClick;

    /**
     * 权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用
     */
    private String menuRight;

    private String menuComponent;

    private String menuIcon;


}

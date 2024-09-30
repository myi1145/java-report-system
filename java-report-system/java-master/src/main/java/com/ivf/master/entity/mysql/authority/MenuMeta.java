package com.ivf.master.entity.mysql.authority;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 菜单元数据实体类，映射数据库中的 "menu_meta" 表。
 */
@Data // Lombok 注解，自动生成 getters、setters、toString 等方法
@Entity // 表明这是一个 JPA 实体
@Table(name = "MENU_META") // 映射数据库表名
public class MenuMeta {

    @Id // 表明这是主键字段
    @Column(name = "MENU_ID") // 映射数据库列 "menu_id"
    private Integer menuId; // 菜单ID，外键，关联 menus 表的 id

    @Column(nullable = false) // 菜单标题，不允许为空
    private String title; // 菜单标题，用于展示

    private String icon; // 菜单图标
    private Boolean alwaysShow = false; // 是否总是显示，true 表示总是显示，false 不总是显示
    private Boolean noCashe = false;  // 是否缓存，为布尔类型，true(1) 表示不缓存，false(0) 表示缓存
    private Boolean affix = false; // 是否固定在标签栏，为布尔类型，true(1) 表示固定，false(0) 表示不固定



    @OneToOne(fetch = FetchType.LAZY, mappedBy = "meta")
    @JoinColumn(name = "MENU_ID", referencedColumnName = "ID", insertable = false, updatable = false) // 指定外键列
    private Menus menu; // 关联的菜单实体
}

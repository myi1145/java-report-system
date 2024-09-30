package com.ivf.master.entity.mysql.authority;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * 菜单实体类，映射数据库中的 "AUTHORITY.MENUS" 表。
 *
 * @author CAI
 * @date 2024-02-20
 */
@Data // Lombok 注解，自动生成getters、setters、toString等方法
@Entity // 表明这是一个 JPA 实体
@TableName("AUTHORITY.MENUS") // MyBatis Plus 注解，映射数据库表名
public class Menus {
    @Id // 表明这是主键字段
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略，自增
    private Integer id; // 菜单ID

    @Column(nullable = false) // 菜单路径，不允许为空
    private String path; // 菜单项的路径

    private String component; // 与菜单项关联的组件
    private String redirect; // 菜单项的重定向路径
    private String name; // 菜单项的名称

    @Column(name = "PARENT_ID") // 映射数据库列 "PARENT_ID"
    private Integer parentId; // 父菜单ID，顶级菜单此字段为 null

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 定义一对多关系
    @JoinColumn(name = "PARENT_ID") // 指定外键列
    private List<Menus> children; // 子菜单列表

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID", referencedColumnName = "MENU_ID")
    private MenuMeta meta; // 存储 meta 信息的 JsonNode 字段
}

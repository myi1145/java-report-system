package com.ivf.master.mapper.role;

import com.ivf.master.entity.mysql.authority.Menus;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色模块的Mapper接口，定义了与角色相关的数据库操作。
 * 使用MyBatis框架，该接口的实现将由框架动态生成。
 */
@Mapper
public interface RoleMapper {

    /**
     * 根据用户名查询用户可以访问的菜单ID列表。
     * 此方法通过用户的用户名，在数据库中查找与之相关联的菜单ID。
     *
     * @param username 用户名，用于查询与之相关联的菜单ID。
     * @return 一个包含菜单ID的ArrayList，每个ID都是Integer类型。
     */
    ArrayList<Integer> selectMenuId(String username);

    /**
     * 根据菜单ID列表查询对应的菜单详细信息。
     * 此方法接收一组菜单ID，返回这些菜单的详细信息，包括菜单的元数据。
     *
     * @param menuId 菜单ID的列表，用于查询对应的菜单详细信息。
     * @return 一个包含菜单详细信息的ArrayList，每个元素都是Menus实体类的实例。
     */
    ArrayList<Menus> selectRoleList(List menuId);
}

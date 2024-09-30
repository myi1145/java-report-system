package com.ivf.master.service.role;

import com.ivf.master.common.DataSourceContextHolder;
import com.ivf.master.entity.mysql.authority.Menus;
import com.ivf.master.mapper.role.RoleMapper;
import com.ivf.master.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色服务类，负责提供角色相关的业务逻辑处理。
 * 该服务类依赖RoleMapper来访问数据库，并按照业务需求组织角色菜单数据结构。
 *
 * @author CAI
 * @since 2024-02-26
 */
@Service
public class RoleService {

    private final RoleMapper roleMapper;

    /**
     * 构造函数，自动注入所需的组件。
     *
     * @param roleMapper 角色数据访问接口
     * @author CAI
     * @since 2024-02-26
     */
    @Autowired
    public RoleService(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    /**
     * 根据角色名称获取角色的菜单列表。
     * 此方法首先根据角色名查询该角色可访问的菜单ID，然后查询这些菜单的详细信息，并构建成菜单树形结构。
     * @param roleName 角色名称，用于查询可访问的菜单ID。
     * @return 返回构建好的菜单树形结构列表的响应实体。
     *
     * @author CAI
     * @since 2024-02-26
     */
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getRoleList(String roleName) {
        // 动态设置数据源为mysql，适用于多数据源场景
        DataSourceContextHolder.setDataSource("mysql");

        try {
            // 根据角色名称查询可访问的菜单ID列表
            List<Integer> menuId = roleMapper.selectMenuId(roleName);
            // 根据菜单ID列表查询对应的菜单详细信息
            List<Menus> allMenus = roleMapper.selectRoleList(menuId);

            // 构建菜单树形结构
            List<Map<String, Object>> menuTree = buildMenuTree(allMenus, null);

            // 封装并返回成功响应
            ApiResponse<List<Map<String, Object>>> listResponse = new ApiResponse<>(
                    ApiResponse.SUCCESS,
                    "success",
                    menuTree
            );
            return ResponseEntity.ok(listResponse);
        } catch (Exception e) {
            // 异常处理，封装并返回错误响应
            String errorMessage = "发生异常: " + e.getMessage();
            ApiResponse<List<Map<String, Object>>> errorResponse = new ApiResponse<>(
                    ApiResponse.FAILURE,
                    errorMessage,
                    null // 出错时返回空数据
            );

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * 递归构建菜单树形结构。
     * @param menus 所有菜单信息列表。
     * @param parentId 父菜单ID，用于递归构建子菜单结构。顶层菜单的parentId为null。
     * @return 构建好的菜单树形结构列表。
     * @author CAI
     * @since 2024-02-26
     */
    private List<Map<String, Object>> buildMenuTree(List<Menus> menus, Integer parentId) {
        return menus.stream()
                .filter(menu -> (parentId == null && menu.getParentId() == null) ||
                        (menu.getParentId() != null && menu.getParentId().equals(parentId)))
                .map(menu -> {
                    Map<String, Object> node = new LinkedHashMap<>();
                    node.put("path", menu.getPath());
                    node.put("component", menu.getComponent());
                    node.put("redirect", menu.getRedirect());
                    node.put("name", menu.getName());

                    // 处理meta信息
                    if (menu.getMeta() != null) {
                        Map<String, Object> meta = getStringObjectMap(menu);
                        node.put("meta", meta);
                    }

                    // 递归构建子菜单
                    List<Map<String, Object>> children = buildMenuTree(menus, menu.getId());
                    if (!children.isEmpty()) {
                        node.put("children", children);
                    }

                    return node;
                })
                .collect(Collectors.toList());
    }

    /**
     * 将菜单的元数据信息映射到Map对象。
     * @param menu 菜单实体，包含元数据信息。
     * @return 映射后的元数据Map对象。
     * @author CAI
     * @since 2024-02-26
     */
    private static Map<String, Object> getStringObjectMap(Menus menu) {
        Map<String, Object> meta = new LinkedHashMap<>();
        meta.put("title", menu.getMeta().getTitle());
        meta.put("icon", menu.getMeta().getIcon());
        // 转换alwaysShow为boolean值
        meta.put("alwaysShow", menu.getMeta().getAlwaysShow() != null && menu.getMeta().getAlwaysShow());
        meta.put("noCache", menu.getMeta().getNoCashe() != null && menu.getMeta().getNoCashe());
        meta.put("affix", menu.getMeta().getAffix() != null && menu.getMeta().getAffix());
        return meta;
    }


}

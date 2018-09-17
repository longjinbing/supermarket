package com.ljb.repository;

import com.ljb.entity.SysMenu;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * 菜单管理Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysMenuRepository extends ExpandJpaRepository<SysMenu,Integer>, JpaSpecificationExecutor<SysMenu> {

    List<SysMenu> findAllByTypeInAndStatus(Collection<Integer> typeList,Integer status);

    List<SysMenu> findAllByTypeIn(Collection<Integer> typeList);

    List<SysMenu> findAllByType(Integer type);

    @Query(value = "SELECT DISTINCT(sys_menu.`id`),sys_menu.* FROM sys_user_role,sys_role_menu,sys_menu WHERE sys_menu.`id`=sys_role_menu.`menu_id`AND sys_role_menu.`role_id`=sys_user_role.`role_id`AND sys_user_role.`user_id`=?1",nativeQuery = true)
    List<SysMenu> findAllByUserId(Integer userId);

    @Query(value = "SELECT DISTINCT(sys_menu.`id`),sys_menu.* FROM sys_user_role,sys_role_menu,sys_menu WHERE sys_menu.`id`=sys_role_menu.`menu_id` AND sys_user_role.`user_id`=?1 AND sys_role_menu.`role_id`=sys_user_role.`role_id`AND sys_user_role.`user_id`=?2",nativeQuery = true)
    List<SysMenu> findAllAndStatusByUserId(Integer status,Integer userId);

    @Query(value = "SELECT DISTINCT(sys_menu.`id`),sys_menu.* FROM sys_role_menu,sys_menu WHERE sys_menu.`id`=sys_role_menu.`menu_id`AND sys_role_menu.`role_id`=?1 ",nativeQuery = true)
    List<SysMenu> findAllByRoleId(Integer roleId);

}

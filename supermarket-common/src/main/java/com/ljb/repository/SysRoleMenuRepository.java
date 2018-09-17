package com.ljb.repository;

import com.ljb.entity.SysRoleMenu;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色与菜单Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysRoleMenuRepository extends ExpandJpaRepository<SysRoleMenu,Integer>, JpaSpecificationExecutor<SysRoleMenu> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM sys_role_menu WHERE role_id =?1",nativeQuery = true)
    void deleteByRoleId(Integer roleId);

}

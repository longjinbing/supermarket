package com.ljb.repository;

import com.ljb.entity.SysRole;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 角色管理Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysRoleRepository extends ExpandJpaRepository<SysRole,Integer>, JpaSpecificationExecutor<SysRole> {

    @Query(value = "SELECT sys_role . * FROM sys_role, sys_user_role WHERE sys_user_role.`role_id` = sys_role.`id` AND sys_user_role.`user_id` =?1",nativeQuery = true)
    List<SysRole> roleByUserId(Integer id);


}

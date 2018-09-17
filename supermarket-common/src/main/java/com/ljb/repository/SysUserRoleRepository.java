package com.ljb.repository;

import com.ljb.entity.SysUserRole;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户与角色Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysUserRoleRepository extends ExpandJpaRepository<SysUserRole,Integer>, JpaSpecificationExecutor<SysUserRole> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM sys_user_role WHERE user_id =?1",nativeQuery = true)
    void deleteByRoleId(Integer userId);

}

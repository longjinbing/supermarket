package com.ljb.repository;

import com.ljb.entity.SysRoleDept;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 角色与部门对应关系Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysRoleDeptRepository extends ExpandJpaRepository<SysRoleDept,Integer>, JpaSpecificationExecutor<SysRoleDept> {

}

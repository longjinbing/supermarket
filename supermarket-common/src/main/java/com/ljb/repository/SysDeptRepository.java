package com.ljb.repository;

import com.ljb.entity.SysDept;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 部门管理Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysDeptRepository extends ExpandJpaRepository<SysDept,Integer>, JpaSpecificationExecutor<SysDept> {

}

package com.ljb.repository;

import com.ljb.entity.SysRegion;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 全球地址Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysRegionRepository extends ExpandJpaRepository<SysRegion,Integer>, JpaSpecificationExecutor<SysRegion> {

}

package com.ljb.repository;

import com.ljb.entity.StorePosition;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 货架管理Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:21
 */
public interface StorePositionRepository extends ExpandJpaRepository<StorePosition,Integer>, JpaSpecificationExecutor<StorePosition> {

}

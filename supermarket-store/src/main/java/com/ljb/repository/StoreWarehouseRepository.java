package com.ljb.repository;

import com.ljb.entity.StoreWarehouse;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 仓库管理Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:12
 */
public interface StoreWarehouseRepository extends ExpandJpaRepository<StoreWarehouse,Integer>, JpaSpecificationExecutor<StoreWarehouse> {

}

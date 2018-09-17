package com.ljb.repository;

import com.ljb.entity.StoreSupplier;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 供应商管理Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:01
 */
public interface StoreSupplierRepository extends ExpandJpaRepository<StoreSupplier,Integer>, JpaSpecificationExecutor<StoreSupplier> {

}

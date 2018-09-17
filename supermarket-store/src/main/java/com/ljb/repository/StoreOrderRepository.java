package com.ljb.repository;

import com.ljb.entity.StoreOrder;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 订单管理Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:33
 */
public interface StoreOrderRepository extends ExpandJpaRepository<StoreOrder,Integer>, JpaSpecificationExecutor<StoreOrder> {

}

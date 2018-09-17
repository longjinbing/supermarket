package com.ljb.repository;

import com.ljb.entity.StoreCategory;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 商品分类Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
public interface StoreCategoryRepository extends ExpandJpaRepository<StoreCategory,Integer>, JpaSpecificationExecutor<StoreCategory> {

}

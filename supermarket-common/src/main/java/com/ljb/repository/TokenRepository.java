package com.ljb.repository;

import com.ljb.entity.Token;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户TokenRepository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface TokenRepository extends ExpandJpaRepository<Token,Integer>, JpaSpecificationExecutor<Token> {

}

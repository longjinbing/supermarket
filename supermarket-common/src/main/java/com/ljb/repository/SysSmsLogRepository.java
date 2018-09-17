package com.ljb.repository;

import com.ljb.entity.SysSmsLog;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 短信日志Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysSmsLogRepository extends ExpandJpaRepository<SysSmsLog,Integer>, JpaSpecificationExecutor<SysSmsLog> {

}

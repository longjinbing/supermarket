package com.ljb.repository;

import com.ljb.entity.SysConfig;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 系统配置信息表Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface SysConfigRepository extends ExpandJpaRepository<SysConfig,Integer>, JpaSpecificationExecutor<SysConfig> {

}

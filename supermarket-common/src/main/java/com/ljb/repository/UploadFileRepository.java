package com.ljb.repository;

import com.ljb.entity.UploadFile;
import com.ljb.jpa.ExpandJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 上传文件Repository
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
public interface UploadFileRepository extends ExpandJpaRepository<UploadFile,Integer>, JpaSpecificationExecutor<UploadFile> {

}

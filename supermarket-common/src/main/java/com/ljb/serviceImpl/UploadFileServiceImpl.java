package com.ljb.serviceImpl;

import com.ljb.entity.UploadFile;
import com.ljb.repository.UploadFileRepository;
import com.ljb.service.UploadFileService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 上传文件Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Autowired
    private UploadFileRepository uploadFileRepository;
    
    @Override
    public UploadFile queryObject(Integer id) {
        return uploadFileRepository.getOne(id);
    }

    @Override
    public Page<UploadFile> queryList(Map<String, Object> map) {
       Query query = new Query<UploadFile>(map);
       return uploadFileRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<UploadFile> queryAll(Map<String, Object> map){
    	Query query = new Query<UploadFile>(map);
       return uploadFileRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<UploadFile>(map);
       return uploadFileRepository.count(query.getSpecification());
    }
    
    
    @Override
	public UploadFile save(UploadFile entity) {
		return uploadFileRepository.save(entity);
	}

    @Override
    public UploadFile update(UploadFile entity) {
        return uploadFileRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	uploadFileRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}

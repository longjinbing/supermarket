package com.ljb.serviceImpl;

import com.ljb.entity.SysDept;
import com.ljb.repository.SysDeptRepository;
import com.ljb.service.SysDeptService;
import com.ljb.shiro.ShiroUtils;
import com.ljb.util.Constant;
import com.ljb.util.Query;
import com.ljb.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.swing.text.html.HTMLDocument;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 部门管理Service实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptRepository sysDeptRepository;

    @Override
    public Collection<Object> zTreeList(Integer userId){
        List<SysDept> depts;
        depts=sysDeptRepository.findAll();
        SysDept sysDept=new SysDept();
        sysDept.setId(0);
        sysDept.setParentId(-1);
        sysDept.setName("总公司");
        depts.add(sysDept);
        return TreeUtils.buildTree(depts);
    }


    @Override
    public SysDept queryObject(Integer id) {
        return sysDeptRepository.findOne(id);
    }

    @Override
    public Page<SysDept> queryList(Map<String, Object> map) {
       Query query = new Query<SysDept>(map);
        Specification<SysDept> specification=new Specification<SysDept>() {
            @Override
            public Predicate toPredicate(Root<SysDept> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> name = root.get("parentId");
                criteriaQuery.where(criteriaBuilder.equal(name, map.get("id")));
                return null;
            }
        };
       return sysDeptRepository.findAll(query.getSpecification().and(specification), query.getPageable());
    }
    
     @Override
    public List<SysDept> queryAll(Map<String, Object> map){
    	Query query = new Query<SysDept>(map);
       return sysDeptRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<SysDept>(map);
       return sysDeptRepository.count(query.getSpecification());
    }
    
    
    @Override
	public SysDept save(SysDept entity) {
		entity.setCreateId(ShiroUtils.getSysUserId());
		return sysDeptRepository.save(entity);
	}

    @Override
    public SysDept update(SysDept entity) {
        return sysDeptRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	sysDeptRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}

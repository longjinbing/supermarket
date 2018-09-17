package com.ljb.serviceImpl;

import com.ljb.entity.Token;
import com.ljb.repository.TokenRepository;
import com.ljb.service.TokenService;
import com.ljb.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户TokenService实现类
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    
    @Override
    public Token queryObject(Integer id) {
        return tokenRepository.getOne(id);
    }

    @Override
    public Page<Token> queryList(Map<String, Object> map) {
       Query query = new Query<Token>(map);
       return tokenRepository.findAll(query.getSpecification(), query.getPageable());
    }
    
     @Override
    public List<Token> queryAll(Map<String, Object> map){
    	Query query = new Query<Token>(map);
       return tokenRepository.findAll(query.getSpecification());
    }

    @Override
    public Long queryTotal(Map<String, Object> map) {
        Query query = new Query<Token>(map);
       return tokenRepository.count(query.getSpecification());
    }
    
    
    @Override
	public Token save(Token entity) {
		return tokenRepository.save(entity);
	}

    @Override
    public Token update(Token entity) {
        return tokenRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
    	tokenRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
      for(Integer id: ids ){
      	delete(id);
      }
    }
}

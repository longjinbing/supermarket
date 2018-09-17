import com.ljb.config.DatabaseConfig;
import com.ljb.entity.SysDept;
import com.ljb.entity.SysRole;
import com.ljb.entity.SysUser;
import com.ljb.repository.SysDeptRepository;
import com.ljb.repository.SysRoleRepository;
import com.ljb.repository.SysUserRepository;
import com.ljb.util.SHA256Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/8/30<br>
 * 描述: <br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
public class initJpaConfiguration {

    private static Logger logger= LoggerFactory.getLogger(initJpaConfiguration.class);

    @Autowired
    SysUserRepository sysUserRepository;
    @Autowired
    SysRoleRepository sysRoleRepository;
    @Autowired
    SysDeptRepository sysDeptRepository;

    @Before
    public void init() {
        sysDeptRepository.deleteAll();
        sysRoleRepository.deleteAll();
        sysUserRepository.deleteAll();

        SysDept sysDept = new SysDept();
        sysDept.setName("总部");
        sysDept.setParentId(0);
        sysDeptRepository.save(sysDept);

        SysUser sysUser = new SysUser();
        sysUser.setUsername("longjinbin");
        sysUser.setPassword(SHA256Util.getSHA256Str("123456"));
        sysUser.setStatus(1);
        sysUser.setId(1);
        sysUserRepository.save(sysUser);

        SysRole sysRole = new SysRole();
        sysRole.setName("admin");
        sysRoleRepository.save(sysRole);
    }

 @Test
    public void test(){
     Pageable pageable= PageRequest.of(0, 10, new Sort(Sort.Direction.ASC,"id"));
     Page<SysUser> page=sysUserRepository.findAll(pageable);
     for (SysUser sysUser:page.getContent()){
         logger.info("username:{}}",sysUser.getUsername());
     }
    }
}

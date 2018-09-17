package com.ljb.shiro;

import java.util.Arrays;
import java.util.List;

import com.ljb.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ljb.entity.SysUser;
import com.ljb.service.SysMenuService;
import com.ljb.service.SysRoleService;
import com.ljb.service.SysUserService;

public class UserRealm extends AuthorizingRealm {
	
	private Logger logger=LoggerFactory.getLogger(UserRealm.class);
	
	@Autowired
	private ShiroService shiroService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    	Session session=SecurityUtils.getSubject().getSession();
    	SysUser sysUser=null;
        try {sysUser=(SysUser)session.getAttribute("curUser");
        }catch(Exception e) {
        	String username=(String)principalCollection.getPrimaryPrincipal();
        	sysUser=shiroService.findByUsername(username);
        	session.setAttribute("curUser", sysUser);
        }
        List<String> permissions=shiroService.permissions(sysUser.getId());
        List<String> roles=shiroService.roles(sysUser.getId());
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }
    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        String password= new String((char[])authenticationToken.getCredentials());
		SysUser sysUser=shiroService.findByUsernameAndPassword(username,password );
		if(sysUser==null) {
			return null;
		}
		if(sysUser.getStatus()!=1) {
			throw new LockedAccountException();
		}
		Session session=SecurityUtils.getSubject().getSession();
		if(session==null||session.getAttribute("curUser")==null) {
			session.setAttribute("curUser", sysUser);
		}
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser.getUsername(),sysUser.getPassword(),this.getName());
        return info;
    }
}

package com.ljb.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import com.ljb.entity.SysUser;
import com.ljb.service.SysUserService;

/**
 * Shiro工具类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月12日 上午9:49:19
 */
public class ShiroUtils {
	
	@Autowired
	private SysUserService sysUserService;

	/* 验证是否登陆
    * 
    * org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY ; true
    * org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY ; com.hncxhd.bywl.entity.manual.UserInfo@533752b2
    */
   public boolean isAuthenticated(String sessionID,HttpServletRequest request,HttpServletResponse response){
       boolean status = false;

       SessionKey key = new WebSessionKey(sessionID,request,response);
       try{
           Session se = SecurityUtils.getSecurityManager().getSession(key);
           Object obj = se.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
           if(obj != null){
               status = (Boolean) obj;
           }
       }catch(Exception e){
           e.printStackTrace();
       }finally{
           Session se = null;
           Object obj = null;
       }

       return status;
   }
   
   
   public SysUser getSysUserInfo(){
      try{
           Integer adminId=(Integer)SecurityUtils.getSubject().getPrincipal();
           return sysUserService.queryObject(adminId);
       }catch(Exception e){
           e.printStackTrace();
       }finally{
       }
       return null;
   }
   
   public SysUser getSysUser() {
       SysUser sysUser=(SysUser)SecurityUtils.getSubject().getSession().getAttribute("curUser");
       if(sysUser==null) {
       	if(!SecurityUtils.getSubject().isAuthenticated()) {
       		throw new AccountException("登录状态异常");
       	}
       	String username=(String)SecurityUtils.getSubject().getPrincipal();
       	sysUser=null;
       	if(sysUser==null) {
       		throw new AccountException("登录状态异常");
       	}
       	SecurityUtils.getSubject().getSession().setAttribute("curUser", sysUser);
       }
       return sysUser;
   }
   
   public static Integer getSysUserId() {
	   return ((SysUser)SecurityUtils.getSubject().getSession().getAttribute("curUser")).getId();
   }

}

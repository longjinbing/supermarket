package com.ljb.serviceImpl;

import com.ljb.entity.SysMenu;
import com.ljb.entity.SysRole;
import com.ljb.entity.SysUser;
import com.ljb.repository.SysMenuRepository;
import com.ljb.repository.SysRoleRepository;
import com.ljb.repository.SysUserRepository;
import com.ljb.service.ShiroService;
import com.ljb.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/14<br>
 * 描述: <br>
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Override
    public SysUser findByUsername(String username){
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public SysUser findByUsernameAndPassword(String username,String password){
        return sysUserRepository.findByUsernameAndPassword(username,password );
    }

    @Override
    public List<String> permissions(Integer userId){
        List<String> permission=new ArrayList<String>();
        List<SysMenu> menus;
        if(userId.equals(Constant.SUPER_ADMIN)) {
            Collection<Integer> typeList=new ArrayList<>();
            typeList.add(1);
            typeList.add(2);
            menus=sysMenuRepository.findAllByTypeIn(typeList);
        }else {
            /*menus=sysMenuRepository.menuByUserId(userId);*/
            menus=null;
        }
        for (SysMenu sysMenu : menus) {
            if(sysMenu.getType()==2) {
                permission.add(sysMenu.getPerms());
            }else if(sysMenu.getType()==1) {
                permission.add(sysMenu.getUrl());
            }
        }
        return permission;
    }

    @Override
    public List<String> roles(Integer userId){
        List<SysRole> roles;
        List<String> roleNames=new ArrayList<String>();
        //判断是否是超级管理员
        if(userId==Constant.SUPER_ADMIN) {
            roleNames.add("超级管理员");
        }else {
            roles=sysRoleRepository.roleByUserId(userId);
            for (SysRole role : roles) {
                roleNames.add(role.getName());
            }
        }
        return roleNames;
    }
}

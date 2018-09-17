package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.SysUser;
import com.ljb.service.SysUserService;
import com.ljb.util.PageUtil;
import com.ljb.util.R;
import com.ljb.util.SHA256Util;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@RestController
@RequestMapping("sysuser")
@Desc(group="系统设置",name="系统用户",type=1,url="admin/sysuser.html")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysuser:list")
    @Desc(name="系统用户列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(sysUserService.queryList(params)));
    }

    @RequestMapping("/resetpass")
    @RequiresPermissions("sysuser:update")
    @Desc(name="重置密码",type=2)
    public R update(@RequestParam("userId") Integer userId,@RequestParam("password") String password,@RequestParam("confirmpassword") String confirmpassword) {
        if(!password.equals(confirmpassword)||password.length()<6){
            return R.error(-1, "密码不符合要求，请重新尝试");
        }
        SysUser sysUser=new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(SHA256Util.getSHA256Str(password));
        sysUserService.update(sysUser);
        return R.ok("重置成功");
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysuser:info")
    @Desc(name="查看系统用户",type=2)
    public R info(@PathVariable("id") Integer id) {
        SysUser sysUser = sysUserService.queryObject(id);

        return R.ok().put("sysUser", sysUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysuser:save")
    @Desc(name="添加系统用户",type=2)
    public R save(@RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysuser:update")
    @Desc(name="修改系统用户",type=2)
    public R update(@RequestBody SysUser sysUser) {
        sysUserService.update(sysUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysuser:delete")
    @Desc(name="删除系统用户",type=2)
    public R delete(@RequestBody Integer[]ids) {
        sysUserService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="系统用户导出",type=2)
    @RequiresPermissions("sysuser:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysUser> list = sysUserService.queryAll(params);

        return R.ok().put("list", list);
    }
}

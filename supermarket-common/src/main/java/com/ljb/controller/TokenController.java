package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.Token;
import com.ljb.service.TokenService;
import com.ljb.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户TokenController
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@RestController
@RequestMapping("token")
@Desc(group="系统设置",name="用户Token",type=1,url="admin/token.html",status = 0)
public class TokenController extends AbstractController {
    @Autowired
    private TokenService tokenService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("token:list")
    @Desc(name="用户Token列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("page", tokenService.queryList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("token:info")
    @Desc(name="查看用户Token",type=2)
    public R info(@PathVariable("id") Integer id) {
        Token token = tokenService.queryObject(id);

        return R.ok().put("token", token);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("token:save")
    @Desc(name="添加用户Token",type=2)
    public R save(@RequestBody Token token) {
        tokenService.save(token);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("token:update")
    @Desc(name="修改用户Token",type=2)
    public R update(@RequestBody Token token) {
        tokenService.update(token);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("token:delete")
    @Desc(name="删除用户Token",type=2)
    public R delete(@RequestBody Integer[]ids) {
        tokenService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="用户Token导出",type=2)
    @RequiresPermissions("token:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<Token> list = tokenService.queryAll(params);

        return R.ok().put("list", list);
    }
}

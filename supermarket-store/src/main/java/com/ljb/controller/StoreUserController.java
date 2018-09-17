package com.ljb.controller;

import java.util.List;
import java.util.Map;

import com.ljb.util.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ljb.entity.StoreUser;
import com.ljb.service.StoreUserService;
import com.ljb.util.R;
import com.ljb.annotation.Desc;

/**
 * 会员管理Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:02
 */
@RestController
@RequestMapping("storeuser")
@Desc(group="客户管理",name="会员管理",type=1,url="shop/storeuser.html")
public class StoreUserController extends AbstractController {
    @Autowired
    private StoreUserService storeUserService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storeuser:list")
    @Desc(name="会员管理列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(storeUserService.queryList(params)));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("storeuser:info")
    @Desc(name="查看会员管理",type=2)
    public R info(@PathVariable("id") Integer id) {
        StoreUser storeUser = storeUserService.queryObject(id);

        return R.ok().put("storeUser", storeUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("storeuser:save")
    @Desc(name="添加会员管理",type=2)
    public R save(@RequestBody StoreUser storeUser) {
        storeUserService.save(storeUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("storeuser:update")
    @Desc(name="修改会员管理",type=2)
    public R update(@RequestBody StoreUser storeUser) {
        storeUserService.update(storeUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("storeuser:delete")
    @Desc(name="删除会员管理",type=2)
    public R delete(@RequestBody Integer[]ids) {
        storeUserService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="会员管理导出",type=2)
    @RequiresPermissions("storeuser:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StoreUser> list = storeUserService.queryAll(params);

        return R.ok().put("data", list);
    }
}

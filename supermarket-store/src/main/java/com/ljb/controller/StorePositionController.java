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
import com.ljb.entity.StorePosition;
import com.ljb.service.StorePositionService;
import com.ljb.util.R;
import com.ljb.annotation.Desc;

/**
 * 货架管理Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:21
 */
@RestController
@RequestMapping("storeposition")
@Desc(group="货架管理",name="货架管理",type=1,url="shop/storeposition.html")
public class StorePositionController extends AbstractController {
    @Autowired
    private StorePositionService storePositionService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storeposition:list")
    @Desc(name="货架管理列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(storePositionService.queryList(params)));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("storeposition:info")
    @Desc(name="查看货架管理",type=2)
    public R info(@PathVariable("id") Integer id) {
        StorePosition storePosition = storePositionService.queryObject(id);

        return R.ok().put("storePosition", storePosition);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("storeposition:save")
    @Desc(name="添加货架管理",type=2)
    public R save(@RequestBody StorePosition storePosition) {
        storePositionService.save(storePosition);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("storeposition:update")
    @Desc(name="修改货架管理",type=2)
    public R update(@RequestBody StorePosition storePosition) {
        storePositionService.update(storePosition);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("storeposition:delete")
    @Desc(name="删除货架管理",type=2)
    public R delete(@RequestBody Integer[]ids) {
        storePositionService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="货架管理导出",type=2)
    @RequiresPermissions("storeposition:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StorePosition> list = storePositionService.queryAll(params);

        return R.ok().put("data", list);
    }
}

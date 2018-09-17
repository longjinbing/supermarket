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
import com.ljb.entity.StoreGoods;
import com.ljb.service.StoreGoodsService;
import com.ljb.util.R;
import com.ljb.annotation.Desc;

/**
 * 商品管理Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
@RestController
@RequestMapping("storegoods")
@Desc(group="商品中心",name="商品管理",type=1,url="shop/storegoods.html")
public class StoreGoodsController extends AbstractController {
    @Autowired
    private StoreGoodsService storeGoodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storegoods:list")
    @Desc(name="商品管理列表",type=2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(storeGoodsService.queryList(params)));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("storegoods:info")
    @Desc(name="查看商品管理",type=2)
    public R info(@PathVariable("id") Integer id) {
        StoreGoods storeGoods = storeGoodsService.queryObject(id);

        return R.ok().put("storeGoods", storeGoods);
    }

    @RequestMapping("/goodssn")
    public R info(@RequestParam String sn) {
        StoreGoods storeGoods = storeGoodsService.findByGoodsSn(sn);
        return R.ok().put("storeGoods", storeGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("storegoods:save")
    @Desc(name="添加商品管理",type=2)
    public R save(@RequestBody StoreGoods storeGoods) {
        storeGoodsService.save(storeGoods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("storegoods:update")
    @Desc(name="修改商品管理",type=2)
    public R update(@RequestBody StoreGoods storeGoods) {
        storeGoodsService.update(storeGoods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("storegoods:delete")
    @Desc(name="删除商品管理",type=2)
    public R delete(@RequestBody Integer[]ids) {
        storeGoodsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name="商品管理导出",type=2)
    @RequiresPermissions("storegoods:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StoreGoods> list = storeGoodsService.queryAll(params);

        return R.ok().put("data", list);
    }
}

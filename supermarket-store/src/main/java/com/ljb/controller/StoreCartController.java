package com.ljb.controller;

import com.ljb.annotation.Desc;
import com.ljb.entity.StoreCart;
import com.ljb.entity.StoreGoods;
import com.ljb.entity.StoreOrder;
import com.ljb.service.StoreCartService;
import com.ljb.service.StoreGoodsService;
import com.ljb.service.StoreOrderService;
import com.ljb.util.IdUtil;
import com.ljb.util.PageUtil;
import com.ljb.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 下单处理Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:33
 */
@RestController
@RequestMapping("storecart")
@Desc(group = "订单中心", name = "下单处理", type = 1, url = "shop/storecart.html")
public class StoreCartController extends AbstractController {
    @Autowired
    private StoreCartService storeCartService;
    @Autowired
    private StoreGoodsService storeGoodsService;
    @Autowired
    private StoreOrderService storeOrderService;


    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storecart:list")
    @Desc(name = "下单处理列表", type = 2)
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", new PageUtil(storeCartService.queryList(params)));
    }

    @RequestMapping("/number")
    public R list(@RequestParam Integer i,@RequestParam Integer id) {
        //查询列表数据
        StoreCart storeCart = storeCartService.queryObject(id);
        StoreGoods storeGoods=storeGoodsService.queryObject(storeCart.getGoodsId());
        if(storeCart.getNumber()+i>1&&storeCart.getNumber()+i<storeGoods.getNumber()) {
            storeCart.setNumber(storeCart.getNumber() + i);
            storeCartService.save(storeCart);
        }else{
            return R.error(403,"商品库存不足");
        }
        return R.ok();
    }

    @RequestMapping("/query/{type}")
    public R list(@PathVariable Integer type) {
        if (type == 1 || type==3) {
            StoreCart storeCart = storeCartService.findByType(type);
            if(storeCart!=null) {
                storeCartService.delete(storeCart.getId());
                return R.ok().put("data", storeCart);
            }
        } else if (type == 2) {
            List<StoreCart> list = storeCartService.findAllByType(type);
            return R.ok().put("data", list);
        }
        return R.error();
    }

    @RequestMapping("/receive")
    public R receive(@RequestParam String sn, @RequestParam Integer type) {
        StoreCart storeCart = new StoreCart();
        if (type == 1 || type==3) {
            storeCart = storeCartService.findByTypeAndGoodsSn(type, sn);
            if (storeCart != null) {
                storeCartService.delete(storeCart.getId());
            }
            storeCart = new StoreCart();
            storeCart.setGoodsSn(sn);
            storeCart.setType(type);
            storeCartService.save(storeCart);
            return R.ok();
        } else if (type == 2) {
            StoreGoods storeGoods = storeGoodsService.findByGoodsSn(sn);
            if (storeGoods == null) {
                return R.error(404, "不存在此商品");
            }
           storeCart = storeCartService.findByTypeAndGoodsSn(type, sn);
            if (storeCart == null) {
                storeCart = new StoreCart();
                storeCart.setGoodsId(storeGoods.getId());
                storeCart.setGoodsName(storeGoods.getTitle());
                storeCart.setGoodsSn(sn);
                //作为备注使用 type=1录入 type=2购买
                storeCart.setType(type);
                if(storeGoods.getNumber()<=0){
                    storeCart.setNumber(1);
                    storeCart.setPicUrl("库存不足");
                }else{
                    storeCart.setNumber(1);
                }
                storeCart.setPrice(storeGoods.getPrice());
                storeCart.setMarketPrice(storeGoods.getMarketPrice());
            } else {
                storeCart.setNumber(storeCart.getNumber() + 1);
            }
            storeCartService.save(storeCart);
            return R.ok().put("storeGoods", storeGoods);
        }
        return R.error();
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("storecart:info")
    @Desc(name = "查看下单处理", type = 2)
    public R info(@PathVariable("id") Integer id) {
        StoreCart storeCart = storeCartService.queryObject(id);

        return R.ok().put("storeCart", storeCart);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("storecart:save")
    @Desc(name = "添加下单处理", type = 2)
    public R save(@RequestBody StoreCart storeCart) {
        storeCartService.save(storeCart);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("storecart:update")
    @Desc(name = "修改下单处理", type = 2)
    public R update(@RequestBody StoreCart storeCart) {
        storeCartService.update(storeCart);

        return R.ok();
    }

    @RequestMapping("/order")
    public R order(@RequestBody Integer[] ids) {
        if(ids.length==0){
            return R.error();
        }
        StoreOrder storeOrder=new StoreOrder();
        List<StoreCart> list=new ArrayList<>();
        BigDecimal sum =new BigDecimal(0);
        for (Integer id:ids) {
            StoreCart storeCart=storeCartService.queryObject(id);
            sum=sum.add(storeCart.getPrice().multiply(new BigDecimal(storeCart.getNumber())));
            list.add(storeCart);
        }
        storeOrder.setParentId(0);
        storeOrder.setOrderSn(IdUtil.createIdByDate());
        storeOrder.setGoodsPrice(sum);
        storeOrder.setOrderPrice(sum);
        storeOrder.setStatus(1);
        storeOrderService.save(storeOrder);
        for (StoreCart item:list) {
            StoreOrder sonOrder=new StoreOrder();
            storeOrder.setStatus(1);
            sonOrder.setOrderPrice(item.getPrice());
            sonOrder.setParentId(storeOrder.getId());
            sonOrder.setGoodsPrice(item.getPrice());
            sonOrder.setOrderSn(item.getGoodsSn());
            storeOrderService.save(sonOrder);
            StoreGoods storeGoods=storeGoodsService.queryObject(item.getGoodsId());
            storeGoods.setNumber(storeGoods.getNumber()-item.getNumber());
            storeGoodsService.update(storeGoods);
        }
        storeCartService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("storecart:delete")
    @Desc(name = "删除下单处理", type = 2)
    public R delete(@RequestBody Integer[] ids) {
        storeCartService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @Desc(name = "下单处理导出", type = 2)
    @RequiresPermissions("storecart:export")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StoreCart> list = storeCartService.queryAll(params);

        return R.ok().put("data", list);
    }
}

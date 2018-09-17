package com.ljb.controller;
import com.ljb.annotation.Desc;
import com.ljb.service.SysGeneratorService;
import com.ljb.util.PageUtil;
import com.ljb.util.R;
import com.ljb.util.Query;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年1月3日 下午6:35:28
 */
@Controller
@RequestMapping("generator")
@Desc(group="开发工具",name="代码生成",type=1,url="generator/codegenerator.html")
public class SysGeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     */
    @RequestMapping("/tablelist")
    @Desc(name="数据库表信息",type=2)
    public @ResponseBody
    R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
    	Query query = new Query(params);
        List<Map<String, Object>> list = sysGeneratorService.queryList(query);
        Long total = sysGeneratorService.queryTotal(query);
        PageUtil pageUtil = new PageUtil(list,total ,null,null);
        return R.ok().put("data", pageUtil);
    }

    /**
     * 生成代码
     */
    @RequestMapping("/generatrorcode")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取表名，不进行xss过滤
        Enumeration params=request.getParameterNames();
        Map<String, Object> map=new HashMap<String, Object>();
        while(params.hasMoreElements()) {
        	String key=(String) params.nextElement();
        	String value=request.getParameter(key);
        	if(value.length()!=0) {
        		map.put(key,value);
        	}
        }
        byte[] data = sysGeneratorService.generatorCode(map);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"AutoCode.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}

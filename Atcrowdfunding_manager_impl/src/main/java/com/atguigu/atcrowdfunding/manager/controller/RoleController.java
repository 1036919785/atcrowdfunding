package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.manager.service.RolerService;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;
import com.atguigu.atcrowdfunding.util.jsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:jiege
 * @Date: 2019/6/8 17:22
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RolerService rolerService;

    @RequestMapping("/add")
    public String add(){
        return "role/add" ;
    }

    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(String name){
        jsonResult jsonResult = new jsonResult();
        try {
            int count = rolerService.doAdd(name);
            jsonResult.setSuccessful(true);
        } catch (Exception e) {
            jsonResult.setMessage("保存角色信息失败!");
            jsonResult.setSuccessful(false);
            e.printStackTrace();
        }
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("/deleRoleById")
    public Object deleRoleById(Integer id){
        jsonResult jsonResult = new jsonResult();
        try {
            int count = rolerService.deleRoleById(id);
            jsonResult.setSuccessful(true);
        } catch (Exception e) {
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("删除角色失败!");
            e.printStackTrace();
        }
        return jsonResult;
    }


    @RequestMapping("/index")
    public String index(){
        return "role/index";
    }

    @ResponseBody
    @RequestMapping("/doIndex")
    public Object doIndex(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                          @RequestParam(value = "pagesize", required = false,defaultValue = "5") Integer pagesize,
                          @RequestParam(value = "seachText", required = false,defaultValue = "") String seachText){
        Map<String,Object> map = new HashMap<String, Object>();
       /* if (seachText!=null){
            map.put("seachText",seachText);
        }*/
       if (StringUtil.isNotEmpty(seachText)){
           if (seachText.contains("%")){
               seachText = seachText.replaceAll("%","\\\\%");
           }
           map.put("seachText",seachText);
       }
        map.put("pageno",pageno);
        map.put("pagesize",pagesize);
        jsonResult jsonResult = new jsonResult();
        try {
            Page page = rolerService.queryRole(map);
            jsonResult.setSuccessful(true);
            jsonResult.setPage(page);
        } catch (Exception e) {
            jsonResult.setMessage("页面加载失败!");
            e.printStackTrace();
        }
        return jsonResult;
    }



/*    @ResponseBody
    @RequestMapping("/doIndex")
    public Object doIndex(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                          @RequestParam(value = "pagesize", required = false,defaultValue = "5") Integer pagesize){
        jsonResult jsonResult = new jsonResult();
        try {
            Page page = rolerService.queryRole(pageno,pagesize);
            jsonResult.setSuccessful(true);
            jsonResult.setPage(page);
        } catch (Exception e) {
            jsonResult.setMessage("页面加载失败!");
            e.printStackTrace();
        }
        return jsonResult;
    }*/
}

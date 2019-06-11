package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.manager.service.RolerService;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;
import com.atguigu.atcrowdfunding.util.jsonResult;
import com.atguigu.atcrowdfunding.vo.VoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private PermissionService permissionService;


    @ResponseBody
    @RequestMapping("/loadDataAsync")
    public Object loadDataAsync(Integer roleId){
        List<Permission> root = new ArrayList<Permission>();
        List<Permission> children = new ArrayList<Permission>();
        try {
            List<Permission> permissions = permissionService.QueryAllPermisson();
            //根据id值查询角色已经分配过的权限
            List<Integer> permissionIdsForRoleId = permissionService.queryPermissionIdsForRoleId(roleId);
            Map<Integer,Permission> map = new HashMap<Integer, Permission>();
            for (Permission innerPermission : permissions){
                //把分配过权限的角色的checked设为true
                if (permissionIdsForRoleId.contains(innerPermission.getId())){
                    innerPermission.setChecked(true);
                }
                map.put(innerPermission.getId(),innerPermission);
            }
            for (Permission child : permissions){
                if (child.getPid()==null){
                    root.add(child);
                }else if (child.getPid()!=null){
                    Permission permission = map.get(child.getPid());
                    children = permission.getChildren();
                    children.add(child);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }

    @RequestMapping("/assignPermission")
    public String assignPermission(){
        return "role/assignPermission";
    }

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

    @ResponseBody
    @RequestMapping("/deleteBathById")
    public Object deleteBathById(VoModel model){
        jsonResult jsonResult = new jsonResult();
        try {
            int count = rolerService.deleteBathById(model);
            jsonResult.setMessage("删除角色成功!");
            jsonResult.setSuccessful(true);
        } catch (Exception e) {
            jsonResult.setMessage("删除角色失败!");
            e.printStackTrace();
        }

        return jsonResult;
    }

    @RequestMapping("/edit")
    public String edit(Integer id,Map map){
        Role role = rolerService.queryRoleByid(id);
        map.put("role",role);
        return "role/edit";
    }
    @ResponseBody
    @RequestMapping("/toEdit")
    public Object toEdit(Role role){
        jsonResult jsonResult = new jsonResult();
        try {
            int count = rolerService.updateRole(role);
            jsonResult.setSuccessful(count==1);
        } catch (Exception e) {
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("角色数据修改失败");
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

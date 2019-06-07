package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:jiege
 * @Date: 2019/6/5 17:38
 */


@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        return "permission/index";
    }

    @ResponseBody
    @RequestMapping("/initZtree")
    public Object initAtree(){
        List<Permission> root = new ArrayList<Permission>();
        try {
            List<Permission> permissions = permissionService.QueryAllPermisson();
            Map<Integer,Permission> map = new HashMap<Integer, Permission>();
            for (Permission innerPermission : permissions){
                map.put(innerPermission.getId(),innerPermission);
            }
            for (Permission child : permissions){
                if (child.getPid()==null){
                    root.add(child);
                }else {
                    map.get(child.getPid()).getChildren().add(child);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }

   /* @ResponseBody
    @RequestMapping("/initZtree")
    public Object initAtree(){
        List<Permission> root = null;
        try {
            root = new ArrayList<Permission>();
            List<Permission> permissions = permissionService.QueryAllPermisson();
            for (Permission permission : permissions){
                if (permission.getPid()==null){
                    root.add(permission);
                }else {
                    for (Permission innerPermission : permissions){
                        if (permission.getPid()==innerPermission.getId()){
                            Permission parent = innerPermission;
                            parent.getChildren().add(permission);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }*/

   /* @ResponseBody
    @RequestMapping("/initZtree")
    public Object initZtree(){


        List<Permission> root = new ArrayList<Permission>();
        try {
            //父
            Permission rootPermission = permissionService.queryRootPermission();
            queryChild(rootPermission);
            rootPermission.setOpen(true);
            root.add(rootPermission) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }
    //递归
    private void queryChild(Permission permission){
        List<Permission> childrenPermission = permissionService.queryChildrenPermission(permission.getId());
            permission.setChildren(childrenPermission);
        for (Permission innerChild : childrenPermission){
            queryChild(innerChild);
        }
    }


  /*  @ResponseBody
    @RequestMapping("/initZtree")
    public Object initZtree(){


        List<Permission> root = new ArrayList<Permission>();
        try {
            //父
            Permission rootPermission = permissionService.queryRootPermission();
            //子
            List<Permission> childrenPermission = permissionService.queryChildrenPermission(rootPermission.getId());
            //设置父子关系
            rootPermission.setChildren(childrenPermission);
            //查询子中为父的子节点
            for (Permission children : childrenPermission){
                List<Permission> innerChildren = permissionService.queryChildrenPermission(children.getId());
                children.setChildren(innerChildren);
            }
            rootPermission.setOpen(true);
            root.add(rootPermission) ;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }*/


/*
    @ResponseBody
    @RequestMapping("/initZtree")
    public Object initZtree(){
        //jsonResult jsonResult = new jsonResult();
        Permission permission = new Permission();
        List<Permission> root = new ArrayList<Permission>();
        try {
            root.add(permission);
            permission.setName("系统权限菜单");
            permission.setOpen(true);
            List<Permission> children = new ArrayList<Permission>();
            Permission permission1 = new Permission();
            permission1.setName("权限管理");
            Permission permission2 = new Permission();
            permission2.setName("用户维护");
            children.add(permission1);
            children.add(permission2);
            permission.setChildren(children);
            //jsonResult.setData(root);
            //jsonResult.setSuccessful(true);
            //jsonResult.setMessage("hello");
        } catch (Exception e) {
            //jsonResult.setSuccessful(false);
            e.printStackTrace();
        }

        return root;

    }
*/


}

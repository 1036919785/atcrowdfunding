package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.Consts;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.jsonResult;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/reg")
    public String reg(){
        return "reg";
    }

    @RequestMapping("/doreg")
    public String doreg(User user){

        user.setCreatetime("2019-05-31 02:25:00");
        System.out.println(user.toString());

        user.setUsername("123213");
        userService.insertUser(user);
        return "index";
    }


    @RequestMapping("/dologin")
    public void dologin(String loginacct, String userpswd, String type, HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        jsonResult result = new jsonResult();

       try {
           Map<String,Object> map = new HashMap<String, Object>();
           map.put("loginacct",loginacct);
           map.put("userpswd", MD5Util.digest(userpswd));
           map.put("type",type);
           User user = userService.queryUserLogin(map);
           session.setAttribute(Consts.LOGIN_USER,user);

           //------------------
           List<Permission> permissions = userService.queryUserPermissonById(user.getId());

           Set<String> myUris = new HashSet<String>();

           Permission permissionRoot = null;

           Map<Integer,Permission> map2 = new HashMap<Integer, Permission>();
           for (Permission innerPermission : permissions){
               map2.put(innerPermission.getId(),innerPermission);
               myUris.add("/"+innerPermission.getUrl());
           }
           session.setAttribute(Consts.MY_URIS,myUris);
           for (Permission child : permissions){
               if (child.getPid()==null){
                   permissionRoot = child;
               }else if (child.getPid()!=null){
                   Permission permission = map2.get(child.getPid());
                   permission.getChildren().add(child);
               }
           }

           session.setAttribute("permissionRoot",permissionRoot);


           //------------------


           result.setSuccessful(true);

       }catch (Exception e){
           result.setMessage("错误!");
           result.setSuccessful(false);
           e.printStackTrace();

       }
        Gson gson = new Gson();
        String json = gson.toJson(result);
        response.getWriter().write(json);

    }

   /* @RequestMapping("/dologin")
    public String dologin(String loginacct, String userpswd,String type, HttpSession session){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("loginacct",loginacct);
        map.put("userpswd",userpswd);
        map.put("type",type);

        User user = userService.queryUserLogin(map);

        session.setAttribute(Consts.LOGIN_USER,user);
        Gson gson = new Gson();

        return "redirect:/main.htm";
    }*/

    @RequestMapping("/main")
    public String main(HttpSession session){

        User user = (User) session.getAttribute(Consts.LOGIN_USER);


        return "main";
    }


    @RequestMapping("/loginout")
    public String loginout(HttpSession session){
        session.invalidate();
        return "redirect:/index.htm";
    }




}

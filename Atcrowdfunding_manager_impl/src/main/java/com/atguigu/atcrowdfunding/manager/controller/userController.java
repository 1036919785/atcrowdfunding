package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
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


/*
  * @author 10369
  * @description
  * @date  2019/6/1 17:07
  * @param
  * @return
  */
@Controller
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("/assignRole")
    public String assignRole(Integer id,Map map){

        List<Role> allRoleList = userService.queryAllRole();
        List<Integer> userRoleIdList = userService.queryUserRole(id);

        List<Role> leftRole = new ArrayList<Role>();
        List<Role> rightRole = new ArrayList<Role>();

        for (Role role : allRoleList) {
            if (userRoleIdList.contains(role.getId())){
                rightRole.add(role);
            }else {
                leftRole.add(role);
            }
        }
        map.put("leftRole",leftRole);
        map.put("rightRole",rightRole);


        return "user/assignRole";
    }

/*
  * @author 10369
  * @description 分配角色
  * @date  2019/6/5 12:46
  * @param  [userid, model]
  * @return  java.lang.Object
  */
    @ResponseBody
    @RequestMapping("/doAddAssign")
    public Object doAddAssign(Integer userid , VoModel model){
        jsonResult jsonResult = new jsonResult();
        try {
            int count = userService.addAssign(userid,model);
            jsonResult.setSuccessful(true);
        } catch (Exception e) {
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("分配角色失败!");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("/deleteAssign")
    public Object deleteAssign(Integer userid,VoModel model){
        jsonResult jsonResult = new jsonResult();

        try {
            int count = userService.deleteAssign(userid,model);
            jsonResult.setSuccessful(true);
        } catch (Exception e) {
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("取消分配失败!");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id,Map map){
        User user = userService.QueryUserInfoById(id);
        map.put("user",user);
        return "user/update";
    }

    /*
      * @author 10369
      * @description 批量删除
      * @date  2019/6/5 12:46
      * @param  [model]
      * @return  java.lang.Object
      */
    @ResponseBody
    @RequestMapping("/deleteBathById")
    public Object deleteBathById(VoModel model){
        jsonResult jsonResult = new jsonResult();

        try {
            int count = userService.deleteBathByVo(model);
            jsonResult.setSuccessful(count==model.getDatas().size());
        } catch (Exception e) {
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("删除失败!");
            e.printStackTrace();
        }

        return jsonResult;
    }


/*    @ResponseBody
    @RequestMapping("/deleteBathById")
    public Object deleteBathById(Integer[] id){
        jsonResult jsonResult = new jsonResult();

        try {
            int count = userService.deleteBathById(id);
            jsonResult.setSuccessful(count==id.length);
        } catch (Exception e) {
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("删除失败!");
            e.printStackTrace();
        }

        return jsonResult;
    }*/


    @ResponseBody
    @RequestMapping("/deleteUserById")
    public Object deleteUserById(Integer id){
        jsonResult jsonResult = new jsonResult();
        try {
            int count = userService.deleteUserById(id);
            jsonResult.setSuccessful(count==1);
        } catch (Exception e) {
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("删除失败!");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(User user){
        jsonResult jsonResult = new jsonResult();

        try {
            int count = userService.doUpdate(user);
            jsonResult.setSuccessful(count==1);
        } catch (Exception e) {
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("修改失败!");
            e.printStackTrace();
        }
        return jsonResult;
    }


    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(User user){
        jsonResult jsonResult = new jsonResult();

        try {
            int count = userService.saveUser(user);
            jsonResult.setSuccessful(count==1);
        } catch (Exception e) {
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("添加失败!");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /*分页查询-异步*/
    @RequestMapping("/doIndex")
    @ResponseBody
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10")Integer pagesize,
                        @RequestParam(value = "queryText",required = false,defaultValue = "") String queryText){
        jsonResult jsonResult = new jsonResult();
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            if (StringUtil.isNotEmpty(queryText)){
//                对于特殊字符%的解决方式(模糊查询)
                if (queryText.contains("%")){
                     queryText = queryText.replaceAll("%", "\\\\%");
                }
                map.put("queryText",queryText);
            }
            map.put("pageno",pageno);
            map.put("pagesize",pagesize);
            Page page = userService.queryPage(map);
            jsonResult.setSuccessful(true);
            jsonResult.setPage(page);
        }catch (Exception e){
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("查询失败!");
            e.printStackTrace();
        }
        return jsonResult;
    }


    /* *//*分页查询-异步*//*
    @RequestMapping("/index")
    @ResponseBody
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10")Integer pagesize
                        ){
        jsonResult jsonResult = new jsonResult();
        try {
            Page page = userService.queryPage(pageno,pagesize);
            jsonResult.setSuccessful(true);
            jsonResult.setPage(page);
        }catch (Exception e){
            jsonResult.setSuccessful(false);
            jsonResult.setMessage("查询失败!");
            e.printStackTrace();
        }
        return jsonResult;
    }
*/


    /*分页查询-同步*/
    /*@RequestMapping("/index")
    public String index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10")Integer pagesize,
                        Map map){
        Page page = userService.queryPage(pageno,pagesize);
        map.put("page",page);
       return "/user/index";
    }*/



}

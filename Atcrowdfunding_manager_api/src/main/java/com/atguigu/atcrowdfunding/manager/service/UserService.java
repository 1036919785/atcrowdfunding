package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.VoModel;

import java.util.List;
import java.util.Map;

public interface UserService {

    User queryUserLogin(Map<String, Object> map);

    int insertUser(User user);

   // Page queryPage(Integer pageno, Integer pagesize);
     Page queryPage(Map<String,Object> map);

    int saveUser(User user);

    User QueryUserInfoById(Integer id);

    int doUpdate(User user);

    int deleteUserById(Integer id);

    int deleteBathById(Integer[] id);

    int deleteBathByVo(VoModel model);

    List<Role> queryAllRole();

    List<Integer> queryUserRole(Integer id);

    int addAssign(Integer userid, VoModel model);

    int deleteAssign(Integer userid, VoModel model);
}

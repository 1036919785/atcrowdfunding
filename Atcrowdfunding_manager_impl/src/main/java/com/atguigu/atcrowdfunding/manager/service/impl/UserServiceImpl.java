package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.Consts;
import com.atguigu.atcrowdfunding.LoginFailException;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.VoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
  * @author : jiege
  * @description
  * @date 16:58 2019/6/1
  * @param
  * @return
  */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserLogin(Map<String, Object> map) {

        User user = userMapper.queryUserLogin(map);

        if (user==null){
            throw new LoginFailException("账号或密码错误!");
        }

        return user;
    }
/*
  * @author 10369
  * @description
  * @date  2019/6/1 17:09
  * @param  [user]
  * @return  void
  */
    @Override
    public int insertUser(User user) {
        userMapper.insertUser(user);
        return 0;
    }
    /*
      * @author : jiege
      * @description
      * @date 16:58 2019/6/1
      * @param  [map]
      * @return  com.atguigu.atcrowdfunding.util.Page
      */
    @Override
    public Page queryPage(Map<String,Object> map) {
        Page page = new Page((Integer) map.get("pageno"),(Integer) map.get("pagesize"));
        Integer startIndex = page.getStartIndex();
        map.put("startIndex",startIndex);
        List<User> datas = userMapper.queryList(map);
        page.setDatas(datas);
        Integer totalsize = userMapper.queryCount(map);
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public int saveUser(User user) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createtime = format.format(new Date());
        user.setCreatetime(createtime);
        user.setUserpswd(MD5Util.digest(Consts.PASSWORD));
        return userMapper.insert(user);
    }

    @Override
    public User QueryUserInfoById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int doUpdate(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBathById(Integer[] ids) {
        int count = 0;
        for (Integer id:ids) {
            count+=userMapper.deleteByPrimaryKey(id);

        }
        if (ids.length!=count){
            throw new RuntimeException("删除失败!");
        }
        return count;
    }

 /*   @Override
    public int deleteBathByVo(VoModel model) {
        return userMapper.deleteBathByVo(model);
    }*/
    @Override
    public int deleteBathByVo(VoModel model) {
        return userMapper.deleteBathByVo2(model.getDatas());
    }

    @Override
    public List<Role> queryAllRole() {
        return userMapper.queryAllRole();
    }

    @Override
    public List<Integer> queryUserRole(Integer id) {
        return userMapper.queryUserRole(id);
    }

    @Override
    public int addAssign(Integer userid, VoModel model) {
        return userMapper.addAssign(userid,model);
    }

    @Override
    public int deleteAssign(Integer userid, VoModel model) {
        return userMapper.deleteAssign(userid,model);
    }


   /* @Override
    public Page queryPage(Integer pageno, Integer pagesize) {
        Page page = new Page(pageno,pagesize);
        Integer startIndex = page.getStartIndex();
        List<User> datas = userMapper.queryList(startIndex,pagesize);
        page.setDatas(datas);
        Integer totalsize = userMapper.queryCount();
        page.setTotalsize(totalsize);

        return page;
    }*/
}

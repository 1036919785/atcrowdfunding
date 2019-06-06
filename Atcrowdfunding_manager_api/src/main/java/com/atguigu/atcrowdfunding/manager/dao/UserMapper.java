package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.vo.VoModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 10369
 */
@Repository
public interface UserMapper {
    
/*
  * @Author : jiege
  * @Description
  * @Date 16:48 2019/6/1
  * @Param  [id]
  * @return  int
  */
    int deleteByPrimaryKey(Integer id);

    int insert(User record);


    User selectByPrimaryKey(Integer id);

    List<User> selectAll();
/*
  * @Author : jiege
  * @Description
  * @Date 16:49 2019/6/1
  * @Param  [record]
  * @return  int
  */
    int updateByPrimaryKey(User record);
/*
  * @Author : jiege
  * @Description
  * @Date 16:51 2019/6/1
  * @Param  [map]
  * @return  com.atguigu.atcrowdfunding.bean.User
  */
    User queryUserLogin(Map<String, Object> map);

    /*
      * @Author : jiege
      * @Description
      * @Date 16:52 2019/6/1
      * @Param  [user]
      * @return  int
      */
    int insertUser(User user);
/*
  * @Author : jiege
  * @Description 
  * @Date 16:52 2019/6/1       
  * @Param  
  * @return  
  */
    List<User> queryList(Map<String,Object> map);
/*
  * @author 10369
  * @description
  * @date  2019/6/1 17:10
  * @param  [map]
  * @return  java.lang.Integer
  */
    Integer queryCount(Map<String,Object> map);

    int deleteBathByVo(VoModel model);

    int deleteBathByVo2(List<User> datas);

    List<Role> queryAllRole();

    List<Integer> queryUserRole(Integer id);

    int addAssign(@Param("userid") Integer userid,@Param("model") VoModel model);

    int deleteAssign(@Param("userid") Integer userid,@Param("model") VoModel model);

    /* List<User> queryList(@Param("startIndex") Integer startIndex,@Param("pagesize") Integer pagesize);*/

    /*Integer queryCount();*/
}
package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> queryRole(@Param("startIndex") Integer startIndex,@Param("pagesize") Integer pagesize);

    int queryTotalSize();

    List<Role> queryRoleByMap(Map<String, Object> map);

    Integer queryTotalSizeByMap(Map<String, Object> map);
}
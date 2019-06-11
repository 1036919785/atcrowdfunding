package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.vo.VoModel;
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

    int doAdd(String name);

    Role queryRoleByid(Integer id);

    int updateRole(Role role);

    int deleteBathById(VoModel model);

    void deleteRolePermission(Integer roleId);

    int insertRolePermission(@Param("roleId") Integer roleId,@Param("model") VoModel model);
}
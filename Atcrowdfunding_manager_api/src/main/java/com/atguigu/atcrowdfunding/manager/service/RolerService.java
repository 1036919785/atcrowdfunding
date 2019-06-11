package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.VoModel;

import java.util.Map;

/**
 * @Author:jiege
 * @Date: 2019/6/8 17:25
 */
public interface RolerService {
    Page queryRole(Map<String, Object> map);

    int doAdd(String name);

    int deleRoleById(Integer id);

    Role queryRoleByid(Integer id);

    int updateRole(Role role);

    int deleteBathById(VoModel model);

    int updateAssignPermissionById(Integer id, VoModel model);

    //Page queryRole(Integer pageno, Integer pagesize);

}

package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Permission;

import java.util.List;

/**
 * @Author:jiege
 * @Date: 2019/6/5 17:44
 */
public interface PermissionService {


    Permission queryRootPermission();

    List<Permission> queryChildrenPermission(Integer id);

    List<Permission> QueryAllPermisson();

    int savePermisson(Permission permission);

    List<Integer> queryPermissionIdsForRoleId(Integer roleId);
}

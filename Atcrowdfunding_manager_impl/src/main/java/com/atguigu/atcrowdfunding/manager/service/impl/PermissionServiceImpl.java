package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.dao.PermissionMapper;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:jiege
 * @Date: 2019/6/5 17:45
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Permission queryRootPermission() {
        return permissionMapper.queryRootPermission();
    }

    @Override
    public List<Permission> queryChildrenPermission(Integer id) {
        return permissionMapper.queryChildrenPermission(id);
    }

    @Override
    public List<Permission> QueryAllPermisson() {
        return permissionMapper.QueryAllPermisson();
    }
}

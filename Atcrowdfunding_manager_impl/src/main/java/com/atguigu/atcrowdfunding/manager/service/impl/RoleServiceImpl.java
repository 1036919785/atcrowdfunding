package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.manager.dao.RoleMapper;
import com.atguigu.atcrowdfunding.manager.service.RolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:jiege
 * @Date: 2019/6/8 17:26
 */
@Service
public class RoleServiceImpl implements RolerService {

    @Autowired
    private RoleMapper roleMapper;
}

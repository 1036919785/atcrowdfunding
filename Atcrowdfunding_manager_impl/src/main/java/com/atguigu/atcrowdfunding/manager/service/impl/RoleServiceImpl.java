package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.manager.dao.RoleMapper;
import com.atguigu.atcrowdfunding.manager.service.RolerService;
import com.atguigu.atcrowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:jiege
 * @Date: 2019/6/8 17:26
 */
@Service
public class RoleServiceImpl implements RolerService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page queryRole(Map<String, Object> map) {
        Page page = new Page((Integer) map.get("pageno"),(Integer) map.get("pagesize"));
        Integer startIndex = page.getStartIndex();
        map.put("startIndex",startIndex);
        List<Role> roleList = roleMapper.queryRoleByMap(map);
        Integer totalsize = roleMapper.queryTotalSizeByMap(map);
        page.setDatas(roleList);
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public int doAdd(String name) {
        return roleMapper.doAdd(name);
    }

    @Override
    public int deleRoleById(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role queryRoleByid(Integer id) {
        return roleMapper.queryRoleByid(id);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

   /* @Override
    public Page queryRole(Integer pageno, Integer pagesize) {
        Page page = new Page(pageno,pagesize);
        Integer startIndex = page.getStartIndex();
        List<Role> roleList = roleMapper.queryRole(startIndex,pagesize);
        int totalsize = roleMapper.queryTotalSize();
        page.setDatas(roleList);
        page.setTotalsize(totalsize);
        return page;
    }*/
}

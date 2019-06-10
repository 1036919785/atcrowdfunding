package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.util.Page;

import java.util.Map;

/**
 * @Author:jiege
 * @Date: 2019/6/8 17:25
 */
public interface RolerService {
    Page queryRole(Map<String, Object> map);

    //Page queryRole(Integer pageno, Integer pagesize);

}

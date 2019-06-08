package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.manager.service.RolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:jiege
 * @Date: 2019/6/8 17:22
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RolerService rolerService;

    @RequestMapping("/index")
    public String index(){
        return "role/index";
    }
}

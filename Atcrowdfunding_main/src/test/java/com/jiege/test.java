package com.jiege;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.MD5Util;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        UserService userService = applicationContext.getBean(UserService.class);

        for (int i=0;i<100;i++){
            User user = new User();
            user.setLoginacct("小明"+i);
            user.setUsername("xiaoming");
            user.setUserpswd(MD5Util.digest("123"));
            user.setEmail("xiaoming"+i+"123@qq.com");
            user.setCreatetime("2019-05-31 01:25:00");
            userService.insertUser(user);
        }

    }
}

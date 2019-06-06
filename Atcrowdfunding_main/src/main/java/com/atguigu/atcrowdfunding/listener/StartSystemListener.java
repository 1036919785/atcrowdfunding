package com.atguigu.atcrowdfunding.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartSystemListener implements ServletContextListener{


    //在服务器启动时,创建application对象时需要执行的方法
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.将项目的上下文路径(request.getcontextpath)放置到application域中
        ServletContext servletContext = servletContextEvent.getServletContext();
        String contextPath = servletContext.getContextPath();
        servletContext.setAttribute("APP_PATH",contextPath);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

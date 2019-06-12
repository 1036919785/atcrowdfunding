package com.atguigu.atcrowdfunding.listener;

import com.atguigu.atcrowdfunding.Consts;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartSystemListener implements ServletContextListener{


    //在服务器启动时,创建application对象时需要执行的方法
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.将项目的上下文路径(request.getcontextpath)放置到application域中
        ServletContext servletContext = servletContextEvent.getServletContext();
        String contextPath = servletContext.getContextPath();
        servletContext.setAttribute("APP_PATH",contextPath);

        //2.利用拦截器查询所有访问路径并存放在application域中
        //获得ioc容器通过webapplicationcontextutils
        ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        PermissionService permissionService = ioc.getBean(PermissionService.class);
        List<Permission> permissions = permissionService.QueryAllPermisson();
        Set<String> allUris = new HashSet<String>();
        for (Permission permission : permissions){
            allUris.add("/"+permission.getUrl());
        }
        servletContext.setAttribute(Consts.PERMISSION_URIS,allUris);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

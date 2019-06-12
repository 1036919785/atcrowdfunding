package com.atguigu.atcrowdfunding.handleinterceptor;

import com.atguigu.atcrowdfunding.Consts;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * @Author:jiege
 * @Date: 2019/6/12 15:13
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    public PermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

     /*   //1.查询所有访问路径
        List<Permission> permissions = permissionService.QueryAllPermisson();
        Set<String> allUris = new HashSet<String>();
        for (Permission permission : permissions){
            allUris.add("/"+permission.getUrl());
        }*/
        Set<String> allUris = (Set<String>) request.getSession().getServletContext().getAttribute(Consts.PERMISSION_URIS);
        String servletPath = request.getServletPath();
        if (allUris.contains(servletPath)){
            HttpSession session = request.getSession();
            Set<String> myUris= (Set<String>) session.getAttribute("myUris");
            if (myUris.contains(servletPath)){
                return true;
            }else {
                response.sendRedirect(request.getContextPath()+"/login.htm");
                return false;
            }
        }else {
            return true;
        }

    }
}

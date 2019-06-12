package com.atguigu.atcrowdfunding.handleinterceptor;

import com.atguigu.atcrowdfunding.Consts;
import com.atguigu.atcrowdfunding.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author:jiege
 * @Date: 2019/6/12 13:25
 */
public class LoginIntercepter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.定义哪些路径不需要拦截(白名单)
        Set<String> uri = new HashSet<String>();
        uri.add("/reg.do");
        uri.add("/reg.htm");
        uri.add("/doreg.do");
        uri.add("/dologin.do");
        uri.add("/login.htm");
        uri.add("/loginout.do");
        uri.add("/index.htm");

        String servletPath = request.getServletPath();

        if (uri.contains(servletPath)){
            return true;
        }

        //2.判断用户是否登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Consts.LOGIN_USER);
        if (user!=null){
            return true;
        }else {
            response.sendRedirect(request.getContextPath()+"/login.htm");
            return false;
        }

    }
}

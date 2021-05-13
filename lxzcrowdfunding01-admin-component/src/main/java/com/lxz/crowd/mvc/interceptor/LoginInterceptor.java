package com.lxz.crowd.mvc.interceptor;

import com.lxz.crodw.entity.Admin;
import com.lxz.crowd.constant.CrowdConstant;
import com.lxz.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.通过request 对象获取Session 对象
        HttpSession session = request.getSession();
// 2.尝试从Session 域中获取Admin 对象
        Admin admin = (Admin) session.getAttribute("loginAdmin");
// 3.判断admin 对象是否为空
        if(admin == null) {
// 4.抛出异常
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        }
// 5.如果Admin 对象不为null，则返回true 放行
        return true;
    }
}

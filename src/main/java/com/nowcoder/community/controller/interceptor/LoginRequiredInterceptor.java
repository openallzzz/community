package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);

            // 在未登录的情况下， 访问登录状态下才有的功能时，应该进行拦截
            if(loginRequired != null && hostHolder.getUser() == null) {
                // 使用response进行重定向、controller中的底层也是使用response这样操作的
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }

        }
        return true;
    }
}

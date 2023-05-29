
package class4.spm.novelnook.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userid = request.getSession().getAttribute("userid");
//        if(userid == null){
//            request.setAttribute("msg","没有权限，请先登录");
//            request.getRequestDispatcher("/admin/log.html").forward(request,response);//这里设置成只能返回登录界面
//            return false;
//        }
        // TODO: 拦截器的逻辑
        return true;
    }
}


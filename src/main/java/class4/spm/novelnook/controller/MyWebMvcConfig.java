
package class4.spm.novelnook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/log.html","/admin/login");//这里只是不拦截挡登录界面，负责前端的同学记得在excludePathPatterns中加上你们的前端资源

        //registry.addInterceptor(new StaffLogInterceptor())
        //        .addPathPatterns("/staff/**").excludePathPatterns("/staff/login.html", "/staff/dist_login/**", "/staff/login");
    }
}


package class4.spm.novelnook.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class StaffLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //有无 staffID
        Object staffID = request.getSession().getAttribute("staffID");

        if(staffID == null) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            //没
            //request.setAttribute("msg_staff_login", "please log in");
            request.getRequestDispatcher("/staff/login.html").forward(request,response);
            return false;
        } else {
            return true;
        }


    }

}

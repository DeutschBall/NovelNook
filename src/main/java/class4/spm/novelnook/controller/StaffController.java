package class4.spm.novelnook.controller;


import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.FineInfo;
import class4.spm.novelnook.pojo.Patron;
import class4.spm.novelnook.pojo.Staff;
import class4.spm.novelnook.service.StaffServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    StaffServiceImpl staffServiceImpl;

    //还书
    @GetMapping("/book/return/{borrowid}/{returntime}")
    public int returnBook(@PathVariable("borrowid") String borrowid, @PathVariable("returntime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date returntime) {
        return staffServiceImpl.returnBook(borrowid, returntime);
    }

    //log in
    @RequestMapping("/login")
    public String login(@RequestParam("userid") int userid, @RequestParam("password") String password, HttpSession session) {
        Staff staff = staffServiceImpl.getStaffById(userid);
        //具体的业务
        if (Objects.equals(staff.getPassword(), password)) {
            session.setAttribute("staffID", userid);
            return "Match success";
        } else {
            return "Match fail";
        }
    }

    //log out
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if(session.getAttribute("staffID") != null) {
            session.removeAttribute("staffID");
            return "staff log out success";
        } else {
            return "staff log out fail";
        }
    }

    //书剩余量
    @GetMapping("/book/remain")
    public int showBookRemain() {
        return staffServiceImpl.getBookRemain();
    }

    //书总量
    @GetMapping("/book/total")
    public int showBookTotal() {
        return staffServiceImpl.getBookTotal();
    }

    //patron 总数
    @GetMapping("/patron/total")
    public int showPatronNum() {
        return staffServiceImpl.getPatronNum();
    }

    //未交罚款总额
    @GetMapping("/fine/total")
    public int showUnpayAmount() {
        return staffServiceImpl.getUnpayAmount();
    }

    //本人信息
    @GetMapping("/whoIam")
    public Staff showSelf(HttpSession session) {
        if(session.getAttribute("staffID") != null) {
            int userid = (int) session.getAttribute("staffID");
            return staffServiceImpl.getSelf(userid);
        } else {
            return null;
        }
    }

    //所有未交罚款信息
    @GetMapping("/fine/show/all")
    public List<FineInfo> showUnpayInfoAll() {
        return staffServiceImpl.getUnpayInfoAll();
    }



}

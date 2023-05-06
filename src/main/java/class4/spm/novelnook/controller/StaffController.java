package class4.spm.novelnook.controller;


import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Patron;
import class4.spm.novelnook.service.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    StaffServiceImpl staffServiceImpl;

    //还书
    @GetMapping("/book/return/{borrowid}/{returntime}")
    public int returnBook(@PathVariable("borrowid") String borrowid, @PathVariable("returntime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date returntime) {
        System.out.println(borrowid + " ==== " + returntime);
        return staffServiceImpl.returnBook(borrowid, returntime);
    }

    @RequestMapping("/login")
    public String login(@RequestParam("userid") int userid, @RequestParam("password") String password)
    {
//        System.out.println(userid);
//        System.out.println(password);
        Patron p = staffServiceImpl.getPatronById(userid);

        //具体的业务
        if (Objects.equals(p.getPassword(), password)) {
            return "Match success";
        } else {
            return "Match fail";
        }

    }



}

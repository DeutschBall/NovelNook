package class4.spm.novelnook.controller;


import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Borrow;
import class4.spm.novelnook.pojo.Patron;
import class4.spm.novelnook.service.PatronServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/patron/backend")
public class PatronController {

    @Autowired
    PatronServiceImpl patronServiceImpl;

    //搜索图书信息
    @ResponseBody
    @GetMapping("/search/{key}/{option}")
    public List<Book> searchForBook(@PathVariable("key") String key,@PathVariable("option") String option) {

        List<Book> list = patronServiceImpl.searchForBook(key, option);
        System.out.println("==============================");
        System.out.println(list);
        System.out.println("==============================");
        return list;
    }

    //获取借阅记录
    @ResponseBody
    @GetMapping("/info/borrow/{userid}")
    public List<Borrow> getBorrowList(@PathVariable("userid") String userid){
        System.out.println(userid);
        return patronServiceImpl.getBorrowList(userid);
    }

    //获取图书信息
    @ResponseBody
    @GetMapping("/info/book/{bookid}")
    public List<Book> getBookInfo(@PathVariable("bookid") String bookid){
        System.out.println(bookid);
        return patronServiceImpl.getBookInfo(bookid);
    }

    //借书
    @ResponseBody
    @GetMapping("/borrow/{userid}/{bookid}")
    public String updateBorrow(@PathVariable("userid") String userid, @PathVariable("bookid") String bookid) {
        return patronServiceImpl.updateBorrow(userid, bookid);
    }
}

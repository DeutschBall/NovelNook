package class4.spm.novelnook.controller;


import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Patron;
import class4.spm.novelnook.service.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    StaffServiceImpl staffServiceImpl;


    // 获取所有patron信息
    @GetMapping("/patron/show/all")
    public List<Patron> getAllPatrons() {
        return staffServiceImpl.getAllPatrons();
    }

    //根据id获得patron信息
    @GetMapping("/patron/show/{userid}")
    public Patron getPatonById(@PathVariable("userid") String userid) {
        return staffServiceImpl.getPatronById(userid);
    }

    //根据id获得book信息
    @GetMapping("/material/show/{bookid}")
    public Book getBookById(@PathVariable("bookid") String bookid) {
        return staffServiceImpl.getBookById(bookid);
    }

    //根据userid删除用户
    @DeleteMapping("/patron/delete/{userid}")
    public int DeletePatron(@PathVariable("userid") String userid) {
        return staffServiceImpl.DeleteParton(userid);
    }

    int randomNum = new Random().nextInt(10) + 1;
    // 将随机数转换为字符串，并拼接到字符串中
    //生成随机的头像
    String avatarUrl = "/avatars/" + randomNum + ".svg";
    @PostMapping("/patron/add/{userid}/{password}/{firstname}/{lastname}/{email}/{telephone}")
    public int AddPatron(@PathVariable("userid") String userid,@PathVariable("password") String password
            ,@PathVariable("firstname" ) String firstname,@PathVariable("lastname") String lastname
            ,@PathVariable("email") String email,@PathVariable("telephone") String telephone)
    {
        return staffServiceImpl.AddPatron(userid, password, firstname, lastname, email, telephone,avatarUrl);
    }

    //根据bookid删除书籍
    @DeleteMapping("/material/delete/{bookid}")
    public int DeleteBook(@PathVariable("bookid") String bookid){
        return staffServiceImpl.DeleteBook(bookid);
    }

    //增加新的书籍
    @PostMapping("/material/addNew/{bookid}/{bookname}/{press}/{author}/{publishtime}/{catagory}/{remain}/{introduction}")
    public int AddNewBook(@PathVariable("bookid") String bookid, @PathVariable("bookname") String bookname,
                          @PathVariable("press") String press, @PathVariable("author") String author,
                          @PathVariable("publishtime") String publishtime, @PathVariable("catagory") String catagory,
                          @PathVariable("remain") int remain, @PathVariable("introduction") String introduction){
        return staffServiceImpl.AddNewBook(bookid,bookname,press,author,publishtime,catagory,remain,introduction);
    }

    //还书
    @PutMapping("/material/return/{bookid}/{userid}")
    public int putBook(@PathVariable("bookid") String bookid, @PathVariable("userid") String userid){
        return staffServiceImpl.putBookByBookidUserid(bookid,userid);
    }

}

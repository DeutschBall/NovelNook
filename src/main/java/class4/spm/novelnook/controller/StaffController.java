package class4.spm.novelnook.controller;


import class4.spm.novelnook.mapper.StaffMapper;
import class4.spm.novelnook.pojo.*;
import class4.spm.novelnook.service.StaffServiceImpl;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    StaffServiceImpl staffServiceImpl;

    @Autowired
    StaffMapper staffMapper;

    //还书
    @GetMapping("/book/return/{borrowid}/{returntime}")
    public int returnBook(@PathVariable("borrowid") String borrowid, @PathVariable("returntime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date returntime) {
        return staffServiceImpl.returnBook(borrowid, returntime);
    }

    //所有patron
    @GetMapping("/patron/show/all")
    public List<Patron> getAllPatrons() {
        return staffServiceImpl.getAllPatrons();
    }

    //log in
    @RequestMapping("/login")
    public String login(@RequestParam("userid") int userid, @RequestParam("password") String password, HttpSession session) {
        if(session.getAttribute("staffID") != null) {
            session.removeAttribute("staffID");
        }
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
    public double showUnpayAmount() {
        return staffServiceImpl.getUnpayAmount();
    }
    //已交罚款总额
    @GetMapping("/fine/paidTotal")
    public double showPaidAmount() {
        return staffMapper.getPaidAmount();
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

    //所有已交罚款信息
    @GetMapping("/fine/show/paidAll")
    public List<FineInfo> showPaidInfoAll() {
        return staffMapper.getPaidInfoAll();
    }

//    patron

    //根据userid 找patron
    @GetMapping("/patron/show/{userid}")
    public Patron getOnePatron(@PathVariable int userid){
    return staffMapper.getPatronById(userid);
}
    //增加
    @PostMapping ("/patron/add/{firstname}/{lastname}/{email}/{telephone}")
    public Patron addPatron(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname, @PathVariable("email") String email, @PathVariable("telephone") String telephone) {
        int id = staffServiceImpl.addPatron(firstname, lastname, email, telephone);
        return staffServiceImpl.getOnePatron(id);
    }

    //删除
    @DeleteMapping("/patron/delete/{userid}")
    public int deletePatron(@PathVariable int userid){
        return  staffServiceImpl.deletePatron(userid);
    }

    //修改
    @PutMapping("/patron/update/{userid}/{password}/{firstname}/{lastname}/{email}/{telephone}")
    public int updatePatron(@PathVariable int userid, @PathVariable String password, @PathVariable String firstname, @PathVariable String lastname, @PathVariable String telephone, @PathVariable String email){
        return staffServiceImpl.updatePatron(userid, password, firstname, lastname, email, telephone);
    }


//book
    // 获取所有book信息
    @GetMapping("/book/show/all")
    public List<BookWithISBN> getAllBooks() {
        return staffServiceImpl.getAllBooks();
    }

    // 根据bookid删除书籍
    @DeleteMapping("/book/delete/{bookid}")
    public int DeleteBook(@PathVariable("bookid") int bookid) {
        return staffServiceImpl.DeleteBook(bookid);
    }

    // 增加新的书籍
    @PostMapping("/book/add/{bookname}/{press}/{author}/{publishtime}/{catagory}/{remain}/{introduction}/{location}")
    public int AddNewBook(@PathVariable("bookname") String bookname, @PathVariable("press") String press,
                          @PathVariable("author") String author, @PathVariable("publishtime") String publishtime,
                          @PathVariable("catagory") String catagory, @PathVariable("remain") int remain,
                          @PathVariable("introduction") String introduction, @PathVariable("location") String location) {
        return staffServiceImpl.AddNewBook(bookname, press, author, publishtime, catagory, remain, introduction,
                location);
    }

    // 更新书籍
    @PutMapping("/book/update/{bookid}/{bookname}/{press}/{author}/{publishtime}/{catagory}/{remain}/{introduction}/{location}")
    public int UpdateBook(@PathVariable("bookid") int bookid, @PathVariable("bookname") String bookname,
                          @PathVariable("press") String press,
                          @PathVariable("author") String author, @PathVariable("publishtime") String publishtime,
                          @PathVariable("catagory") String catagory, @PathVariable("remain") int remain,
                          @PathVariable("introduction") String introduction, @PathVariable("location") String location) {
        return staffServiceImpl.UpdateBook(bookid, bookname, press, author, publishtime, catagory, remain, introduction,
                location);
    }




    //查isbn  一个ip一天 20次的上限？
    @GetMapping("/book/getByISBN/{isbn}")
    public BookWithISBN getBookByISBN(@PathVariable("isbn") String isbn) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity
                ("http://47.99.80.202:6066/openApi/getInfoByIsbn?isbn=" + isbn +"&appKey=ae1718d4587744b0b79f940fbef69e77", String.class);

        JSONObject json = JSONObject.parseObject(response.getBody());
        JSONObject data = json.getJSONObject("data");

        BookWithISBN book = new BookWithISBN();
        book.setBookname(data.getString("bookName"));
        book.setPress(data.getString("press"));
        book.setAuthor(data.getString("author"));
        book.setPublishtime(data.getString("pressDate"));
        book.setCatagory("novel");
        book.setIntroduction(data.getString("bookDesc"));
        book.setIsbn(isbn);

        // 向前端返回，仅展示，未插入数据库
        return book;
    }
    //通过ISBN添加
    @GetMapping("/book/addByISBN/{isbn}/{remain}/{location}")
    @Transactional
    public int getBookInfoByISBN(@PathVariable("isbn") String isbn, @PathVariable("remain") int remain, @PathVariable("location")String location) {

        //设置url
        String url = "http://47.99.80.202:6066/openApi/getInfoByIsbn?isbn=" + isbn +"&appKey=ae1718d4587744b0b79f940fbef69e77";
        //访问并获得返回
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        System.out.println(response);

        JSONObject json = JSONObject.parseObject(response.getBody());
        JSONObject data = json.getJSONObject("data");

        if(data == null) {
            return -1; //接口调用次数不足
        }

        Book book = new Book();
        book.setBookname(data.getString("bookName"));
        book.setPress(data.getString("press"));
        book.setAuthor(data.getString("author"));
        book.setPublishtime(data.getString("pressDate"));
        book.setCatagory("novel");
        book.setIntroduction(data.getString("bookDesc"));
        book.setRemain(remain);
        book.setLocation(location);

        // 向前端返回
        return staffMapper.addBookByISBN(book) * staffMapper.add_isbn_bookid(isbn, book.getBookid());

    }

    //根据bookname找书
    @GetMapping("/book/get/{bookname}")
    public BookWithISBN getBookByBookName(@PathVariable("bookname") String bookname) {
        return staffMapper.serachByBookName(bookname);
    }


    //根据userid 找patron
    @GetMapping("/patron/get/userid/{userid}")
    public Patron getPatronById(@PathVariable int userid){
        return staffMapper.getPatronById(userid);
    }
    //根据firstname 找patron
    @GetMapping("/patron/get/firstname/{firstname}")
    public List<Patron> getOnePatronByFirstname(@PathVariable String firstname){
        return staffMapper.getPatronByFirstname(firstname);
    }
    //根据firstname 找patron
    @GetMapping("/patron/get/lastname/{lastname}")
    public List<Patron> getOnePatronByLastname(@PathVariable String lastname){
        return staffMapper.getPatronByLastname(lastname);
    }





}

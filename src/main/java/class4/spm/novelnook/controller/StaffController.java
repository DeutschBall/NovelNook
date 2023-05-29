package class4.spm.novelnook.controller;


import class4.spm.novelnook.common.BarCode;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    StaffServiceImpl staffServiceImpl;

    @Autowired
    StaffMapper staffMapper;

    BookWithISBN bookWithISBN = null;


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


    @GetMapping("/bct/{bookid}/{remain}")
    public void testBC(@PathVariable int bookid, @PathVariable int remain) throws IOException {
        for (int i = 0; i < remain; i++) {
            createBarCode(bookid, i);
        }
    }

    //查isbn  一个ip一天 20次的上限？
    @GetMapping("/book/getByISBN/{isbn}")
    public BookWithISBN getBookByISBN(@PathVariable("isbn") String isbn) {
        if(bookWithISBN == null) { //缓冲book为空
            getAndSetBookWithISBN(isbn);
            // 向前端返回，仅展示，未插入数据库
            return bookWithISBN;
        } else if (!Objects.equals(bookWithISBN.getIsbn(), isbn)) {//缓冲book不为空，但不是要找的isbn
            getAndSetBookWithISBN(isbn);
            // 向前端返回，仅展示，未插入数据库
            return bookWithISBN;
        } else {
            //缓冲区就是要找的isbn
            // 向前端返回，仅展示，未插入数据库
            return bookWithISBN;
        }
    }
    //通过ISBN添加
    @GetMapping("/book/addByISBN/{isbn}/{remain}/{location}")
    @Transactional
    public int addBookInfoByISBN(@PathVariable("isbn") String isbn, @PathVariable("remain") int remain, @PathVariable("location")String location) throws IOException {

        if(bookWithISBN == null) {//缓冲book为空
            int res = getAndSetBookWithISBN(isbn);
            if(res == -1) {
                return -1;//接口调用次数不足
            }
        } else if (!Objects.equals(bookWithISBN.getIsbn(), isbn)) {//缓冲book不为空，但不是要找的isbn
            int res = getAndSetBookWithISBN(isbn);
            if(res == -1) {
                return -1;//接口调用次数不足
            }
        }
        //其他：使用缓存区book


        if(staffMapper.isExist(isbn) > 0) { //如果这种书已经在数据库，就只修改remain
            int bookid = staffMapper.isExist(isbn);
            staffMapper.addRemain(bookid, remain);
            return bookid;
        } else { //否则，增加新品种

            Book book = new Book(bookWithISBN.getBookid(), bookWithISBN.getBookname(), bookWithISBN.getPress(), bookWithISBN.getAuthor(),
                    bookWithISBN.getPublishtime(), bookWithISBN.getCatagory(), remain,bookWithISBN.getIntroduction(), location);

            //插入book表，获得bookid
            staffMapper.addBookByISBN(book);
            //插入isbn_bookid表
            staffMapper.add_isbn_bookid(isbn, book.getBookid());

            //制作条形码，存
            for (int i = 1; i <= remain; i++) {
                createBarCode(book.getBookid(), i);
            }
            bookWithISBN = null;//将缓冲的book置null
            // 向前端返回bookid
            return book.getBookid();
        }

    }

    //还书
    @GetMapping("/book/return/{realid}/{returntime}")
    @Transactional
    public int returnBook(@PathVariable("realid") String realid, @PathVariable("returntime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date returntime) {
        //还书，输入real id，日期
        //拿到borrowid
        String borrowid = staffMapper.getBorrowID(realid);
        if (borrowid == null) { //不存在的borrowid (这本书没有被借)
            return -1;
        }

        //查借阅记录
        Borrow borrowRecord = staffMapper.getBorrowRecord(borrowid);
        if(Objects.equals(borrowRecord.getStatus(), "returned")) { //已归还
            return -2;
        }
        if(returntime.getTime() < borrowRecord.getBorrowtime().getTime()) { //还书日期小于借书日期
            return -3;
        }

        //构建 Returned对象
        Returned returned = new Returned();
        returned.setBorrowid(borrowid);
        returned.setReturntime(returntime);
        long outDay = (returntime.getTime() - borrowRecord.getDeadline().getTime()) / (1000*60*60*24);
        if(outDay <= 0) {
            returned.setFineamount(0);
            returned.setIspay(true);
        } else {
            returned.setFineamount((int)outDay * staffMapper.getFineRule());
            returned.setIspay(false);
        }

        int res = 1;

        //改remain
        res *= staffMapper.returnBookRemain(borrowRecord.getBookid());
        //改borrow status
        res *= staffMapper.returnBookBorrowStatus(borrowid);
        //添加return  求fine
        res *= staffMapper.returnBookAddReturn(returned);
        //改book_realid borowid
        res *= staffMapper.returnBookRealidStatus(realid);
        return res;
    }

    // 根据bookid删除书籍
    @DeleteMapping("/book/delete/{bookid}")
    public List<Integer> DeleteBook(@PathVariable("bookid") int bookid) {
        List<Integer> userids = staffMapper.WhoBorrowing(bookid);
        if(userids.size() == 0) {
            //没人在借，全删
            List<Integer> res = new ArrayList<>();
            if(staffMapper.DeleteBook(bookid) == 1) {
                //成功删除
                res.add(-1);
                //还应该删掉资源中的barcode图  算了
            } else {
                //没能成功删除 原因未知
                res.add(-2);
            }
            return res;
        } else {
            //有人在借，返回在借的人的id，remain改0
            //staffMapper.setBookRemainToZero(bookid);
            //还应该去真实id里删掉没在借的
            //太麻烦了，remain也不改0了，直接不让删
            return userids;
        }
    }

    private int getAndSetBookWithISBN(String isbn) {
        bookWithISBN = new BookWithISBN();
        //设置url
        String url = "http://47.99.80.202:6066/openApi/getInfoByIsbn?isbn=" + isbn +"&appKey=ae1718d4587744b0b79f940fbef69e77";
        //访问并获得返回
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        //取出data部分
        JSONObject json = JSONObject.parseObject(response.getBody());
        JSONObject data = json.getJSONObject("data");
        //判断data是否空
        if(data == null) {
            return -1; //接口调用次数不足
        }
        //取出内容
        bookWithISBN.setBookname(data.getString("bookName"));
        bookWithISBN.setPress(data.getString("press"));
        bookWithISBN.setAuthor(data.getString("author"));
        bookWithISBN.setPublishtime(data.getString("pressDate"));
        bookWithISBN.setCatagory("novel");
        bookWithISBN.setIntroduction(data.getString("bookDesc"));
        bookWithISBN.setIsbn(isbn);
        return 0;
    }

    private void createBarCode(int bookid, int offset) throws IOException {
        String barcode = bookid + "0000" + offset;//条形码编号
        String barcodeFileName = barcode + ".jpg";//条形码文件名
        //调用，生成图像
        BufferedImage image = BarCode.getBarCodeWithWords(barcode, barcode, "", "");

        //放项目中
        String directoryPath = new File("src/main/resources/static/barcodes").getAbsolutePath();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String filePath = directoryPath + "/" + barcodeFileName;
        File file = new File(filePath);
        ImageIO.write(image, "jpg", file);

        //放项目的生成文件中
        String directoryPathTar = new File("target/classes/static/barcodes").getAbsolutePath();
        File directoryTar = new File(directoryPathTar);
        if (!directoryTar.exists()) {
            directoryTar.mkdirs();
        }
        String filePathTar = directoryPathTar + "/" + barcodeFileName;
        File fileTar = new File(filePathTar);
        ImageIO.write(image, "jpg", fileTar);

        //barcode内容就作为每本书独特id
        staffMapper.addBookRealID(barcode, bookid, null);
    }



}

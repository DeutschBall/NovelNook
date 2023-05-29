package class4.spm.novelnook.controller;


import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.BorrowRecords;
import class4.spm.novelnook.pojo.Reservation;
import class4.spm.novelnook.pojo.Returned;
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


    //release 1

    //搜索图书信息
    @ResponseBody
    @GetMapping("/search/{key}/{option}")
    public List<Book> searchForBook(@PathVariable("key") String key, @PathVariable("option") String option) {
        return patronServiceImpl.searchForBook(key, option);
    }

    //获取借阅记录
    @ResponseBody
    @GetMapping("/info/borrow/{userid}")
    public List<BorrowRecords> getBorrowList(@PathVariable("userid") int userid) {
        return patronServiceImpl.getBorrowList(userid);
    }

    //获取图书信息
    @ResponseBody
    @GetMapping("/info/book/{bookid}")
    public List<Book> getBookInfo(@PathVariable("bookid") int bookid) {
        System.out.println(bookid);
        return patronServiceImpl.getBookInfo(bookid);
    }

    //借书
    @ResponseBody
    @GetMapping("/borrow/{userid}/{bookid}")
    public String updateBorrow(@PathVariable("userid") int userid, @PathVariable("bookid") int bookid) {
        return patronServiceImpl.updateBorrow(userid, bookid);
    }

    /*--------------------------------------------------------------------------------------*/

    //
    // release 2
    //

    //登录
    @ResponseBody
    @GetMapping("/login/{userid}/{password}")
    public String login(@PathVariable("userid") int userid,@PathVariable("password") String password){
        if(patronServiceImpl.SearchForPassword(userid).equals(password))
            return String.valueOf(userid);
        return "Password is wrong";
    }

    //获取借书数量
    @ResponseBody
    @GetMapping("/count/borrow/{userid}")
    public int  getBorrowCount(@PathVariable("userid") int userid) {
        return patronServiceImpl.getBorrowCount(userid);
    }

    //获取逾期图书数量
    @ResponseBody
    @GetMapping("/count/overdue/{userid}")
    public int getOverdueCount(@PathVariable("userid")  int userid) {
        return patronServiceImpl.getOverdueCount(userid);
    }

    //获取罚单列表
    @ResponseBody
    @GetMapping("/info/ticket/{userid}")
    public List<Returned> getTicketList(@PathVariable("userid") int userid) {
        return patronServiceImpl.getTicketList(userid);
    }

    //获取罚款金额
    @ResponseBody
    @GetMapping("/count/ticket/{userid}")
    public Double  getFineAmount(@PathVariable("userid") int userid) {
        return patronServiceImpl.getFineAmount(userid);
    }

    //预约图书
    @ResponseBody
    @GetMapping("/reserve/{userid}/{bookid}")
    public String reserveBook(@PathVariable("userid") int userid, @PathVariable("bookid") int bookid){
        return patronServiceImpl.reserveBook(userid,bookid);
    }

    //获取预约列表
    @ResponseBody
    @GetMapping("/info/reserve/{userid}")
    public List<Reservation> getReservationList(@PathVariable("userid") int userid){
        return patronServiceImpl.getReservationList(userid);
    }

    //更新并检查预约状态
    @ResponseBody
    @GetMapping("notice/{userid}")
    public List<String> noticeMessage(@PathVariable("userid") int userid){
        return patronServiceImpl.checkReservationStatus(userid);
    }

    //取消预约
    @ResponseBody
    @GetMapping("cancel/{userid}/{bookid}")
    public void cancelReservation(@PathVariable("userid") int userid, @PathVariable("bookid") int bookid){
        patronServiceImpl.cancelReservation(userid, bookid);
    }

    /*--------------------------------------------------------------------------------------*/

    //
    // release 3
    //

    //获取旧密码
    @ResponseBody
    @GetMapping("getoldpwd/{userid}")
    public String getOldPassword(@PathVariable("userid") int userid) {
        return patronServiceImpl.getOldPassword(userid);
    }

    //修改密码
    @ResponseBody
    @GetMapping("updatepwd/{userid}/{newPassword}")
    public void updatePatronPassword(@PathVariable("userid") int userid, @PathVariable("newPassword") String newPassword){
        patronServiceImpl.updatePatronPassword(userid,newPassword);
    }


}

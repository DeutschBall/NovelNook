package class4.spm.novelnook.service;

import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.BorrowRecords;
import class4.spm.novelnook.pojo.Reservation;
import class4.spm.novelnook.pojo.Returned;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface PatronService {

    //搜索图书
    List<Book> searchForBook(String key, String option);

    //获取借阅记录
    List<BorrowRecords> getBorrowList(int userid);

    //获取图书信息
    List<Book> getBookInfo(int bookid);

    //更新借阅信息
    String updateBorrow(int userid,int bookid);


    /*---------------------------------------------------------------------*/

    //
    // release 2
    //

    //登录
    String SearchForPassword(int userid);

    //获取借书数量
    int getBorrowCount(int userid);

    //获取逾期图书数量
    int getOverdueCount(int userid);

    //获取罚单列表
    List<Returned> getTicketList(int userid);

    //获取罚款金额
    Double  getFineAmount(int userid);

    //预约图书
    String reserveBook(int userid, int bookid);

    //获取预约列表
    List<Reservation> getReservationList(int userid);

    //更新并检查预约状态
    List<String> checkReservationStatus(int userid);

    //取消预约
    void cancelReservation(int userid, int bookid);

    /*---------------------------------------------------------------------*/

    //
    // release 3
    //

    //获取旧密码
    String getOldPassword(int userid);

    //修改密码
    void updatePatronPassword(int userid, String newPassword);

}

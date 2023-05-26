package class4.spm.novelnook.mapper;


import class4.spm.novelnook.pojo.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatronMapper {


    //获取所有borrow信息
    @Select("select borrowid,bookid,bookname,location,borrowtime,deadline,status " +
            "from book natural join borrow where userid = #{userid} " +
            "order by borrowtime")
    List<BorrowRecords> getBorrowList(int userid);

    //获取book信息
    @Select("select * from book where bookid = #{bookid}")
    List<Book> getBookInfo(int bookid);

    //搜索book信息
    @Select("select * from book where ${option} like '%${key}%'")
    List<Book> searchForBook(String key, String option);

    //从book表获取remain
    @Select("select remain from book where bookid = #{bookid}")
    int getBookRemain(int bookid);

    //更新book信息，remain-1
    @Update("update book set remain = remain - 1 where bookid = #{bookid}")
    void updateBook(int bookid);

    //从borrow表获取status
    @Select("select status " +
            "from borrow " +
            "where borrowid = " +
            "(select max(borrowid) from borrow where userid = #{userid} and bookid = #{bookid})")
    String getBorrowStatus(int userid, int bookid);

    //增加borrow记录
    @Insert("insert into borrow(borrowid, userid, bookid, borrowtime, deadline, status)"+
            "values(#{borrowid}, #{userid}, #{bookid}, #{borrowtime}, #{deadline}, #{status})")
    void addBorrow(Borrow borrow);

    //查找最大borrowid
    @Select("select max(borrowid) from borrow")
    String getMaxBorrowId();

    /*-----------------------------------------------------------------------------------------*/

    //
    // release 2
    //

    //登录
    @Select("Select password from patron where userid = #{userid}")
    String SearchForPassword(int userid);

    //获取借书数量
    @Select("select count(borrowid) from borrow where userid = #{userid}")
    int getBorrowCount(int userid);

    //获取逾期图书数量
    @Select("select count(borrowid) from borrow where userid = #{userid} and deadline < now()")
    int getOverdueCount(int userid);

    //获取所有罚单信息
    @Select("select borrowid,returntime,fineamount,ispay from borrow natural join returned where userid = #{userid}")
    List<Returned> getTicketList(int userid);

    //获取罚单数量
    @Select("select count(borrowid) from borrow natural join returned where userid = #{userid}")
    String getFineCount(int userid);

    //获取未支付金额
    @Select("select sum(fineamount) from returned natural join borrow where userid = #{userid} and ispay = 0")
    Double getFineAmount(int userid);

    //预约图书
    @Insert("insert into reservation(reservationid,userid, bookid, reservationtime, status)"+
            "values(#{reservationid},#{userid}, #{bookid}, #{reservationtime},#{status})")
    void reserveBook(Reservation reservation);

    //查找未取消预约记录
    @Select("select reservationid from reservation " +
            "where userid = #{userid} and bookid = #{bookid} and status != 'canceled'")
    String checkReservation(int userid, int bookid);

    //更新预约状态为satisfied
    @Update("update reservation set status = 'satisfied' " +
            "where userid = #{userid} and status = 'waiting' " +
            "and reservationid " +
            "in (select reservationid " +
            "from (select reservationid from reservation natural join book where remain != 0) as resid)")
    void updateReservationSatisfied(int userid);

    //更新预约状态为waiting
    @Update("update reservation set status = 'waiting' " +
            "where userid = #{userid} and status = 'satisfied' " +
            "and reservationid " +
            "in (select reservationid " +
            "from (select reservationid from reservation natural join book where remain = 0) as resid)")
    void updateReservationWaiting(int userid);

    //查找satisfied预约记录
    @Select("select bookname from book where bookid in (select bookid from book natural join reservation where status = 'satisfied' and userid = #{userid})")
    List<String> getSatisfiedBook(int userid);

    //获取预约记录
    @Select("select reservationid,bookid,bookname,location,reservationtime,status " +
            "from reservation natural join book where userid = #{userid}")
    List<Reservation> getReservationList(int userid);

    //更新预约状态为finished或canceled
    @Update("update reservation set status = #{status} " +
            "where userid = #{userid} and bookid = #{bookid} and status != 'canceled'")
    void updateReservation(int userid, int bookid, String status);

    /*-----------------------------------------------------------------------------------------*/

    //
    // release 3
    //

    //获取借书限制
    @Select("select limitnum from booklimit")
    Integer getBookLimit();

    //更新book_realid
    @Update("update book_realid set borrowid = #{borrowid} " +
            "where realid in " +
            "(select realid from (select min(realid) as realid from book_realid where bookid = #{bookid} and borrowid IS NULL ) as r)")
    void updateBookRealid(String borrowid, int bookid);
}

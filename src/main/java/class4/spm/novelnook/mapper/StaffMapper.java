package class4.spm.novelnook.mapper;


import class4.spm.novelnook.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaffMapper {

    // 获取所有patron信息
    @Select("select * from patron")
    List<Patron> getAllPatrons();

    //根据userid 找patron
    @Select("select * from patron where userid = #{userid}")
    Patron getPatronById(@Param("userid") int userid);

    //登录用，根据输入id找staff
    @Select("select * from staff where userid = #{userid}")
    Staff getStaffById(@Param("userid") int userid);

    //获得罚款规则金额
    @Select("select * from ")
    int getFineRule();

    //还书
    //1. 查借阅记录
    @Select("select * from borrow where borrowid = #{borrowid}")
    Borrow getBorrowRecord(@Param("borrowid") String borrrowid);
    //2. book remain+1
    @Update("update book set remain = remain + 1 where bookid = #{bookid}")
    int returnBookRemain(@Param("bookid")int bookid);
    //3. borrow status
    @Update("update borrow set status = 'returned' where borrowid = #{borrowid}")
    int returnBookBorrowStatus(@Param("borrowid")String borowid);
    //4. returned new
    @Insert("Insert into returned(borrowid, returntime, fineamount, ispay) values (#{borrowid}, #{returntime}, #{fineamount}, #{ispay})")
    int returnBookAddReturn(Returned returned);

    //书 所有剩余
    @Select("SELECT SUM(remain) FROM book")
    int getBookRemain();

    //书 在借
    @Select("SELECT COUNT(*) FROM borrow WHERE status='borrowing'")
    int getBookBorrowing();

    //patron 所有
    @Select("SELECT COUNT(*) FROM patron")
    int getPatronNum();

    //未交罚款总额
    @Select("SELECT SUM(returned.fineamount) AS total_fine FROM returned WHERE returned.ispay = 0")
    int getUnpayAmount();

    //本人信息
    @Select("select * from staff where userid = #{userid}")
    Staff getSelf(int userid);

    //所有未交罚款信息
    @Select("SELECT borrow.borrowid, borrow.userid, book.bookname, borrow.borrowtime, borrow.deadline, returned.returntime, returned.fineamount " +
            "FROM book " +
            "JOIN borrow ON book.bookid = borrow.bookid " +
            "JOIN returned ON borrow.borrowid = returned.borrowid " +
            "WHERE borrow.status = 'returned' and returned.ispay = 0")
    List<FineInfo> getUnpayInfoAll();

//patron
    //获取一个patron信息
    @Select("select * from patron where userid = #{userid}")
    Patron getOnePatron(@Param("userid")int userid);

    //增加
    @Insert("insert into Patron(password, firstname, lastname, email, telephone, avatar)" +
            "values(#{password}, #{firstname}, #{lastname}, #{email}, #{telephone}, #{avatar})")
    @Options(useGeneratedKeys = true, keyProperty = "userid",keyColumn = "userid")
    int addPatron(Patron patron);

    //删除
    @Delete("delete from patron where userid = #{userid}")
    int deletePatron(@Param("userid")int userid);

    //更改
    @Update("update patron set password = #{password}, firstname = #{firstname}, lastname = #{lastname}, email = #{email}, telephone = #{telephone} where userid = #{userid}" )
    int updatePatron(@Param("userid")int userid, @Param("password")String password, @Param("firstname")String firstname, @Param("lastname")String lastname, @Param("email")String email, @Param("telephone")String telephone);


}

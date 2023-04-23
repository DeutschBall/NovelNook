package class4.spm.novelnook.mapper;


import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Borrow;
import class4.spm.novelnook.pojo.Patron;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatronMapper {


    //获取所有borrow信息
    @Select("select * from borrow where userid = #{userid}")
    List<Borrow> getBorrowList(String userid);

    //获取book信息
    @Select("select * from book where bookid = #{bookid}")
    List<Book> getBookInfo(String bookid);

    //搜索book信息
    @Select("select * from book where ${option} like '%${key}%'")
    List<Book> searchForBook(String key, String option);

    //从book表获取remain
    @Select("select remain from book where bookid = #{bookid}")
    int getBookRemain(String bookid);

    //更新book信息，remain-1
    @Update("update book set remain = remain - 1 where bookid = #{bookid}")
    int updateBook(String bookid);

    //从borrow表获取status
    @Select("select status " +
            "from borrow " +
            "where borrowid = (select max(borrowid) from borrow where userid = #{userid} and bookid = #{bookid})")
    String getBorrowStatus(String userid, String bookid);

    //增加borrow记录
    @Insert("insert into borrow(borrowid, userid, bookid, borrowtime, deadline, status)"+
            "values(#{borrowid}, #{userid}, #{bookid}, #{borrowtime}, #{deadline}, #{status})")
    int addBorrow(Borrow borrow);

    //查找最大borrowid
    @Select("select max(borrowid) from borrow")
    String getMaxBorrowId();


}

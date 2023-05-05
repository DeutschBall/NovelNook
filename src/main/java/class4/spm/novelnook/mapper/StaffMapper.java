package class4.spm.novelnook.mapper;


import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Borrow;
import class4.spm.novelnook.pojo.Patron;
import class4.spm.novelnook.pojo.Returned;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaffMapper {

    // 获取所有patron信息
    @Select("select * from patron")
    List<Patron> getAllPatrons();

    //还书
    //1. 查借阅记录
    @Select("select * from borrow where borrowid = #{borrowid}")
    Borrow getBorrowRecord(@Param("borrowid") String borrrowid);
    //2. book remain+1
    @Update("update book set remain = remain + 1 where bookid = #{bookid}")
    int returnBookRemain(@Param("bookid")String bookid);
    //3. borrow status
    @Update("update borrow set status = 'returned' where borrowid = #{borrowid}")
    int returnBookBorrowStatus(@Param("borrowid")String borowid);
    //4. returned new
    @Insert("Insert into returned(borrowid, returntime, fineamount, ispay) values (#{borrowid}, #{returntime}, #{fineamount} #{ispay})")
    int returnBookAddReturn(Returned returned);


}

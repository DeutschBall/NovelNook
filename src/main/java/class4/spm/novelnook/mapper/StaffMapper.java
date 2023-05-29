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


    //登录用，根据输入id找staff
    @Select("select * from staff where userid = #{userid}")
    Staff getStaffById(@Param("userid") int userid);

    //获得罚款规则金额
    @Select("select money from fine ORDER BY id DESC LIMIT 1;")
    double getFineRule();

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
    double getUnpayAmount();
    //已交罚款总额
    @Select("SELECT SUM(returned.fineamount) AS total_fine FROM returned WHERE returned.ispay = 1")
    double getPaidAmount();

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

    //所有已交罚款信息
    @Select("SELECT borrow.borrowid, borrow.userid, book.bookname, borrow.borrowtime, borrow.deadline, returned.returntime, returned.fineamount " +
            "FROM book " +
            "JOIN borrow ON book.bookid = borrow.bookid " +
            "JOIN returned ON borrow.borrowid = returned.borrowid " +
            "WHERE borrow.status = 'returned' and returned.ispay = 1")
    List<FineInfo> getPaidInfoAll();

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




//BOOK
    // 获取所有book信息
    @Select("SELECT b.*, ib.isbn FROM book b JOIN isbn_bookid ib ON b.bookid = ib.bookid")
    List<BookWithISBN> getAllBooks();

    // 删除书
    @Delete("DELETE FROM book WHERE bookid = #{bookid};")
    int DeleteBook(@Param("bookid") int bookid);
    //要删的书，谁在借
    @Select("SELECT userid FROM borrow WHERE bookid = #{bookid} AND status = 'borrowing'")
    List<Integer> WhoBorrowing(@Param("bookid") int bookid);
    //remain = 0而非删除
    @Update("UPDATE book SET remain = 0 WHERE bookid = #{bookid}")
    int setBookRemainToZero(@Param("bookid") int bookid);

    //删掉isbn_bookid   没必要，有外键约束自动删了
    //@Delete("DELETE FROM isbn_bookid WHERE bookid = #{bookid};")
    //int DeleteBook_ISBN(@Param("bookid") int bookid);

    // 增加新书
    @Insert("INSERT INTO book(bookname, press, author, publishtime, catagory, remain, introduction, location) VALUES (#{bookname}, #{press}, #{author}, #{publishtime}, #{catagory}, #{remain}, #{introduction}, #{location})")
    int AddNewBook(@Param("bookname") String bookname, @Param("press") String press,
                   @Param("author") String author, @Param("publishtime") String publishtime,
                   @Param("catagory") String catagory, @Param("remain") int remain,
                   @Param("introduction") String introduction, @Param("location") String location);


    // 修改书
    @Update("update book set bookname = #{bookname}, press = #{press}, author =  #{author}, publishtime = #{publishtime}, catagory =  #{catagory},  remain = #{remain} , introduction = #{introduction}, location = #{location} where bookid = #{bookid}")
    int UpdateBook(@Param("bookid") int bookid, @Param("bookname") String bookname,
                   @Param("press") String press,
                   @Param("author") String author, @Param("publishtime") String publishtime,
                   @Param("catagory") String catagory, @Param("remain") int remain,
                   @Param("introduction") String introduction, @Param("location") String location);

   //支付成功后的，return的ispay变成1
   @Update("update returned set ispay = 1 where borrowid = #{borrowid}")
   int UpdateIspay(@Param("borrowid") String borrowid);

    //添加 addBookByISBN
    @Insert("INSERT INTO book(bookname, press, author, publishtime, catagory, remain, introduction, location) VALUES (#{bookname}, #{press}, #{author}, #{publishtime}, #{catagory}, #{remain}, #{introduction}, #{location})")
    @Options(useGeneratedKeys = true, keyProperty = "bookid",keyColumn = "bookid")
    int addBookByISBN(Book book);
    @Insert("INSERT INTO isbn_bookid(isbn, bookid) values (#{isbn}, #{bookid})")
    int add_isbn_bookid(@Param("isbn") String isbn, @Param("bookid") int bookid);
    @Insert("INSERT INTO book_realid(realid, bookid, borrowid) values (#{realid}, #{bookid}, #{borrowid})")
    int addBookRealID(@Param("realid") String realid, @Param("bookid") int bookid, @Param("borrowid") String borrowid);
    @Select("SELECT IFNULL(sum(bookid), -1) AS result FROM isbn_bookid WHERE isbn = #{isbn}")
    int isExist(@Param("isbn") String isbn);
    @Update("UPDATE book SET remain = remain + #{newRemain} WHERE bookid = #{bookid}")
    int addRemain(@Param("bookid") int bookid, @Param("newRemain") int newRemain);


    //由bookname查书
    @Select("SELECT b.*, ib.isbn FROM book b JOIN isbn_bookid ib ON b.bookid = ib.bookid where bookname LIKE CONCAT('%', #{bookname}, '%')")
    BookWithISBN serachByBookName(@Param("bookname") String bookname);


    //根据userid 找patron
    @Select("select * from patron where userid = #{userid}")
    Patron getPatronById(@Param("userid") int userid);
    //根据firstname 找patron
    @Select("select * from patron where firstname = #{firstname}")
    List<Patron> getPatronByFirstname(@Param("firstname") String firstname);
    //根据lastname 找patron
    @Select("select * from patron where lastname = #{lastname}")
    List<Patron> getPatronByLastname(@Param("lastname") String lastname);


    //还书
    //拿到borrowid
    @Select("select borrowid from book_realid where realid = #{realid}")
    String getBorrowID(@Param("realid") String realid);
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
    //5. book_realid borrowid=null
    @Update("update book_realid set borrowid = null where realid = #{realid}")
    int returnBookRealidStatus(@Param("realid")String realid);

    //borrow history
    @Select("SELECT borrow.userid, book.bookname, borrow.borrowtime, borrow.deadline, borrow.status FROM borrow JOIN book ON borrow.bookid = book.bookid;")
    List<StaffBorrowHistroy> allBorrowHistory();

}

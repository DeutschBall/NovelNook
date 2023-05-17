package class4.spm.novelnook.service;

import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.FineInfo;
import class4.spm.novelnook.pojo.Patron;
import class4.spm.novelnook.pojo.Staff;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface StaffService {

    // 获取所有patron信息
    List<Patron> getAllPatrons();

    //根据userid 找patron
    Patron getPatronById(int userid);

    //根据Id 找staff
    Staff getStaffById(int userid);

    //还书
    int returnBook(String borrowid, Date returntime);

    //书剩余量
    int getBookRemain();

    //书总量
    int getBookTotal();

    //patron 所有
    int getPatronNum();

    //未交罚款总额
    int getUnpayAmount();

    //本人信息
    Staff getSelf(int userid);

    //所有未交罚款信息
    List<FineInfo> getUnpayInfoAll();

//patron
    //获取一个
    Patron getOnePatron(int userid);

    //增
    int addPatron(String firstname,String lastname,String email,String telephone);

    //删
    int deletePatron(int userid);

    //改
    int updatePatron(int userid, String password, String firstname, String lastname, String email, String telephone);


//book
    // 查询所有书
    List<Book> getAllBooks();

    // 增加书
    int AddNewBook(String bookname, String press, String author, String publishtime, String catagory, int remain,
                   String introduction, String location);

    // 删除书
    int DeleteBook(int bookid);

    // 改书
    int UpdateBook(int bookid, String bookname, String press, String author, String publishtime, String catagory,
                   int remain, String introduction, String location);

    //支付成功后的，return的ispay变成1
    int UpdateIspay(String borrowid);

}

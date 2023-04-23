package class4.spm.novelnook.service;

import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Patron;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {

    // 获取所有patron信息
    List<Patron> getAllPatrons();

    //根据userid获取patron信息
    Patron getPatronById(String userid);

    //根据bookid获取书信息
    Book getBookById(String bookid);

    //根据userid删除
    int DeleteParton(String userid);

    //增加用户
    int AddPatron (String userid,String password,String firstname,String lastname,String email,String telephone,String avatarUrl);

    //增加新书
    int AddNewBook (String bookid, String bookname, String press, String author, String publishtime, String catagory, int remain, String introduction);

    //删书
    int DeleteBook (String bookid);

    //还书
    int putBookByBookidUserid(String bookid, String userid);

}

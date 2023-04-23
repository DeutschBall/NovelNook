package class4.spm.novelnook.service;

import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Borrow;
import class4.spm.novelnook.pojo.Patron;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatronService {

    //搜索图书
    List<Book> searchForBook(String key, String option);

    //获取借阅记录
    List<Borrow> getBorrowList(String userid);

    //获取图书信息
    List<Book> getBookInfo(String bookid);

    //更新借阅信息
    String updateBorrow(String userid,String bookid);
}

package class4.spm.novelnook.service;

import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Patron;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface StaffService {

    // 获取所有patron信息
    List<Patron> getAllPatrons();

    //还书
    int returnBook(String borrowid, Date date);

}

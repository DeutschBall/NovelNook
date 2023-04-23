package class4.spm.novelnook.service;

import class4.spm.novelnook.mapper.StaffMapper;
import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffMapper staffMapper;

    // 获取所有patron信息  具体实现
    public List<Patron> getAllPatrons() {
        return staffMapper.getAllPatrons();
    }

    //根据userid获取patron信息 具体实现
    @Override
    public Patron getPatronById(String userid) {
        return staffMapper.getPatronById(userid);
    }

    //根据bookid获取书信息 具体实现
    @Override
    public Book getBookById(String bookid) {
        return staffMapper.getBookById(bookid);
    }

    //根据userid删除patron具体实现
    @Override
    public int DeleteParton(String userid) { return  staffMapper.DeletePatron(userid); }

    //增加用户具体实现
    @Override
    public  int AddPatron(String userid,String password,String firstname,String lastname,String email,String telephone ,String avatarUrl) {
        return staffMapper.AddPatron(userid, password, firstname, lastname, email, telephone,avatarUrl);
    }

    //具体实现增加新书
    @Override
    public int AddNewBook (String bookid, String bookname, String press ,String author ,String publishtime ,String catagory, int remain , String introduction){
        return staffMapper.AddNewBook(bookid, bookname, press, author, publishtime, catagory, remain, introduction);
    }

    //具体实现删除书
    @Override
    public int DeleteBook (String bookid){
        return staffMapper.DeleteBook(bookid);
    }

    //还书 实现
    @Override
    public int putBookByBookidUserid(String bookid, String userid){
        return staffMapper.putBookByBookid(bookid) & staffMapper.putBookByBookidUserid(bookid, userid);
    }



}

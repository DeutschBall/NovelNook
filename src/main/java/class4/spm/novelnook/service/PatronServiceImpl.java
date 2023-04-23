package class4.spm.novelnook.service;

import class4.spm.novelnook.mapper.PatronMapper;
import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Borrow;
import class4.spm.novelnook.pojo.Patron;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PatronServiceImpl implements PatronService{

    @Autowired
    PatronMapper patronMapper;

    //
    // 对Controller接口
    //

    //搜索图书
    public List<Book> searchForBook(String key, String option) {
        return patronMapper.searchForBook(key, option);
    }

    //获取借阅记录
    public List<Borrow> getBorrowList(String userid) {
        return patronMapper.getBorrowList(userid);
    }

    //获取图书信息
    public List<Book> getBookInfo(String bookid) {
        return patronMapper.getBookInfo(bookid);
    }

    //更新借阅信息
    public String updateBorrow(String userid,String bookid) {
        String borrowstatus = patronMapper.getBorrowStatus(userid, bookid);

        //条件判断
        if(borrowstatus != null && borrowstatus.equals("borrowing"))
            return "Failed: You have already borrowed the material!";
        if(updateBook(bookid) == 0)
            return "Failed: No materials remain!";

        //添加新的borrow数据
        Date borrowtime = new Date();
        Borrow borrow = new Borrow();
        borrow.setBorrowid(getNewBorrowId());
        borrow.setUserid(userid);
        borrow.setBookid(bookid);
        borrow.setBorrowtime(borrowtime);
        borrow.setDeadline(getDeadline(borrowtime));
        borrow.setStatus("borrowing");

        //更新数据库
        patronMapper.addBorrow(borrow);
        return "success!";
    }

    //
    //------------------------------------------------------------------------------------------
    //


    //
    // 辅助方法
    //

    //每次借书更新remain，remain为0更新失败，返回0，成功返回1
    int updateBook(String bookid) {
        if(patronMapper.getBookRemain(bookid) == 0)   return 0;
        patronMapper.updateBook(bookid);
        return 1;
    }

    //获取deadline
    //目前没有确定明确要求，暂定时间为一个月
    Date getDeadline(Date sourceDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, 1);

        return c.getTime();
    }

    //获取最新borrowid
    //在数据库中最大borrowid基础上+1
    String getNewBorrowId() {
        String maxid = patronMapper.getMaxBorrowId();
        String[] cs = maxid.split("BO");
        int n = cs[1].length();
        int nums = Integer.parseInt(cs[1])+1;
        String newnum = String.valueOf(nums);
        n = Math.min(n, newnum.length());
        return maxid.subSequence(0,maxid.length()-n) + newnum;
    }
}

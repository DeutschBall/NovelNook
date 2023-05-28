package class4.spm.novelnook.service;

import class4.spm.novelnook.mapper.PatronMapper;
import class4.spm.novelnook.pojo.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
public class PatronServiceImpl implements PatronService{

    @Autowired
    PatronMapper patronMapper;
    @Autowired
    MailService mailService;

    //
    // 对Controller接口
    //

    //搜索图书
    public List<Book> searchForBook(String key, String option) {
        return patronMapper.searchForBook(key, option);
    }

    //获取借阅记录
    public List<BorrowRecords> getBorrowList(int userid) {
        return patronMapper.getBorrowList(userid);
    }

    //获取图书信息
    public List<Book> getBookInfo(int bookid) {
        return patronMapper.getBookInfo(bookid);
    }

    //更新借阅信息
    public String updateBorrow(int userid,int bookid) {
        String borrowstatus = patronMapper.getBorrowStatus(userid, bookid);

        //条件判断
        if(patronMapper.getBorrowCount(userid)>=patronMapper.getBookLimit())
            return "Failed: You have too many borrowing books!";
        /*
        if(patronMapper.getOverdueCount(userid)>=5)
            return "Failed: You have too many overdue books!";
        if(Integer.parseInt(patronMapper.getFineCount(userid))>=5)
            return "Failed: You have too many unpaid fines!";
         */
        if(borrowstatus != null && borrowstatus.equals("borrowing"))
            return "Failed: You have already borrowed the material!";
        if(updateBook(bookid) == 0)
            return "Failed: No materials remain!";

        if(patronMapper.checkReservation(userid, bookid)!=null)
            patronMapper.updateReservation(userid, bookid, "finished");
        //添加新的borrow数据
        Date borrowtime = new Date();
        String borrowid = String.valueOf(UUID.randomUUID());

        Borrow borrow = new Borrow();
        borrow.setBorrowid(borrowid);
        borrow.setUserid(userid);
        borrow.setBookid(bookid);
        borrow.setBorrowtime(borrowtime);
        borrow.setDeadline(getDeadline(borrowtime));
        borrow.setStatus("borrowing");

        //更新数据库
        patronMapper.addBorrow(borrow);
        patronMapper.updateBookRealid(borrowid, bookid);
        return "Success!";
    }

    /*------------------------------------------------------------------------------------------*/

    //
    // release 2
    //

    //登录
    public String SearchForPassword(int userid) {
        return patronMapper.SearchForPassword(userid);
    }

    //获取借书数量
    public int getBorrowCount(int userid) {
        return patronMapper.getBorrowCount(userid);
    }

    //获取逾期图书数量
    public int getOverdueCount(int userid) {
        return patronMapper.getOverdueCount(userid);
    }

    //获取罚单列表
    public List<Returned> getTicketList(int userid) {
        return patronMapper.getTicketList(userid);
    }

    //获取罚款金额
    public Double  getFineAmount(int userid) {
        return patronMapper.getFineAmount(userid);
    }

    //预约图书
    public String reserveBook(int userid,int bookid) {
        if(patronMapper.checkReservation(userid, bookid)!=null)
            return "Failed, you have already reserved the book!";
        Reservation reservation = new Reservation(userid, bookid, new Date(), "waiting");
        patronMapper.reserveBook(reservation);
        return "Success!";
    }

    //更新并检查预约状态
    public List<String> checkReservationStatus(int userid) {
        patronMapper.updateReservationSatisfied(userid);
        patronMapper.updateReservationWaiting(userid);

        return patronMapper.getSatisfiedBook(userid);
    }

    //获取预约列表
    public List<Reservation> getReservationList(int userid) {
        return patronMapper.getReservationList(userid);
    }

    //取消预约
    public void cancelReservation(int userid, int bookid) {
        patronMapper.updateReservation(userid, bookid, "canceled");
    }

    /*------------------------------------------------------------------------------------------*/

    //
    // release 3
    //

    //获取旧密码
    public String getOldPassword(int userid) {
        return patronMapper.getOldPassword(userid);
    }

    //修改密码
    public void updatePatronPassword(int userid, String newPassword) {
        patronMapper.updatePatronPassword(userid, newPassword);
    }

    //定时发送邮件
    @Scheduled(cron ="0 0 12 * * ?")   //设置发送时间：秒、分、时、日、月、周，*代表所有值，？代表不指定，当前设置为每天12：00
    public void sendEmail(){
        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> emails = patronMapper.getEmail(current);
        StringBuilder text = new StringBuilder("");
        System.out.println(emails);
        for (String email : emails) {
            List<BorrowRecords> books = patronMapper.getUnreturnedBook(email, current);
            for(BorrowRecords book : books) {
                text.append(book.getBookname()).append(" - Deadline:").append(sdf.format(book.getDeadline())).append("\n");
            }
            mailService.sendMail(email, text.toString());

        }
    }

    //
    // 辅助方法
    //

    //每次借书更新remain，remain为0更新失败，返回0，成功返回1
    int updateBook(int bookid) {
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

    /*
    //获取最新borrowid
    //在数据库中最大borrowid基础上+1
    String getNewBorrowId() {
        String maxid = patronMapper.getMaxBorrowId();
        if(maxid==null)
            return "brid001";
        String[] cs = maxid.split("brid");
        int n = cs[1].length();
        int nums = Integer.parseInt(cs[1])+1;
        String newnum = String.valueOf(nums);
        n = Math.min(n, newnum.length());
        return maxid.subSequence(0,maxid.length()-n) + newnum;
    }
     */

}

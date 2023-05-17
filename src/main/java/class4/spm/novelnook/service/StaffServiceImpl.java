package class4.spm.novelnook.service;

import class4.spm.novelnook.mapper.StaffMapper;
import class4.spm.novelnook.pojo.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffMapper staffMapper;


    // 获取所有patron信息
    public List<Patron> getAllPatrons() {
        return staffMapper.getAllPatrons();
    }

    public Patron getPatronById(int userid) {
        return staffMapper.getPatronById(userid);
    }

    //根据Id 找staff
    public Staff getStaffById(int userid){
        return staffMapper.getStaffById(userid);
    }

    //还书
    public int returnBook(String borrowid, Date returntime) {

        Borrow borrowRecord = staffMapper.getBorrowRecord(borrowid);

        //不存在的borrowid
        if(borrowRecord == null) {
            return -1;
        }

        //已归还
        if(Objects.equals(borrowRecord.getStatus(), "returned")) {
            return -2;
        }

        //还书日期小于借书日期
        if(returntime.getTime() < borrowRecord.getBorrowtime().getTime()) {
            return -3;
        }

        //构建 Returned对象
        Returned returned = new Returned();
        returned.setBorrowid(borrowid);
        returned.setReturntime(returntime);
        long outDay = (returntime.getTime() - borrowRecord.getDeadline().getTime()) / (1000*60*60*24);
        if(outDay <= 0) {
            returned.setFineamount(0);
            returned.setIspay(true);
        } else {
            returned.setFineamount((int)outDay * staffMapper.getFineRule());
            returned.setIspay(false);
        }

        //更新数据库
        return staffMapper.returnBookRemain(borrowRecord.getBookid())
                * staffMapper.returnBookBorrowStatus(borrowid)
                * staffMapper.returnBookAddReturn(returned);

    }

    //书剩余量
    public int getBookRemain(){
        return staffMapper.getBookRemain();
    }

    //书总量
    public int getBookTotal(){
        return staffMapper.getBookRemain() + staffMapper.getBookBorrowing();
    }

    //patron 所有
    public int getPatronNum(){
        return staffMapper.getPatronNum();
    }

    //未交罚款总额
    public double getUnpayAmount() {
        return staffMapper.getUnpayAmount();
    }

    //本人信息
    public Staff getSelf(int userid) {
        return staffMapper.getSelf(userid);
    }

    //所有未交罚款信息
    public List<FineInfo> getUnpayInfoAll() {
        return staffMapper.getUnpayInfoAll();
    }

    //查一个
    @Override
    public Patron getOnePatron(int userid){
        return staffMapper.getOnePatron(userid);
    }

    //增加
    @Override
    public int addPatron(String firstname,String lastname,String email,String telephone) {
        Patron patron = new Patron();
        Random random;
        random = new Random();
        StringBuilder valSb = new StringBuilder();
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyz";
        int charLength = charStr.length();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(charLength);
            valSb.append(charStr.charAt(index));
        }
        String password;
        password = valSb.toString();

        random = new Random();
        String avatar = "avatars/" + (random.nextInt(9) + 1) +  ".svg";

        patron.setPassword(password);
        patron.setFirstname(firstname);
        patron.setLastname(lastname);
        patron.setEmail(email);
        patron.setTelephone(telephone);
        patron.setAvatar(avatar);
        staffMapper.addPatron(patron);
        return patron.getUserid();
    }

    //删除
    @Override
    public int deletePatron(int userid){
        return staffMapper.deletePatron(userid);
    }

    //修改
    @Override
    public int updatePatron(int userid, String password, String firstname, String lastname, String email, String telephone){
        return  staffMapper.updatePatron(userid, password, firstname, lastname, email, telephone);
    }


//book
    // 获取所有书信息
    public List<Book> getAllBooks() {
        return staffMapper.getAllBooks();
    }

    // 增加新书
    @Override
    public int AddNewBook(String bookname, String press, String author, String publishtime, String catagory, int remain,
                          String introduction, String location) {
        return staffMapper.AddNewBook(bookname, press, author, publishtime, catagory, remain, introduction, location);
    }

    // 删除书
    @Override
    public int DeleteBook(int bookid) {
        return staffMapper.DeleteBook(bookid);
    }

    // 更新书的信息
    @Override
    public int UpdateBook(int bookid, String bookname, String press, String author, String publishtime,
                          String catagory, int remain, String introduction, String location) {
        return staffMapper.UpdateBook(bookid, bookname, press, author, publishtime, catagory, remain, introduction,location);
    }

    //支付成功后的，return的ispay变成1
    public int UpdateIspay(String borrowid) {
        return staffMapper.UpdateIspay(borrowid);
    }


}

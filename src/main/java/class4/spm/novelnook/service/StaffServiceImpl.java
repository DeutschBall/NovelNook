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

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffMapper staffMapper;

    //过了ddl，每一天罚 1 镑（由admin组定）
    int finePerDay = 1;

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

        //已归还
        if(Objects.equals(borrowRecord.getStatus(), "returned")) {
            return 0;
        }

        //Returned对象
        Returned returned = new Returned();
        returned.setBorrowid(borrowid);
        returned.setReturntime(returntime);
        long outDay = (returntime.getTime() - borrowRecord.getDeadline().getTime()) / (1000*60*60*24);
        if(outDay <= 0) {
            returned.setFineamount(0);
            returned.setIspay(true);
        } else {
            returned.setFineamount((int)outDay * finePerDay);
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
    public int getUnpayAmount() {
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

}

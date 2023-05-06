package class4.spm.novelnook.service;

import class4.spm.novelnook.mapper.StaffMapper;
import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Borrow;
import class4.spm.novelnook.pojo.Patron;
import class4.spm.novelnook.pojo.Returned;
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

    //过了ddl，每一天罚 1 镑（老师规定的）
    int finePerDay = 1;

    // 获取所有patron信息
    public List<Patron> getAllPatrons() {
        return staffMapper.getAllPatrons();
    }

    public Patron getPatronById(int userid) {
        return staffMapper.getPatronById(userid);
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
}

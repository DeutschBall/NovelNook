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

    //过了ddl，每一天罚 1 镑（老师规定的）
    int finePerDay = 1;

    // 获取所有patron信息  实现
    public List<Patron> getAllPatrons() {
        return staffMapper.getAllPatrons();
    }





}

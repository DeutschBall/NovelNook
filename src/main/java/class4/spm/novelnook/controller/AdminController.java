package class4.spm.novelnook.controller;

import class4.spm.novelnook.mapper.AdminMapper;
import class4.spm.novelnook.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;
import tool.R;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminMapper adminMapper;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


    @GetMapping("/staff")
    public R<List<Staff>> getStaff(){//展示列表界面
        List<Staff> list = adminMapper.ShowStaff();
        return R.success(list);

    }
//    @GetMapping("/staff/{username}")
//    public List<Staff> getStaffByUsername(@PathVariable("username") String username){//列表界面查找
//        System.out.println(username);
//        List<Staff> list=adminMapper.getStaffByUserName(username);
//        if (list.isEmpty()) {
//            list.add(new Staff("can't", "find", "this", "User", " ", " "," "));
//        }
//        return list;
//    }

    @GetMapping("/staff/{username}")
    public R<List<Staff>>  getStaffByUsername(@PathVariable("username") String username){//列表界面查找
        System.out.println(username);
        List<Staff> list=adminMapper.getStaffByUserName(username);
//        R<Staff> r = (R<Staff>) list;
        if (list.isEmpty()) {
            return R.error("No such person");
        }
        return R.success(list);
//        r.success(list);
//        return r.success(list);
    }

    @DeleteMapping("/staff/{username}")
    public R<List<Staff>> deleteStaffByUsername(@PathVariable("username") String username){//列表界面删除
        System.out.println(username);
        List<Staff> l  = adminMapper.getStaffByUserName(username);
//        System.out.println(list);
        if (l == null) {
            return R.error("Delete fail");
        }
        else {
            adminMapper.deleteStaffByUserName(username);
            return R.success();
        }
    }
    
    /**
     * update function
     * @param staff param got from json data in RequestBody
     * @return
     */
    @PutMapping("/staff")
    public R update(@RequestBody Staff staff) {
        //flag is used to judge whether operation is success
        int flag = adminMapper.updateByUserName(staff);

        if(flag > 0) {
            return R.success(null);
        }

        return R.error("update fail");

    }

}

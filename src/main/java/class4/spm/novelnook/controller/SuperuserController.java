package class4.spm.novelnook.controller;

import class4.spm.novelnook.pojo.Staff;
import class4.spm.novelnook.pojo.Superuser;
import class4.spm.novelnook.service.SuperuserService;
import class4.spm.novelnook.service.SuperuserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tool.R;
import class4.spm.novelnook.pojo.admin;

import java.util.List;
import class4.spm.novelnook.pojo.admin;

@RestController
@RequestMapping("/api/superuser")
public class SuperuserController {

    @Autowired
    private SuperuserService superuserService;

    @GetMapping("/get-admin")
    public List<admin> getAdminList(){//展示列表界面
        return superuserService.getAdminList();
    }

    // 添加超级管理员
    @PostMapping("/add-superuser")
    public void addSuperuser(@RequestBody Superuser superuser) {
        superuserService.save(superuser);
    }

    // 添加普通管理员
    @GetMapping("/add-admin")
    public void addAdmin(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("password") String password) {
        superuserService.addAdmin(firstname, lastname, password);
    }

    // 删除普通管理员
    @GetMapping("/delete-admin")
    public void deleteAdmin(@RequestParam("id") Long id) {
        superuserService.deleteAdmin(id);
    }

    //弃用
    // 根据用户名查找超级管理员
    @GetMapping("/find-by-username/{username}")
    public void findByUsername(@PathVariable("username") String username) {
        superuserService.findByUsername(username);
    }

    @GetMapping("/find-by-userid")
    public admin findByUserid(@RequestParam int userid){return superuserService.findByUserid(userid);}
}

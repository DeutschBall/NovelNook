package class4.spm.novelnook.controller;

import class4.spm.novelnook.pojo.Superuser;
import class4.spm.novelnook.service.SuperuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/superuser")
public class SuperuserController {

    @Autowired
    private SuperuserService superuserService;

    // 添加超级管理员
    @PostMapping("/add-superuser")
    public void addSuperuser(@RequestBody Superuser superuser) {
        superuserService.save(superuser);
    }

    // 添加普通管理员
    @PostMapping("/add-admin")
    public void addAdmin(@RequestParam("username") String username, @RequestParam("password") String password) {
        superuserService.addAdmin(username, password);
    }

    // 删除普通管理员
    @DeleteMapping("/delete-admin/{id}")
    public void deleteAdmin(@PathVariable("id") Long id) {
        superuserService.deleteAdmin(id);
    }

    // 根据用户名查找超级管理员
    @GetMapping("/find-by-username/{username}")
    public void findByUsername(@PathVariable("username") String username) {
        superuserService.findByUsername(username);
    }
}

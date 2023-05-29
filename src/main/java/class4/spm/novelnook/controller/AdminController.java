package class4.spm.novelnook.controller;

import class4.spm.novelnook.mapper.AdminMapper;
import class4.spm.novelnook.pojo.Staff;
import class4.spm.novelnook.pojo.admin;
import class4.spm.novelnook.pojo.Fine;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import tool.R;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;
import java.io.File;
import java.util.UUID;

@RestController
@CrossOrigin
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
        System.out.println(list);
        System.out.println("actived");
        return R.success(list);

    }

    @GetMapping("/staff/{userid}")
//    @PathVariable是用来接收请求路径中的参数值
    public R<List<Staff>>  getStaffByUsername(@PathVariable("userid") int userid){//列表界面查找
        System.out.println(userid);
        List<Staff> list=adminMapper.getStaffByUserName(userid);
//        R<Staff> r = (R<Staff>) list;
        if (list.isEmpty()) {
            return R.error("No such person");
        }
        return R.success(list);
//        r.success(list);
//        return r.success(list);
    }

    @DeleteMapping("/staff/{userid}")
    public R<List<Staff>> deleteStaffByUsername(@PathVariable("userid") int userid){//列表界面删除
        System.out.println(userid);
        List<Staff> l  = adminMapper.getStaffByUserName(userid);
//        System.out.println(list);
        if (l == null) {
            return R.error("Delete fail");
        }
        else {
            adminMapper.deleteStaffByUserName(userid);
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

    /**
     * Add new staff function
     * @param staff param got from json data in RequestBody
     * @return
     */
    @PostMapping ("/staff")
    public R addNewStaff(@RequestBody Staff staff) {

        int flag = adminMapper.addNewStaff(staff);

        if (flag > 0) {
            return R.success(null);
        }

        return R.error("Add new staff fail.\n" + "This staff maybe exist.");
    }

    /**
     * User can upload their own pictures as their avatar
     * @param file   pictures upload by user
     * @return
     */
    @PostMapping("/uploadAvatar")
    public R uploadAvatar(MultipartFile file){

        //judge whether file is null
        if (file.isEmpty()) {
            return R.error("file not exist or file type error");
        }

        //rename file
        String originalFilename = file.getOriginalFilename();
        //get suffix: .png, .jpg, etc;
        String suffix = "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //create a random file name
        String uuid = UUID.randomUUID().toString().replace("-","");

        //upload file
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath()
                + "\\src\\main\\resources\\static\\avatars\\";
        String path = pre + uuid + suffix;

        try {
            file.transferTo(new File(path));
            return R.success(path);     //send path to frontend
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.error("upload avatar fail");
    }    


    //只有以管理员身份登录之后才能访问/admin/*的所有url
    @PostMapping("/login")
    public R login(HttpServletResponse response, @RequestParam("userid") int userid, @RequestParam("password") String password,HttpSession session) throws IOException {
        // 验证用户名和密码是否匹配
        List<admin> list = adminMapper.getAdminByUserId(userid);
        admin Ad = adminMapper.getAdminByUserId_a(userid);
        String Password = adminMapper.getPasswordByUserid(userid);
        if (!list.isEmpty() && Password.equals(password)) {
            // 登录成功，设置cookie等
            response.addCookie(new Cookie("userid", String.valueOf(userid)));
            session.setAttribute("userid",userid);
//            response.sendRedirect("/admin/index.html");
//            response.sendRedirect("/admin/logout");//测试时用此跳转测试logout
            return R.success(Ad);
        } else {
            // 登录失败，返回错误信息和状态码
            response.sendRedirect("/admin/log.html");//登录界面这里可以改
            return R.error("用户名或密码错误");
        }
    }

    @GetMapping("/logout")
    public R logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(false);//获取session，若session不存在返回null
        if (session != null){//若会话无效
            session.invalidate(); // 销毁session
            response.sendRedirect("/admin/log.html");//返回登录界面
            return R.success();
        }
        else {
            return R.error("登出失败");
        }
    }





    //头像设置：String在本地数据库存储，使用时可以尝试response.sendRedirect()方法
    //前端的话，貌似是<img src="/users/1/avatar">（？没了解过。。）
    @GetMapping("/staff/{userid}/avatar")
    public R show_avatars(@PathVariable("userid") int userid){//用户列表展示界面显示用户头像，通过将用户头像储存在文件系统中实现
        List<admin> list = adminMapper.getAdminByUserId(userid);
        String avatar = adminMapper.getAvatarByuserId(userid);
        if(list.isEmpty()){
            return R.error("User not found");
        }

        if (avatar== null){
            return R.error("Avatar not found");
        }
        else{
            return R.success(avatar);
        }
    }

    /**
     * Show the amount of fine
     * @return
     */
    @GetMapping("/fine")
    public R showFineMoney() {
        return R.success(adminMapper.showFineMoney());
    }

    /**
     * modify the amount of fine
     * default is 2
     * @param fine param got from json data in RequestBody
     * @return
     */
    @PutMapping("/updateMoney")
    public R updateFineMoney(@RequestBody Fine fine) {
        int flag = adminMapper.updateFineMoney(fine);

        if(flag > 0) {
            return R.success(null);
        }
        return R.error("modify the amount of fine fail");
    }


}

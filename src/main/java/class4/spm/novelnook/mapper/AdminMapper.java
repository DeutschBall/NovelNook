package class4.spm.novelnook.mapper;


import class4.spm.novelnook.pojo.Staff;
/*import class4.spm.novelnook.pojo.User;*/
import class4.spm.novelnook.pojo.admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
/*    @Select("select * from user where userrole = 'patron'")
    List<User> getAllPatrons();*/

    @Select("select * from staff where username like '%${username}%'")
    List<Staff> getStaffByUserName(String username);//用来查找

    @Select("select * from staff ")//用来显示界面
    List<Staff> ShowStaff();

    @Delete("delete from staff where username = #{username}")
    void deleteStaffByUserName(String username);//用来删除

    @Select("select password from staff where username = #{username}")
    String getPasswordByUsername(String username);//用姓名查找密码


    //admin方法：
    @Select("select * from admin where userid like '%${userid}%'")
    List<admin> getAdminByUserId(int userid);//用来查找

    @Select("select password from admin where userid = #{userid}")
    String getPasswordByUserid(int userid);//用id查找密码（admin表用)

    @Select("select avatar from admin where userid = #{userid}")
    String getAvatarByuserId(int  userid);//用id查找头像信息

    /**
     * update function at Dao level
     * update staff table by userid
     * @param staff object staff got from service
     * @return
     *       if return 0, update fail
     *       if return > 0, update success
     */
    @Update("update staff set password = #{password}, " +
            "firstname = #{firstname}, lastname = #{lastname}, " +
            "telephone = #{telephone}, email = #{email}, avatar = #{avatar} " +
            "where userid = #{userid}")
    int updateByUserId(Staff staff);

    /**
     * add new staff function at Dao level
     * add new staff into staff table
     * @param staff object staff got from service
     * @return
     *      if return 0, insert fail
     *      if return > 0, insert success
     */
    @Insert("insert into staff(password, firstname, lastname, email, telephone, avatar) " +
            "values( #{password}, #{firstname}, #{lastname}, " +
            "#{email}, #{telephone}, #{avatar})")
    int addNewStaff(Staff staff);

}

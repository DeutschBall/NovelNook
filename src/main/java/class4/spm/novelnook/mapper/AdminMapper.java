package class4.spm.novelnook.mapper;


import class4.spm.novelnook.pojo.Staff;
/*import class4.spm.novelnook.pojo.User;*/
import class4.spm.novelnook.pojo.admin;
import class4.spm.novelnook.pojo.Fine;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
/*    @Select("select * from user where userrole = 'patron'")
    List<User> getAllPatrons();*/

    @Select("select * from staff where userid like '%${userid}%'")
    List<Staff> getStaffByUserName(int userid);//用来查找

    @Select("select * from staff ")//用来显示界面
    List<Staff> ShowStaff();

    @Delete("delete from staff where userid = #{userid}")
    void deleteStaffByUserName(int userid);//用来删除

    @Select("select password from staff where userid = #{userid}")
    String getPasswordByUsername(int userid);//用姓名查找密码


    //admin方法：
    @Select("select * from admin where userid like '%${userid}%'")
    List<admin> getAdminByUserId(int userid);//用来查找

    @Select("select password from admin where userid = #{userid}")
    String getPasswordByUserid(int userid);//用id查找密码（admin表用)

    @Select("select avatar from admin where userid = #{userid}")
    String getAvatarByuserId(int  userid);//用id查找头像信息

    @Select("select * from admin where userid like '%${userid}%'")
    admin getAdminByUserId_a(int userid);//还是查找，不过返回admin类型

    /**
     * update staff's information function at Dao level
     * update staff table by username
     * @param staff object staff got from service
     * @return
     *       if return 0, update fail
     *       if return > 0, update success
     */
    @Update("update staff set password = #{password}, " +
            "firstname = #{firstname}, lastname = #{lastname}, " +
            "telephone = #{telephone}, email = #{email}, avatar = #{avatar} " +
            "where userid = #{userid}")
    int updateByUserName(Staff staff);

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
    
    /**
     * show the amount of fine
     * only 1 line in the fine table
     * @return
     */
    @Select("select money from fine where id = 1")
    Fine showFineMoney();

    /**
     * modify the amount of fine
     * the default money is 2
     * only 1 line in the fine table
     * @param fine object fine got from service
     * @return
     */
    @Update("update fine set money = #{money} where id = 1")
    int updateFineMoney(Fine fine);

}

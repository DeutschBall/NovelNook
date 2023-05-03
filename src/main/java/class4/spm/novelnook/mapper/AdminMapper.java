package class4.spm.novelnook.mapper;


import class4.spm.novelnook.pojo.Staff;
import class4.spm.novelnook.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    @Select("select * from user where userrole = 'patron'")
    List<User> getAllPatrons();

    @Select("select * from staff where username like '%${username}%'")
    List<Staff> getStaffByUserName(String username);//用来查找

    @Select("select * from staff ")//用来显示界面
    List<Staff> ShowStaff();

    @Delete("delete from staff where username = #{username}")
    void deleteStaffByUserName(String username);//用来删除

    @Select("select password from staff where username = #{username}")
    String getPasswordByUsername(String username);//用姓名查找密码

    /**
     * update function at Dao level
     * update staff table by username
     * @param staff object staff got from service
     * @return
     *       if return 0, update fail
     *       if return > 0, update success
     */
    @Update("update staff set password = #{password}, " +
            "firstname = #{firstname}, lastname = #{lastname}, " +
            "telephone = #{telephone}, email = #{email}, avatar = #{avatar}" +
            "where username = #{username}")
    int updateByUserName(Staff staff);

    /**
     * add new staff function at Dao level
     * add new staff into staff table
     * @param staff object staff got from service
     * @return
     *      if return 0, insert fail
     *      if return > 0, insert success
     */
    @Insert("insert into staff " +
            "values(#{username}, #{password}, #{firstname}, #{lastname}, " +
            "#{email}, #{telephone})")
    int addNewStaff(Staff staff);

    /**
     * update user's avatar at Dao level
     * update user's avatar by username
     * @param staff
     * @return
     */
    @Update("update staff set avatar = #{avatar} where username = #{username}")
    int updateAvatarByUserName(Staff staff);

}

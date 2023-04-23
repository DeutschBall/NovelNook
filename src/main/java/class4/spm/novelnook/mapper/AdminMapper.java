package class4.spm.novelnook.mapper;


import class4.spm.novelnook.pojo.Staff;
import class4.spm.novelnook.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

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

}

package class4.spm.novelnook.mapper;

import class4.spm.novelnook.pojo.Superuser;
import org.apache.ibatis.annotations.*;
import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Patron;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.stereotype.Repository;
import class4.spm.novelnook.pojo.admin;

@Mapper
public interface SuperuserMapper {

    // 根据用户名查找超级管理员
    @Select("SELECT * FROM admin WHERE username = #{username}")
    void findByUsername(String username);

    //show all
    @Select("SELECT * FROM admin")
    public List<admin> getAdminList();

    // 添加超级管理员
    @Insert("INSERT INTO superuser(username, password) VALUES(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Superuser superuser);

    // 添加普通管理员
    @Insert("INSERT INTO admin(firstname, lastname, password) VALUES(#{firstname}, #{lastname}, #{password})")
    //@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addAdmin(@Param("firstname") String firstname,@Param("lastname") String lastname, @Param("password") String password);

    // 删除普通管理员
    @Delete("DELETE FROM admin WHERE userid = #{id}")
    void deleteAdmin(Long id);

    @Select("SELECT * FROM admin WHERE userid = #{userid}")
    public admin findByUserid(int userid);
}

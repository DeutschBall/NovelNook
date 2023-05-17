package class4.spm.novelnook.service;

import class4.spm.novelnook.mapper.SuperuserMapper;
import class4.spm.novelnook.pojo.Superuser;
import org.springframework.beans.factory.annotation.Autowired;
import class4.spm.novelnook.pojo.admin;

import java.util.List;

public interface SuperuserService {


    public List <admin> getAdminList();

    public admin findByUserid(int userid);



    public void save(Superuser superuser) ;



    public void addAdmin(String firstname, String lastname, String password) ;



    public void deleteAdmin(Long id) ;



    public void findByUsername(String username) ;

}

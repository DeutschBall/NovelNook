package class4.spm.novelnook.service;

import class4.spm.novelnook.mapper.SuperuserMapper;
import class4.spm.novelnook.pojo.Superuser;
import org.springframework.beans.factory.annotation.Autowired;

public interface SuperuserService {



    public void save(Superuser superuser) ;



    public void addAdmin(String username, String password) ;



    public void deleteAdmin(Long id) ;



    public void findByUsername(String username) ;

}

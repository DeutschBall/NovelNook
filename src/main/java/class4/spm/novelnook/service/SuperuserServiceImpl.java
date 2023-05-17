package class4.spm.novelnook.service;

import class4.spm.novelnook.mapper.SuperuserMapper;
import class4.spm.novelnook.pojo.Superuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import class4.spm.novelnook.pojo.admin;

import java.util.List;

@Service
public class SuperuserServiceImpl implements SuperuserService {

    @Autowired
    private SuperuserMapper superuserMapper;

    public List<admin> getAdminList(){return superuserMapper.getAdminList();}

    //@Override
    public void save(Superuser superuser) {
        superuserMapper.save(superuser);
    }

    //@Override
    public void addAdmin(String firstname, String lastname,String password) {
        superuserMapper.addAdmin(firstname, lastname, password);
    }

    //@Override
    public void deleteAdmin(Long id) {
        superuserMapper.deleteAdmin(id);
    }

    //@Override
    public void findByUsername(String username) {
        superuserMapper.findByUsername(username);
    }

    public admin findByUserid(int userid){return superuserMapper.findByUserid(userid);}
}

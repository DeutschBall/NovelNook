package class4.spm.novelnook.service;

import class4.spm.novelnook.mapper.SuperuserMapper;
import class4.spm.novelnook.pojo.Superuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperuserServiceImpl implements SuperuserService {

    @Autowired
    private SuperuserMapper superuserMapper;

    //@Override
    public void save(Superuser superuser) {
        superuserMapper.save(superuser);
    }

    //@Override
    public void addAdmin(String username, String password) {
        superuserMapper.addAdmin(username, password);
    }

    //@Override
    public void deleteAdmin(Long id) {
        superuserMapper.deleteAdmin(id);
    }

    //@Override
    public void findByUsername(String username) {
        superuserMapper.findByUsername(username);
    }
}

package co.edu.usco.TM.service;

import co.edu.usco.TM.persistence.entity.User;
import co.edu.usco.TM.persistence.repository.OwnerRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.usco.TM.persistence.repository.RolRepo;
import co.edu.usco.TM.persistence.repository.UserRepo;
import co.edu.usco.TM.service.interfaces.IntOwnerService;

@Service
public class OwnerService implements IntOwnerService {

    @Autowired
    private UserRepo usrRepo;

    @Autowired
    private RolRepo rolRepo;

    @Autowired
    private OwnerRepo ownRepo;
    
    @Override
    @Transactional(readOnly = true)
    public List<User> listOwners() {
        return (List<User>) usrRepo.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setRol(rolRepo.findByRolName("ROLE_USER"));
        usrRepo.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        usrRepo.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(User user) {
        return usrRepo.findById(user.getId()).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<User> getUserByEmail(String email) {
        return usrRepo.findByEmail(email);
    }

}

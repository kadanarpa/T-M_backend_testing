package co.edu.usco.TM.service;

import co.edu.usco.TM.dao.RolDAO;
import co.edu.usco.TM.dao.UserDAO;
import co.edu.usco.TM.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IntUserService {

    @Autowired
    private UserDAO usrDAO;

    @Autowired
    private RolDAO rolDAO;

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return (List<User>) usrDAO.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setRol(rolDAO.findByRolName("ROLE_USER"));
        usrDAO.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        usrDAO.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(User user) {
        return usrDAO.findById(user.getId()).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<User> getUserByEmail(String email) {
        return usrDAO.findByEmail(email);
    }

}

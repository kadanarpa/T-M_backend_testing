
package co.edu.usco.TM.service;

import co.edu.usco.TM.entity.User;
import java.util.List;

public interface IntUserService {
    
    public List<User> listUsers();
    
    public void save(User user);
    
    public void delete(User user);
    
    public User getUser(User user);
    
}

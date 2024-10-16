
package co.edu.usco.TM.service.interfaces;

import co.edu.usco.TM.persistence.entity.User;
import java.util.List;

public interface IntOwnerService {
    
    public List<User> listOwners();
    
    public void save(User user);
    
    public void delete(User user);
    
    public User getUser(User user);
    
}

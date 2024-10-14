
package co.edu.usco.TM.dao;

import co.edu.usco.TM.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Long>{
    List<User> findByEmail(String email);
}

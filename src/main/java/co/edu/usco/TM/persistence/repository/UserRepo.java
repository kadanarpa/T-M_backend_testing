
package co.edu.usco.TM.persistence.repository;

import co.edu.usco.TM.persistence.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    List<User> findByEmail(String email);
}


package co.edu.usco.TM.dao;

import co.edu.usco.TM.entity.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolDAO extends CrudRepository<Rol, Long> {
    Rol findByRolName(String rolName);
}

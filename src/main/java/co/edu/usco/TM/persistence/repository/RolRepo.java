
package co.edu.usco.TM.persistence.repository;

import co.edu.usco.TM.persistence.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepo extends JpaRepository<Rol, Long> {
    Rol findByRolName(String rolName);
}

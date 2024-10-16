
package co.edu.usco.TM.persistence.repository;

import co.edu.usco.TM.persistence.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepo extends JpaRepository<Owner, Long>{
    
}

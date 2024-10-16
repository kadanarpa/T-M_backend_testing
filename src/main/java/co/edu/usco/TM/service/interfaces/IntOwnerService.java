
package co.edu.usco.TM.service.interfaces;

import co.edu.usco.TM.persistence.entity.Owner;
import java.util.List;

public interface IntOwnerService {
    
    public List<Owner> listOwners();
    
    public void save(Owner owner);
    
    public void delete(Owner owner);
    
    public Owner getOwner(Owner owner);
    
}

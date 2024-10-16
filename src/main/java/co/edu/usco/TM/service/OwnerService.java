package co.edu.usco.TM.service;

import co.edu.usco.TM.persistence.entity.Owner;
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
    public List<Owner> listOwners() {
        return (List<Owner>) ownRepo.findAll();
    }

    @Override
    @Transactional
    public void save(Owner owner) {
        
        owner.getUser().setRol(rolRepo.findByRolName("ROLE_USER"));
        ownRepo.save(owner);
    }

    @Override
    @Transactional
    public void delete(Owner owner) {
        ownRepo.delete(owner);
    }

    @Override
    @Transactional(readOnly = true)
    public Owner getOwner(Owner owner) {
        return ownRepo.findById(owner.getId()).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Owner> getOwnerByEmail(String email) {
        return null;
    }

}

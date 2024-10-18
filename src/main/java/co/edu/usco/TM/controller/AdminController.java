package co.edu.usco.TM.controller;

import co.edu.usco.TM.persistence.entity.Owner;
import co.edu.usco.TM.persistence.entity.User;
import co.edu.usco.TM.service.OwnerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/owner")
@Controller
public class AdminController {

    @Autowired
    private OwnerService ownService;

    @GetMapping
    public String showIndex(Model model) {

        List<Owner> owners = ownService.listOwners();

        model.addAttribute("owners", owners);
        return "ownersList";
    }

    @GetMapping("/append")
    public String appendOwner(Model model) {

        Owner newOwner = new Owner();
        newOwner.setUser(new User());

        model.addAttribute("newOwner", newOwner);
        model.addAttribute("valor", "Insert");

        return "modifyUserForm";
    }

    @PostMapping("/insert")
    public String insertOwner(@Valid @ModelAttribute("newOwner") Owner newOwner, Errors error, Model model) {

        if (error.hasErrors()) {
            if (newOwner.getId() == null) {
                model.addAttribute("valor", "Insert");
            } else {
                model.addAttribute("valor", "Modify");
            }
            return "modifyUserForm";
        } else {
            ownService.save(newOwner);

            return "redirect:/admin/owner";
        }
    }

    @GetMapping("/modify/{id}")
    public String modifyOwner(Owner owner, Model model) {

        Owner modOwner = ownService.getOwner(owner);

        model.addAttribute("newOwner", modOwner);
        model.addAttribute("valor", "Modify");
        return "modifyUserForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwner(Owner owner) {

        Owner delOwner = ownService.getOwner(owner);
        ownService.delete(delOwner);

        return "redirect:/admin/owner";
    }
}

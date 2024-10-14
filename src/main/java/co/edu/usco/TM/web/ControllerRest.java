package co.edu.usco.TM.web;

import co.edu.usco.TM.entity.User;
import co.edu.usco.TM.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerRest {

    @Autowired
    private UserService usrService;

    @GetMapping("/")
    public String showIndex(Model model) {

        List<User> users = usrService.listUsers();

        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/append")
    public String appendUser(Model model) {
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        model.addAttribute("valor", "Insert");
        return "modifyUserForm";
    }

    @PostMapping("/insert")
    public String insertUser(@Valid @ModelAttribute("newUser") User newUser, Errors error, Model model) {

        if (error.hasErrors()) {
            if (newUser.getId() == null) {
                model.addAttribute("valor", "Insert");
            } else {
                model.addAttribute("valor", "Modify");
            }
            return "modifyUserForm";
        } else {
            usrService.save(newUser);
            return "redirect:/";
        }
    }

    @GetMapping("/modify/{id}")
    public String modifyUser(User user, Model model) {

        User modUser = usrService.getUser(user);

        model.addAttribute("newUser", modUser);
        model.addAttribute("valor", "Modify");
        return "modifyUserForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(User delUser) {

        usrService.delete(delUser);

        return "redirect:/";
    }
}

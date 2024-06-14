package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.models.User;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserDao userDao = new UserDao();

    @GetMapping("")
    public String allUsers(Model model) {

        model.addAttribute("usera", userDao.getUsers());

        return "/user";
    }


    @GetMapping("/new")
    public String newUser(Model model) {

        model.addAttribute("user", new User());
        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        System.out.println("попал");
        userDao.saveUserButton(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String editUser( @PathVariable int id, Model model) {

        model.addAttribute("user", userDao.getUserById(id));
        return "/edit";


    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable int id) {

        userDao.updateUser(id,user);
        return "redirect:/user";
    }


    @PostMapping("/{id}")
    public String delete(@PathVariable int id) {

        userDao.deleteUser(id);
return "redirect:/user";
    }


}

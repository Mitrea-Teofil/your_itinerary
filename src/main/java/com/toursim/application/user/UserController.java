package com.toursim.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/show")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam ("id") int id){
        userService.deleteUser(id);
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

//    @GetMapping
//    public String userIsLogged(){
//        return SecurityContextHolder.getContext().getAuthentication() != null &&
//                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ? "isLoggedIn" : null;
//    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
       try{
           userService.save(registrationDto);
       }catch(Exception e ){
           return "redirect:/register?failed";
        }
        return "redirect:/register?success";
    }

}

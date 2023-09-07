package com.example.demo.user;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String DisplayLogin(Model model){
        return "login";
    }
    private final UserService userService;
    @PostMapping("/SelectPorts")
    public String userProfile(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        try {
            String pass = userService.getUserByUsername(username).getPassword();
            if (pass.equals(password)) {
                return "redirect:/login?username="+username+"&password="+password;
            } else {
                String errorMessage = "Invalid username or password. Please try again.";
                model.addAttribute("error", errorMessage);
                return "login"; // Return to the login page with the error message
            }
        } catch (NullPointerException e) {
            System.err.println("An error occurred: " + e.getMessage());
            String errorMessage = "Invalid username or password. Please try again.";
            model.addAttribute("error", errorMessage);
            return "login"; // Return to the login page with the error message
        }
    }

}



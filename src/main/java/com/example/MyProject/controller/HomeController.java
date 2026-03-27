package com.example.MyProject.controller;

import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MyProject.model.Project;
import com.example.MyProject.repository.ProjectRepository;
import com.example.MyProject.service.ProjectService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    ProjectService service;
    @Autowired
ProjectRepository repository;

    @RequestMapping("/main")
    public String MainPage() {
        return "home";
    }

    @GetMapping ("/register")
    public String AddDetails(Model model) {
        model.addAttribute("student", new Project());
        return "registration";
    }
    @PostMapping("/register")
    public String SaveDetails(@ModelAttribute Project details){
        service.AddStudent(details);
        return "login";


    }
    
    @GetMapping("/course")
    public String showForm(){
    return "courses";
}
 @RequestMapping("/login")
    public String Loginlogin(){
        return "login";
    }
    
    @RequestMapping("/about")
    public String Aboutus(){
        return "about";
    }

    
    //  @RequestMapping("/sendOtp")
    // public String Send(){
    //     return "sendOtp";
    // }
     @RequestMapping("/Submit")
    public String Sub(){
        return "usernamePass";
    }
   

   

    @RequestMapping("/contact")
    public String Contactus(){
        return "contact";
    }




    @GetMapping("/sendOtp")
    public String Login(){
        return"sendOtp";
    }

  @PostMapping("/sendOtp")
public String SendOtp(Model model, @RequestParam("email") String email) {
    Project existingStudent = this.repository.findByEmail(email);

    if (existingStudent != null) {
        String newOtp = this.generateRandomOtp();
        existingStudent.setOtp(newOtp);
        this.repository.save(existingStudent);

        this.service.sendEmail(email, newOtp);

        model.addAttribute("email", email); 
        return "verifyOtp";
    } else {
        model.addAttribute("error", "Email not found");
        return "sendOtp";
    }
}
 @PostMapping("/verifyOtp")
public String Verify(@RequestParam("email") String email,
                     @RequestParam("otp") String enteredOtp,
                     Model model){

    Project user = repository.findByEmail(email);

    if(user != null && user.getOtp() != null && user.getOtp().equals(enteredOtp)){

        user.setVerified(true);
        repository.save(user);

        
        model.addAttribute("email", email);

        return "usernamePass";
    }
    else{
        model.addAttribute("error","Invalid OTP");
        model.addAttribute("email", email);
        return "verifyOtp";
    }
}
    private String generateRandomOtp(){
        String otp=String.valueOf((new Random()).nextInt(9000)+1000);
        return otp;
    }

// @PostMapping("/Submit")
// public String login(@RequestParam("username") String username,
//                     @RequestParam("password") String password,
//                     @RequestParam("email") String email,
//                     Model model) {

    
//     Project user = repository.findByEmail(email);

//     if (user != null) {

        
//         user.setUsername(username);
//         user.setPassword(password);

//         repository.save(user); 

//         model.addAttribute("user", user);
//         return "dashboard";

//     } else {
//         model.addAttribute("error", "Email not found");
//         return "usernamePass";
//     }
// }


       

@PostMapping("/Submit")
public String login(@RequestParam("username") String username,
                    @RequestParam("password") String password,
                    @RequestParam("email") String email,
                    Model model,
                    HttpSession session) {

    Project user = repository.findByEmail(email);

    if (user != null) {

        user.setUsername(username);
        user.setPassword(password);

        repository.save(user);

        // ✅ STORE USER IN SESSION
        session.setAttribute("loggedUser", user);

        return "redirect:/dashboard";

    } else {
        model.addAttribute("error", "Email not found");
        return "usernamePass";
    }
}





// @GetMapping("/dashboard")
// public String dashboard(HttpSession session, Model model) {

//     Project user = (Project) session.getAttribute("loggedUser");

//     if (user == null) {
//         return "redirect:/login";
//     }

//     model.addAttribute("user", user);

//     return "dashboard";
// }

@GetMapping("/dashboard")
public String dashboard(HttpSession session, Model model) {

    Project user = (Project) session.getAttribute("loggedUser");

    if (user == null) {
        return "redirect:/login"; // 🚫 block access after logout
    }

    model.addAttribute("user", user);

    return "dashboard";
}


@PostMapping("/loginUser")
public String loginUser(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {

    // System.out.println("Username: " + username);
    // System.out.println("Password: " + password);

    Project user = repository.findByUsernameAndPassword(username.trim(), password.trim());

    if (user != null) {

        System.out.println("User found: " + user.getUsername());

        session.setAttribute("loggedUser", user); 

        return "redirect:/dashboard";

    } else {
        System.out.println("User NOT found");

        model.addAttribute("error", "Invalid Username or Password");
        return "login";
    }
}





    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Home Page");
        return "home"; 
    }

    // @GetMapping("/profile")
    // public String profile(Model model) {
    //     model.addAttribute("message", "User Profile Page");
    //     return "profile"; 
    // }


@GetMapping("/profile")
public String profile(HttpSession session, Model model) {

    Project user = (Project) session.getAttribute("loggedUser");

    if (user == null) {
        return "redirect:/profile";
    }

    model.addAttribute("user", user);

    return "profile";
}


    @GetMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("message", "Settings Page");
        return "settings"; 
    }

    // @GetMapping("/logout")
    // public String logout(HttpSession session) {
    //     session.invalidate(); 
    //     return "redirect:/";  
    // }






    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {

        // 1. Invalidate session
        if (session != null) {
            session.invalidate();
        }

        // 2. Delete JSESSIONID cookie
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0); // important → deletes cookie
        response.addCookie(cookie);

        return "redirect:/login"; // redirect to login page
    }



    @GetMapping("/editProfile")
public String editProfile(HttpSession session, Model model) {

    Project user = (Project) session.getAttribute("loggedUser");

    if (user == null) {
        return "redirect:/login";
    }

    model.addAttribute("user", user);

    return "editProfile";
}
@PostMapping("/updateProfile")
public String updateProfile(@RequestParam String fullName,
                            @RequestParam String email,
                            @RequestParam String mobile,
                            @RequestParam String education,
                            @RequestParam String city,
                            // @RequestParam("imageFile") MultipartFile file,
                            HttpSession session) throws IOException {

    Project user = (Project) session.getAttribute("loggedUser");

    user.setFullName(fullName);
    user.setEmail(email);
    user.setMobile(mobile);
    user.setEducation(education);
    user.setCity(city);

    // if (!file.isEmpty()) {
    //     user.setImage(file.getBytes());
    // }

    repository.save(user);

    session.setAttribute("loggedUser", user); // update session

    return "redirect:/dashboard";
}
}






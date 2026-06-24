package in.starx.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import in.starx.main.model.User;
import in.starx.main.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/register")
	public String registerPage()
	{
		return "register";
	}
	
	@PostMapping("/registerUser")
	public String registerUser(User user)
	{
		userService.registerUser(user);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
	
	
	@PostMapping("/loginUser")
	public String loginUser( @RequestParam String email, @RequestParam String password, HttpSession session, Model model )
	{
		User user = userService.loginUser(email, password);
		
		if(user != null)
		{
			session.setAttribute("loggedUser", user);
			return "redirect:/";
		}
		else {
			model.addAttribute("error", "Invalid email or password");
			return "login";
		}
		
	}
	

	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}
	
	
}

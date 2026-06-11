package in.starx.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.starx.main.model.Admin;
import in.starx.main.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/login")
	public String loginPage() {
		return "admin/login";
	}
	
	@PostMapping("/login")
	public String login(String email, String password,
	                    HttpSession session, Model model) {

	    System.out.println("Email: " + email);
	    System.out.println("Password: " + password);

	    Admin admin = adminService.login(email, password);

	    if(admin != null) {
	        session.setAttribute("admin", admin);
	        return "redirect:/admin/dashboard";
	    }

	    model.addAttribute("error", "Invalid Credentials");
	    return "admin/login";
	}
	
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "admin/dashboard";
	}
	
}

package in.starx.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.starx.main.model.User;
import in.starx.main.service.FoodService;
import jakarta.servlet.http.HttpSession;

@Controller
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@GetMapping("/")
	public String home(Model model, HttpSession session)
	{
		
		User loggedUser = (User) session.getAttribute("loggedUser");
		
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("foods", foodService.getAllFoodItems());
		
		return "index";
	}
	
	@GetMapping("/check")
	@ResponseBody
	public String check()
	{
	    return "Foods Size : " + foodService.getAllFoodItems().size();
	}
	
}

package in.starx.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.starx.main.model.Admin;
import in.starx.main.model.Food;
import in.starx.main.service.AdminService;
import in.starx.main.service.FoodService;
import in.starx.main.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private OrderService orderService;
	
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
	
	
	
	//added below 3 methods
	@GetMapping("/add-food")
	public String addFoodPage()
	{
	    return "admin/add-food";
	}
	
	@GetMapping("/manage-food")
	public String manageFood(Model model)
	{
	    model.addAttribute(
	            "foods",
	            foodService.getAllFoodItems()
	    );

	    return "admin/manage-food";
	}

	@GetMapping("/manage-orders")
	public String manageOrders(Model model)
	{
	    model.addAttribute(
	            "orders",
	            orderService.getAllOrders()
	    );

	    return "admin/manage-orders";
	}
	
	
	
	//added below CRUD methods for food
	@PostMapping("/add-food")
	public String addFood(Food food)
	{
		foodService.addFood(food);

	    return "redirect:/admin/manage-food";
	}
	
	@GetMapping("/edit-food/{id}")
	public String editFoodPage(
	        @PathVariable int id,
	        Model model)
	{
	    Food food =
	            foodService.getFoodById(id);

	    model.addAttribute(
	            "food",
	            food
	    );

	    return "admin/edit-food";
	}
	
	@PostMapping("/update-food")
	public String updateFood(Food food)
	{
	    foodService.updateFood(food);

	    return "redirect:/admin/manage-food";
	}
	
	@GetMapping("/delete-food/{id}")
	public String deleteFood(
	        @PathVariable int id)
	{
	    System.out.println(
	            "Deleting Food ID : " + id
	    );

	    foodService.deleteFood(id);

	    return "redirect:/admin/manage-food";
	}
	
	
	// added for orders
	@PostMapping("/update-order-status")
	public String updateOrderStatus(
	        @RequestParam int orderId,
	        @RequestParam String status)
	{
	    orderService.updateStatus(
	            orderId,
	            status
	    );

	    return "redirect:/admin/manage-orders";
	}

	
	@GetMapping("/order/{id}")
	public String viewOrder(
	        @PathVariable int id,
	        Model model)
	{
	    model.addAttribute(
	            "order",
	            orderService.getOrderById(id)
	    );

	    return "admin/order-details";
	}
}

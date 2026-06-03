package in.starx.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import in.starx.main.model.Cart;
import in.starx.main.model.User;
import in.starx.main.service.CartService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
	@GetMapping("/add-to-cart/{foodId}")
	public String addToCart(@PathVariable int foodId, HttpSession session)
	{
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null)
		{
			return "redirect:/login";
		}
		
		cartService.addToCart(loggedUser.getId(), foodId);

		return "redirect:/cart";
	}
	
	@GetMapping("/cart")
	public String cartPage(HttpSession session, Model model)
	{
		User loggedUser = (User) session.getAttribute("loggedUser");
		
		if(loggedUser == null)
		{
			return "redirect:/login";
		}
		
		List<Cart> cartItems = cartService.getCartItems(loggedUser.getId());
		double totalAmount = cartService.getTotalAmount(cartItems);
		
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("totalAmount" , totalAmount);
		model.addAttribute("loggedUser", loggedUser);
		
		return "cart";
	}
	 
	@GetMapping("/remove-cart/{cartId}")
	public String removeCartItem(@PathVariable int cartId)
	{
		cartService.removeCartItem(cartId);
		return "redirect:/cart";
	}
	
}

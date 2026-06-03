package in.starx.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.starx.main.model.Cart;
import in.starx.main.repository.CartRepository;

@Service
public class CartService {

	@Autowired	
	private CartRepository cartRepository;
	
	public int addToCart(int userId, int foodId)
	{
		return cartRepository.addToCart(userId, foodId);
	}

	public List<Cart> getCartItems(int userId)
	{
		return cartRepository.getCartItems(userId);
	}

	public int removeCartItem(int cartId)
	{
		return cartRepository.removeCartItem(cartId);
	}
	
	public int clearCartItems(int userId)
	{
		return cartRepository.clearCartItems(userId);
	}
	
	public double getTotalAmount(List<Cart> cartItems)
	{
		double total = 0;
		
		for(Cart cartItem : cartItems)
		{
			total += cartItem.getPrice();
		}
		return total;
	}
}

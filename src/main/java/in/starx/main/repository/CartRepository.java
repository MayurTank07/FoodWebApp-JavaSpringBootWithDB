package in.starx.main.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.starx.main.model.Cart;

@Repository
public class CartRepository {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public int addToCart(int userId, int foodId)
	{
		String checkCart = "SELECT COUNT(*) FROM CART WHERE USER_ID = ? AND FOOD_ID = ?";
		Integer count = jdbcTemplate.queryForObject(checkCart, Integer.class, userId, foodId);  //2
		
		if(count != null && count > 0)
		{
			String updateCart = "update CART set quantity = quantity + 1 where user_id = ? and food_id = ?";
			return jdbcTemplate.update(updateCart, userId, foodId);   // 3
		}
		else {
			String insertCart = "insert into CART (user_id, food_id, quantity) values (?, ?, ?)";
			return jdbcTemplate.update(insertCart, userId, foodId, 1);
		}
	}
	
	
	public List<Cart> getCartItems(int userId)
	{
		 String getItems = """
				select c.id, c.user_id, c.food_id, c.quantity,
				f.food_name, f.price, f.image
				from cart c
				join foods f on c.food_id = f.id
				where c.user_id = ?
				""";
		
		return jdbcTemplate.query(getItems, (ResultSet rs, int rowNum) -> {
			
			Cart cart = new Cart();
			cart.setId(rs.getInt("id"));
			cart.setUserId(rs.getInt("user_id"));
			cart.setFoodId(rs.getInt("food_id"));
			cart.setQuantity(rs.getInt("quantity"));
			cart.setFoodName(rs.getString("food_name"));
			cart.setPrice(rs.getDouble("price"));
			cart.setImage(rs.getString("image"));
			cart.setTotalPrice(rs.getDouble("price") * rs.getInt("quantity"));
			
			return cart;
		}, userId);
		
	}
	
	public int removeCartItem(int cartId)
	{
		String removeCart = "Delete from cart where id = ?";
		return jdbcTemplate.update(removeCart, cartId);
	}
	
	public int clearCartItems(int userId)
	{
		String clearCart = "Delete from cart where user_id = ?";
		return jdbcTemplate.update(clearCart, userId);
	}
}

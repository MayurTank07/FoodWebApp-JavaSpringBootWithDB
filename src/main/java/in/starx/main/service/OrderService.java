package in.starx.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.starx.main.model.Order;
import in.starx.main.repository.OrderRespository;

@Service
public class OrderService {

	@Autowired
	private OrderRespository orderRespository;
	
	
	public int placeOrder(Order order, String paymentMethod)
	{
		return orderRespository.placeOrder(order, paymentMethod);
	}
	
	public List<Order> getUserOrders(int userId)
	{
		return orderRespository.getUserOrders(userId);
	}
	
	// added
	 public List<Order> getAllOrders()
	    {
	        return orderRespository.getAllOrders();
	    }
	 
	 //added
	 public int updateStatus(
		        int orderId,
		        String status)
		{
		    return orderRespository.updateStatus(
		            orderId,
		            status
		    );
	}
	 
	 //added
	 public Order getOrderById(int id)
	 {
	     return orderRespository.getOrderById(id);
	 }
}

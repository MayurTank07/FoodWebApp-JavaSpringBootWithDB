package in.starx.main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import in.starx.main.model.Order;

@Repository
public class OrderRespository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int placeOrder(Order order, String paymentMethod)
	{
		String placeOrderSQL = """
				INSERT INTO orders
				(
				 user_id,
				 total_amount,
				 status,
				 customer_name,
				 phone,
				 address,
				 order_date
				)
				VALUES
				(
				 ?, ?, ?, ?, ?, ?, ?
				)
				""";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(placeOrderSQL, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getUserId());
			ps.setDouble(2, order.getTotalAmount());
			ps.setString(3, order.getStatus());
			ps.setString(4, order.getCustomerName());
			ps.setString(5, order.getPhone());
			ps.setString(6, order.getAddress());
			ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
			return ps;
 		}, keyHolder );
		
		
		int orderId = keyHolder.getKey().intValue();
		
		String paymentSql = """
		        INSERT INTO payments(order_id, payment_method, payment_status)
		        VALUES(?,?,?)
		        """;
		jdbcTemplate.update(paymentSql, orderId, paymentMethod, "Pending");
		
		return orderId;
		
	}
	
	public List<Order> getUserOrders(int userId)
	{
	    String sql = "SELECT * FROM orders WHERE user_id=? ORDER BY id DESC";

	    return jdbcTemplate.query(sql, (rs, rowNum) -> {

	        Order order = new Order();

	        order.setId(rs.getInt("id"));
	        order.setUserId(rs.getInt("user_id"));
	        order.setTotalAmount(rs.getDouble("total_amount"));
	        order.setStatus(rs.getString("status"));
	        order.setCustomerName(rs.getString("customer_name"));
	        order.setPhone(rs.getString("phone"));
	        order.setAddress(rs.getString("address"));

	        if(rs.getTimestamp("order_date") != null)
	        {
	            order.setOrderDate(
	                rs.getTimestamp("order_date").toLocalDateTime()
	            );
	        }

	        return order;

	    }, userId);
	}
	
	//added
	public List<Order> getAllOrders()
	{
	    String sql =
	    """
	    SELECT *
	    FROM orders
	    ORDER BY id DESC
	    """;

	    return jdbcTemplate.query(
	            sql,
	            (rs,rowNum)->{

	                Order order = new Order();

	                order.setId(rs.getInt("id"));
	                order.setUserId(rs.getInt("user_id"));
	                order.setTotalAmount(rs.getDouble("total_amount"));
	                order.setStatus(rs.getString("status"));
	                order.setCustomerName(rs.getString("customer_name"));
	                order.setPhone(rs.getString("phone"));
	                order.setAddress(rs.getString("address"));

	                if(rs.getTimestamp("order_date") != null)
	                {
	                    order.setOrderDate(
	                            rs.getTimestamp("order_date")
	                            .toLocalDateTime()
	                    );
	                }

	                return order;
	            });
	}
	
	//added
	public int updateStatus(
        int orderId,
        String status)
{
    String sql =
    """
    UPDATE orders
    SET status=?
    WHERE id=?
    """;

    return jdbcTemplate.update(
            sql,
            status,
            orderId
    );
}
	
	//added
	public Order getOrderById(int id)
	{
	    String sql =
	    "SELECT * FROM orders WHERE id=?";

	    return jdbcTemplate.queryForObject(
	            sql,
	            (rs,rowNum)->{

	                Order order = new Order();

	                order.setId(rs.getInt("id"));
	                order.setCustomerName(rs.getString("customer_name"));
	                order.setPhone(rs.getString("phone"));
	                order.setAddress(rs.getString("address"));
	                order.setStatus(rs.getString("status"));
	                order.setTotalAmount(rs.getDouble("total_amount"));

	                return order;

	            },
	            id
	    );
	}
}

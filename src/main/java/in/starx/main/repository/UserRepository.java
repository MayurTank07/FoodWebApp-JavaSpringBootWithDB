package in.starx.main.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.starx.main.model.User;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int registerUser(User user)
	{
		String insertSql = "INSERT INTO USERS ( name, email, password ) values ( ?, ?, ? )";
		
		return jdbcTemplate.update(insertSql, user.getName(), user.getEmail(), user.getPassword());
	}
	
	public User loginUser(String email, String password)
	{
		String loginSql = "select * from users where email = ? and password = ?";
	
		try 
		{
			return jdbcTemplate.queryForObject(loginSql, (ResultSet rs, int rowNum) -> {
				User user = new User();
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				
				return user;
				
			}, email, password);
		} 
		catch (Exception e) 
		{
			return null;
		}
		
	}
	
	public List<User> getAllUsers()
	{
		String getUserSQL = """
				SELECT * FROM USERS
				ORDER BY id DESC
				""";
		
		return jdbcTemplate.query(getUserSQL, (rs, rowNum)-> {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));
			
			if(rs.getTimestamp("created_at") != null)
			{
				user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
			}
			
			
			return user;
		});
	}
	
	public User getUserById(int id)
	{
		String getUserSQL = """
				SELECT * FROM USERS where id = ?
				""";
		
		return jdbcTemplate.queryForObject(getUserSQL, (rs, rowNum)-> {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));
			
			if(rs.getTimestamp("created_at") != null)
			{
				user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
			}
			
			
			return user;
		}, id);
	}
}

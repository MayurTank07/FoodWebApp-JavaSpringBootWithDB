package in.starx.main.repository;

import java.sql.ResultSet;

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
}

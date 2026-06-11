package in.starx.main.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.starx.main.model.Admin;

@Repository
public class AdminRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Admin login(String email, String password)
	{
		String loginSql = "SELECT * FROM admins WHERE email=? AND password=?;";
		
		try {
				
			return jdbcTemplate.queryForObject(loginSql, (rs, rowNum)-> {
						
						Admin admin = new Admin();
						
						admin.setId(rs.getInt("id"));
						admin.setEmail(rs.getString("email"));
						admin.setPassword(rs.getString("password"));
						
						return admin;
						
					}, email, password);		
		} 
		catch (Exception e) {
			 e.printStackTrace();
			return null;
		}
	}
	
}

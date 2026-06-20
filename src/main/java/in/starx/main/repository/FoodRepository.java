package in.starx.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.starx.main.model.Food;

@Repository
public class FoodRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Food> getAllFoods()
	{
	    String sql = "select * from foods";

	    List<Food> foods = jdbcTemplate.query(sql, (rs, rowNum) -> {

	        Food food = new Food();

	        food.setId(rs.getInt("id"));
	        food.setFoodName(rs.getString("food_name"));
	        food.setPrice(rs.getDouble("price"));
	        food.setImage(rs.getString("image"));
	        food.setCategory(rs.getString("category"));

	        System.out.println("Food Found : " + food.getFoodName());

	        return food;
	    });

	    System.out.println("Total Foods : " + foods.size());

	    return foods;
	}
	
	//added 
	public int addFood(Food food)
	{
	    String sql =
	    """
	    INSERT INTO foods
	    (
	        food_name,
	        price,
	        image,
	        category
	    )
	    VALUES
	    (
	        ?, ?, ?, ?
	    )
	    """;

	    return jdbcTemplate.update(
	            sql,
	            food.getFoodName(),
	            food.getPrice(),
	            food.getImage(),
	            food.getCategory()
	    );
	}
	
	//added
	public int updateFood(Food food)
	{
	    String sql =
	    """
	    UPDATE foods
	    SET
	        food_name=?,
	        price=?,
	        image=?,
	        category=?
	    WHERE id=?
	    """;
	
	    return jdbcTemplate.update(
	            sql,
	            food.getFoodName(),
	            food.getPrice(),
	            food.getImage(),
	            food.getCategory(),
	            food.getId()
	    );
	}
	
	//added
	public int deleteFood(int id)
	{
	    String sql =
	    """
	    DELETE FROM foods
	    WHERE id=?
	    """;

	    return jdbcTemplate.update(
	            sql,
	            id
	    );
	}
	
	//added
	public Food getFoodById(int id)
{
    String sql = "SELECT * FROM foods WHERE id=?";

    return jdbcTemplate.queryForObject(
            sql,
            (rs,rowNum)->{

                Food food = new Food();

                food.setId(rs.getInt("id"));
                food.setFoodName(rs.getString("food_name"));
                food.setPrice(rs.getDouble("price"));
                food.setImage(rs.getString("image"));
                food.setCategory(rs.getString("category"));

                return food;
            },
            id
    );
	}
}

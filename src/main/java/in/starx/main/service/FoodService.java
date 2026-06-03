package in.starx.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.starx.main.model.Food;
import in.starx.main.repository.FoodRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	public List<Food> getAllFoodItems()
	{
		return foodRepository.getAllFoods();
	}
}

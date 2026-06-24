package in.starx.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.starx.main.model.User;
import in.starx.main.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public int registerUser(User user)
	{
		return userRepository.registerUser(user);
	}
	
	public User loginUser(String email, String password)
	{
		return userRepository.loginUser(email, password);
	}
	
	public List<User> getAllUsers()
	{
		return userRepository.getAllUsers();
	}
	
	public User getUserById(int id)
	{
		return userRepository.getUserById(id);
	}
}

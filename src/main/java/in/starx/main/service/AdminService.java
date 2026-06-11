package in.starx.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.starx.main.model.Admin;
import in.starx.main.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	
	public Admin login(String email, String password)
	{
		return adminRepository.login(email, password);
	}
	
}

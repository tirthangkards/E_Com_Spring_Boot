package fun.bonkers.controller;

import java.security.Principal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import fun.bonkers.model.Category;
import fun.bonkers.model.Role;
import fun.bonkers.model.User;
import fun.bonkers.repository.RoleRepository;
import fun.bonkers.repository.UserRepository;
import fun.bonkers.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/shop/loginn")
	public String getLogin() {
		return "loginn";	
	}
	
	@GetMapping("/shop/register")
	public String getRegister(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@PostMapping("/shop/register")
	public String getRegister(@ModelAttribute("user")User user, HttpServletRequest req) throws ServletException {
		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		user.setRole("ADMIN");
		userService.addUser(user);
		return "redirect:/shop/home";
	}
	
	@GetMapping("/shop/user/profile")
	public String getUserProfile(Principal p, Model model) {
		String name=p.getName();
		User user=userRepository.findByEmail(name);
		model.addAttribute("user", user);		
		return "profile";
	}
}

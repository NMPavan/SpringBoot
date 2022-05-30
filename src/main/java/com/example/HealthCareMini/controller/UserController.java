package com.example.HealthCareMini.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HealthCareMini.Entity.User;
import com.example.HealthCareMini.Utility.MyMailUtil;
import com.example.HealthCareMini.Utility.UserUtil;
import com.example.HealthCareMini.services.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService service;

	@Autowired
	private UserUtil util;

	@Autowired
	private MyMailUtil mailUtil;


	@GetMapping("/login")
	public String showLogin() {
		return "UserLogin";
	}

	@GetMapping("/profile")
	public String showProfile() {
		return "UserProfile";
	}

	@GetMapping("/setup")
	public String setup(HttpSession session, Principal p) {

		// read current username
		String username = p.getName();

		// load user object
		User user = service.findByUsername(username).get();

		System.out.println("userob" + user);

		// store in HttpSession
		session.setAttribute("userOb", user);

		// session.setMaxInactiveInterval(10*60);

		return "UserHome";
	}

	@GetMapping("/showPwdUpdate")
	public String showPwdUpdate() {
		return "UserPwdUpdate";
	}

	@PostMapping("/pwdUpdate")
	public String pwdUpdate(HttpSession session, @RequestParam String password, Model model) {

		User user = (User) session.getAttribute("userOb");

		Long userId = user.getId();

		//String pwd = bd.encode(password);

		service.updatePassword(password, userId);
		
		//send email to user
		if(userId !=null)
			new Thread(new Runnable() {
				public void run() {
					String text = "YOUR USERNAME IS: " + user.getUsername() +", AND NEW PASSWORD IS "+ password;
					mailUtil.send(user.getUsername(), "PASWORD UPDATED!", text);
				}
			}).start();

		model.addAttribute("message", "Password Updated!");
		return "UserPwdUpdate";
	}

}

package com.example.HealthCareMini.Runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.HealthCareMini.Entity.User;
import com.example.HealthCareMini.Utility.MyMailUtil;
import com.example.HealthCareMini.Utility.UserUtil;
import com.example.HealthCareMini.constants.UserRoles;
import com.example.HealthCareMini.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


@Component
public class MasterAccountSetUpRunner implements CommandLineRunner {

	@Value("${master.user.name}")
	private String displayName;
	
	@Value("${master.user.email}")
	private String username;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil userUtil;
	
	@Autowired
	private MyMailUtil mailUtil ;
	
	@Autowired
	private BCryptPasswordEncoder bd;
	
	public void run(String... args) throws Exception {
		if(!userService.findByUsername(username).isPresent()) {
			String pwd = userUtil.genPwd();
			User user = new User();
			user.setDisplayName(displayName);
			user.setUsername(username);
			user.setPassword(bd.encode(pwd));
			user.setRole(UserRoles.ADMIN.name());
			Long genId  = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your uname is " + username +", password is "+ pwd;
						mailUtil.send(username, "ADMIN ADDED", text);
					}
				}).start();
		}
	}	

}
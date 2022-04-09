package com.citi.marketcap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.citi.marketcap.dto.User;
import com.citi.marketcap.service.UserService;
import com.citi.marketcap.service.UserServiceImpl;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String welcomePage(ModelMap modelMap, @RequestParam String userName, @RequestParam String password) {
		User user = new User(1, userName, password);
		String isLoggedIn = userService.loggedIn(user);
		if (isLoggedIn.equals("success"))
			return "welcome";
		
		modelMap.put("error", isLoggedIn);
			return "login";
	}
}

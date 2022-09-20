package com.citi.marketcap.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.marketcap.dto.Stock;
import com.citi.marketcap.dto.User;
import com.citi.marketcap.repository.UserRepository;

//singleton
@Service("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public String loggedIn(User user)
	{
		return userRepository.loggedIn(user);
	}
}

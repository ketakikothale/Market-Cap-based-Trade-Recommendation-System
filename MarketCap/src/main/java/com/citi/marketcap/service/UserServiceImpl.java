package com.citi.marketcap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.marketcap.dto.User;
import com.citi.marketcap.repository.UserRepository;

//singleton
@Service("userService")
public class UserServiceImpl implements UserService {

//	private UserServiceImpl() {
//	}
//
//	private static UserService userService;// only 1 reference because of static
//
//	public static UserService getInstance() {
//		if (userService == null) {
//			userService = new UserServiceImpl();
//			return userService;
//		}
//		return userService;
//	}

	@Autowired
	private UserRepository userRepository;//=UserRepositoryImpl.getInstance();;

	@Override
	public String loggedIn(User user) {
		return userRepository.loggedIn(user);
	}
	

}

package com.citi.marketcap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.marketcap.dto.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

//	private UserRepositoryImpl() {
//	}
//
//	private static UserRepository userRepository;
//
//	public static UserRepository getInstance() {
//		if (userRepository == null) {
//			userRepository = new UserRepositoryImpl();
//			return userRepository;
//		}
//		return userRepository;
//	}

	@Autowired
	DataSource dataSource;

	@Override
	public String loggedIn(User user) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "select * from user where userName=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getUserName());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				if (user.getPassword().equals(resultSet.getString(3))) {
					return "success";
				} else {
					return "Fail:Password not match";
				}
			} else {
				return "fail:user not found!!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "fail";
	}

}

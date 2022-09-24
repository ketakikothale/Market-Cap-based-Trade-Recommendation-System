package com.citi.marketcap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.marketcap.controller.LoginController;
import com.citi.marketcap.dto.Stock;

@Repository
public class StockRepositoryImpl implements StockRepository
{

	@Autowired
	DataSource dataSource;

	@Override
	public String saveStock(Stock stock)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try
		{
			connection = dataSource.getConnection();
			String query = "insert into user_saved_stock values(?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, LoginController.user.getUserId());
			preparedStatement.setString(2, stock.getSymbol());
			preparedStatement.setDouble(3, stock.getPrice());
			preparedStatement.setInt(4, 1);
			preparedStatement.setString(5, stock.getDate());
			preparedStatement.setString(6, stock.getTime());

			int res = preparedStatement.executeUpdate();

			if (res > 0) return "success";
			else
				return "failure";
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return "failure";
	}
	
	@Override
	public ArrayList<Stock> getSaved()
	{
		ArrayList<Stock> list = null;
		Connection connection = null;
		ResultSet resultSet = null;
		String query = "select * from user_saved_stock";
		boolean flag = false;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Stock s = new Stock();
				
				if(LoginController.user.getUserId()==resultSet.getInt(1))
				{
					s.setSymbol(resultSet.getString(2));
					s.setPrice(resultSet.getDouble(3));
					s.setDate(resultSet.getString(5));
					s.setTime(resultSet.getString(6));
					if (s != null && !flag) { // improve the condition
						flag = true;
						list = new ArrayList<>();
					}
					list.add(s);
				}
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public void unsaveStock(Stock stock) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try
		{
			connection = dataSource.getConnection();
			String query = "delete from user_saved_stock where (userId=? and symbol=? and time=?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, LoginController.user.getUserId());
			preparedStatement.setString(2, stock.getSymbol());
			preparedStatement.setString(3, stock.getTime());
			
//			System.out.println(preparedStatement.toString());
			
			int res = preparedStatement.executeUpdate();

			if (res > 0) 
				System.out.println("deleted");
			else
				System.out.println("not deleted");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
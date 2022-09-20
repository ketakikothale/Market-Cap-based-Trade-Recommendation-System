package com.citi.marketcap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.marketcap.controller.LoginController;
import com.citi.marketcap.dto.Stock;
import com.citi.marketcap.dto.User;

@Repository
public class StockRepositoryImpl implements StockRepository
{

	@Autowired
	DataSource dataSource;

	@Override
	public String marketCap(User user)
	{
		return "Hi all";
	}

	@Override
	public Stock stockDetails(String symbol)
	{
		Stock s1 = null;
//		try {
//			yahoofinance.Stock stk = YahooFinance.get(symbol);
//			if (stk != null) {
//				StockQuote stkq = YahooFinance.get(symbol).getQuote(true);
//				s1 = new Stock(symbol, stkq.getChange(), stkq.getPrice());
//			}
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}

		return s1;

//		StringBuilder informationString = new StringBuilder();
//		String APIkey = "6d9bce770b5f2123cc374e05f79ec341";//"f6baf1e3ee826845e485e907450fddbe";
//		try {
//			URL marketCap = new URL("https://financialmodelingprep.com/api/v3/quote/" + symbol + "?apikey=" + APIkey);
//			HttpURLConnection conn;
//			try {
//				conn = (HttpURLConnection) marketCap.openConnection();
//				conn.setRequestMethod("GET");
//				conn.connect();
//
//				// Check if connect is made
//				int responseCode = conn.getResponseCode();
//
//				// 200 OK
//				if (responseCode != 200) {
//					throw new RuntimeException("HttpResponseCode: " + responseCode);
//				} else {
//					Scanner scanner = new Scanner(marketCap.openStream());
//
//					while (scanner.hasNext()) {
//						informationString.append(scanner.nextLine());
//					}
//					// Close the scanner
//					scanner.close();
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	System.out.println(informationString);
//		
////		Stock s1=null;
////		
////		Gson gson=new Gson();
////		s1=gson.fromJson(String.valueOf(informationString), Stock.class);
//		
//		return null;
	}

	@Override
	public String saveStock(Stock stock)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try
		{
			connection = dataSource.getConnection();
			String query = "insert into user_saved_stock values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, LoginController.user.getUserId());
			preparedStatement.setString(2, stock.getSymbol());
			preparedStatement.setDouble(3, stock.getPrice());
			preparedStatement.setInt(4, 1);

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
			// list = new ArrayList<>(); // memory?
			while (resultSet.next()) {
				Stock s = new Stock();
				
				if(LoginController.user.getUserId()==resultSet.getInt(1))
				{
					s.setSymbol(resultSet.getString(2));
					s.setPrice(resultSet.getDouble(3));
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
	public void unsaveStock(String stockSymbol) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try
		{
			connection = dataSource.getConnection();
			String query = "delete from user_saved_stock where (userId=? and symbol=?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, LoginController.user.getUserId());
			preparedStatement.setString(2, stockSymbol);
			
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
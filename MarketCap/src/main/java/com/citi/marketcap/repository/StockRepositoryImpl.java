package com.citi.marketcap.repository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.marketcap.dto.Stock;
import com.citi.marketcap.dto.User;
import com.google.gson.Gson;

import yahoofinance.*;
import yahoofinance.quotes.stock.StockQuote;

@Repository
public class StockRepositoryImpl implements StockRepository {

	@Autowired
	DataSource dataSource;

	@Override
	public String marketCap(User user) {
		return "Hi all";
	}

	@Override
	public Stock stockDetails(String symbol) {
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
}

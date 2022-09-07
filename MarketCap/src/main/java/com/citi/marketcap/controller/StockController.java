package com.citi.marketcap.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.citi.marketcap.service.StockService;

@Controller
public class StockController {

	@Autowired
	StockService stockService;
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcomePage() {
		return "welcome";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public void display(ModelMap modelMap,@RequestParam(value="type") String str) {
		System.out.println(str);
		String APIkey = "f6baf1e3ee826845e485e907450fddbe";
		StringBuilder informationString = new StringBuilder();
		
		if(str.compareTo("small")==0)
		{
			try {

				// large stocks have market cap between 300 million to 2 billion dollars
				URL marketCap = new URL(
						"https://financialmodelingprep.com/api/v3/stock-screener?marketCapLowerThan=159862800000&marketCapMoreThan=23981595000&exchange=NASDAQ&apikey="
								+ APIkey);

				HttpURLConnection conn = (HttpURLConnection) marketCap.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();

				// Check if connect is made
				int responseCode = conn.getResponseCode();

				// 200 OK
				if (responseCode != 200) {
					throw new RuntimeException("HttpResponseCode: " + responseCode);
				} else {

//					StringBuilder informationString = new StringBuilder();
					
					Scanner scanner = new Scanner(marketCap.openStream());

					while (scanner.hasNext()) {
						informationString.append(scanner.nextLine());
					}
					// Close the scanner
					scanner.close();

//					System.out.println(informationString);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(str.compareTo("mid")==0)
		{
			try {

				// mid stocks have market cap between 2-10 billion dollars
				URL marketCap = new URL(
						"https://financialmodelingprep.com/api/v3/stock-screener?marketCapLowerThan=797083000000&marketCapMoreThan=159862800000&exchange=NASDAQ&apikey="
								+ APIkey);

				HttpURLConnection conn = (HttpURLConnection) marketCap.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();

				// Check if connect is made
				int responseCode = conn.getResponseCode();

				// 200 OK
				if (responseCode != 200) {
					throw new RuntimeException("HttpResponseCode: " + responseCode);
				} else {

//					StringBuilder informationString = new StringBuilder();
					Scanner scanner = new Scanner(marketCap.openStream());

					while (scanner.hasNext()) {
						informationString.append(scanner.nextLine());
					}
					// Close the scanner
					scanner.close();

//					System.out.println(informationString);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(str.compareTo("large")==0)
		{
			try {

				// large stocks have market cap >5 billion dollars
				URL marketCap = new URL(
						"https://financialmodelingprep.com/api/v3/stock-screener?marketCapMoreThan=398541500000&exchange=NASDAQ&apikey="
								+ APIkey);

				HttpURLConnection conn = (HttpURLConnection) marketCap.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();

				// Check if connect is made
				int responseCode = conn.getResponseCode();

				// 200 OK
				if (responseCode != 200) {
					throw new RuntimeException("HttpResponseCode: " + responseCode);
				} else {

//					StringBuilder informationString = new StringBuilder();
					Scanner scanner = new Scanner(marketCap.openStream());

					while (scanner.hasNext()) {
						informationString.append(scanner.nextLine());
					}
					// Close the scanner
					scanner.close();

//					System.out.println(informationString);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		modelMap.clear();
//		modelMap.put("response", informationString);
	}
}

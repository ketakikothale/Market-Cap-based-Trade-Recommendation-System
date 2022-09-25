//package com.citi.marketcap.controller;
//
//import java.util.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import com.citi.marketcap.dto.Stock;
//import com.citi.marketcap.dto.StockComparator;
//import com.citi.marketcap.service.StockService;
//
//@Controller
//public class StockController
//{
//
//	ArrayList<Stock> topFive = new ArrayList<>();
//
//	@Autowired
//	StockService stockService;
//
//	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
//	public String welcomePage()
//	{
//		return "welcome";
//	}
//
//	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "type" })
//	public void display(ModelMap modelMap, @RequestParam(value = "type") String str)
//	{
//		// list to store stock to recommend
//		ArrayList<Stock> al = new ArrayList<>();
//		ArrayList<Stock> sort1 = new ArrayList<>();
//		ArrayList<Stock> sort2 = new ArrayList<>();
//		ArrayList<String> tickerList = new ArrayList<>();
//
//		String APIkey = "6d9bce770b5f2123cc374e05f79ec341";// "f6baf1e3ee826845e485e907450fddbe";
//		StringBuilder informationString = new StringBuilder();
//
//		if (str.compareTo("small") == 0)
//		{
//			try
//			{
//				// small stocks have market cap between 300 million to 2 billion dollars
//				URL marketCap = new URL(
//						"https://financialmodelingprep.com/api/v3/stock-screener?marketCapLowerThan=159862800000&marketCapMoreThan=23981595000&exchange=NASDAQ&apikey="
//								+ APIkey);
//
//				HttpURLConnection conn = (HttpURLConnection) marketCap.openConnection();
//				conn.setRequestMethod("GET");
//				conn.connect();
//
//				// Check if connect is made
//				int responseCode = conn.getResponseCode();
//
//				// 200 OK
//				if (responseCode != 200)
//				{
//					throw new RuntimeException("HttpResponseCode: " + responseCode);
//				}
//				else
//				{
//					Scanner scanner = new Scanner(marketCap.openStream());
//
//					while (scanner.hasNext())
//					{
//						informationString.append(scanner.nextLine());
//					}
//					// Close the scanner
//					scanner.close();
//				}
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//
//		else if (str.compareTo("mid") == 0)
//		{
//			try
//			{
//				// mid stocks have market cap between 2-10 billion dollars
//				URL marketCap = new URL(
//						"https://financialmodelingprep.com/api/v3/stock-screener?marketCapLowerThan=797083000000&marketCapMoreThan=159862800000&exchange=NASDAQ&apikey="
//								+ APIkey);
//
//				HttpURLConnection conn = (HttpURLConnection) marketCap.openConnection();
//				conn.setRequestMethod("GET");
//				conn.connect();
//
//				// Check if connect is made
//				int responseCode = conn.getResponseCode();
//
//				// 200 OK
//				if (responseCode != 200)
//				{
//					throw new RuntimeException("HttpResponseCode: " + responseCode);
//				}
//				else
//				{
//					Scanner scanner = new Scanner(marketCap.openStream());
//
//					while (scanner.hasNext())
//					{
//						informationString.append(scanner.nextLine());
//					}
//					// Close the scanner
//					scanner.close();
//				}
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//
//		else if (str.compareTo("large") == 0)
//		{
//			try
//			{
//
//				// large stocks have market cap >5 billion dollars
//				URL marketCap = new URL(
//						"https://financialmodelingprep.com/api/v3/stock-screener?marketCapMoreThan=398541500000&exchange=NASDAQ&apikey="
//								+ APIkey);
//
//				HttpURLConnection conn = (HttpURLConnection) marketCap.openConnection();
//				conn.setRequestMethod("GET");
//				conn.connect();
//
//				// Check if connect is made
//				int responseCode = conn.getResponseCode();
//
//				// 200 OK
//				if (responseCode != 200)
//				{
//					throw new RuntimeException("HttpResponseCode: " + responseCode);
//				}
//				else
//				{
//					Scanner scanner = new Scanner(marketCap.openStream());
//
//					while (scanner.hasNext())
//					{
//						informationString.append(scanner.nextLine());
//					}
//					// Close the scanner
//					scanner.close();
//				}
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//		modelMap.clear();
//
//		al.clear();
//		tickerList.clear();
//		topFive.clear();
//		StringBuilder sb=new StringBuilder();
//
//		JSONParser parse = new JSONParser();
//		JSONArray dataObject;
//		try
//		{
//			dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
//
//			for (int i = 0; i < dataObject.size(); i++)
//			{
//				JSONObject countryData = (JSONObject) dataObject.get(i);
//				
//				
//				sb.append(countryData.get("symbol").toString());
//				if(i!=dataObject.size()-1)
//					sb.append(",");
//				
//				
//				
//				StringBuffer temp=new StringBuffer();
//				try
//				{
////					URL marketCap = new URL("https://financialmodelingprep.com/api/v3/stock-price-change/"+sb.toString()+"?apikey=f6baf1e3ee826845e485e907450fddbe");
//
//					URL marketCap=new URL("https://query1.finance.yahoo.com/v11/finance/quoteSummary/"+countryData.get("symbol").toString());
//					HttpURLConnection conn = (HttpURLConnection) marketCap.openConnection();
//					conn.setRequestMethod("GET");
//					conn.connect();
//
//					// Check if connect is made
//					int responseCode = conn.getResponseCode();
//
//					// 200 OK
//					if (responseCode != 200)
//					{
//						throw new RuntimeException("HttpResponseCode: " + responseCode);
//					}
//					else
//					{
//						Scanner scanner = new Scanner(marketCap.openStream());
//
//						while (scanner.hasNext())
//						{
//							temp.append(scanner.nextLine());
//						}
//						// Close the scanner
//						scanner.close();
//					}
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//				
//				System.out.println(temp);
//				
//				
//				
//				Stock stock = new Stock(countryData.get("symbol").toString(), countryData.get("companyName").toString(),
//						Double.parseDouble(countryData.get("price").toString()),
//						Double.parseDouble(countryData.get("beta").toString()),
//						Double.parseDouble(countryData.get("marketCap").toString()),
//						Double.parseDouble(countryData.get("volume").toString()),
//						Boolean.parseBoolean(countryData.get("isActivelyTrading").toString()), "", "");
//
//				al.add(stock);
//			}
//		}
//		catch (ParseException e)
//		{
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//		
//		
//
//		// sort al in descending order of volume
//		Collections.sort(al, new StockComparator());
//
//		// get stocks which are actively trading
//		for (int i = 0; i < al.size(); i++)
//		{
//			if (al.get(i).isActivelyTrading())
//			{
//				sort1.add(al.get(i));
//			}
//		}
//
//		// get stocks with beta value approx equal to 1.0
//		for (int i = 0; i < sort1.size(); i++)
//		{
//			if ((sort1.get(i).getBeta() >= 0.9) && (sort1.get(i).getBeta() <= 1.1))
//			{
//				sort2.add(al.get(i));
//			}
//		}
//
//		if (sort2.size() >= 5)
//		{
//			// recommend top five stocks only
//			for (int i = 0; i < 5; i++)
//			{
//				topFive.add(sort2.get(i));
//			}
//		}
//		else
//		{
//			// that means less than 5 stocks are available
//
//			if (sort1.size() >= 5)
//			{
//				// exclude beta value parameter
//				for (int i = 0; i < 5; i++)
//				{
//					topFive.add(sort1.get(i));
//				}
//			}
//			else
//			{
//				// exclude actively trading parameter
//				for (int i = 0; i < 5; i++)
//				{
//					topFive.add(al.get(i));
//				}
//			}
//		}
//
//		modelMap.put("response", topFive);
//	}
//
//	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "ticker" })
//	public void save(ModelMap modelMap, @RequestParam(value = "ticker") String str)
//	{
//		modelMap.put("response", topFive);
//
//		String status = "no status";
//
//		for (int i = 0; i < topFive.size(); i++)
//		{
//			if (topFive.get(i).getSymbol().compareTo(str) == 0)
//			{
//				// get current date
//				String m, date;
//				int d, y;
//				String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
//						"Dec" };
//
//				GregorianCalendar gcalendar = new GregorianCalendar();
//				d = gcalendar.get(Calendar.DATE);
//				m = months[gcalendar.get(Calendar.MONTH)];
//				y = gcalendar.get(Calendar.YEAR);
//				date = Integer.toString(d) + " " + m + " " + Integer.toString(y);
//
//				// get current time
//				int h, min, sec;
//				String time;
//				h = gcalendar.get(Calendar.HOUR);
//				min = gcalendar.get(Calendar.MINUTE);
//				sec = gcalendar.get(Calendar.SECOND);
//				time = Integer.toString(h) + ":" + Integer.toString(min) + ":" + Integer.toString(sec);
//
//				topFive.get(i).setDate(date);
//				topFive.get(i).setTime(time);
//
//				status = stockService.saveStock(topFive.get(i));
//			}
//		}
//	}
//
//	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "show" })
//	public void show(ModelMap modelMap, @RequestParam(value = "show") String str)
//	{
//
//		ArrayList<Stock> show = stockService.getSaved();
//		modelMap.put("showall", show);
//	}
//
//	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "unsave" })
//	public void unsave(ModelMap modelMap, @RequestParam(value = "unsave") String str)
//	{
//		ArrayList<Stock> savedStocks = stockService.getSaved();
//
//		System.out.println(str);
//
//		String[] arrOfStr = str.split(" ");
//
//		int index = 0;
//		for (int i = 0; i < savedStocks.size(); i++)
//		{
//			if (savedStocks.get(i).getSymbol().equals(arrOfStr[0]))
//			{
//				index = i;
//
//				savedStocks.get(index).setTime(arrOfStr[1]);
//
//				Stock stockToUnsave = savedStocks.get(index);
//				stockService.unsaveStock(stockToUnsave);
//				break;
//			}
//		}
//	}
//}

package com.citi.marketcap.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.citi.marketcap.dto.Stock;
import com.citi.marketcap.repository.APIImpl;
import com.citi.marketcap.service.APIService;
import com.citi.marketcap.service.StockService;
import com.citi.marketcap.service.UserService;

@Controller
public class StockController
{
	@Autowired
	StockService stockService;
	
	@Autowired
	UserService userService;

	@Autowired
	APIService apiService;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcomePage()
	{
		return "welcome";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "type" })
	public void display(ModelMap modelMap, @RequestParam(value = "type") String str)
	{
		apiService.recommend(str);
		modelMap.clear();
		modelMap.put("response", APIImpl.topFive);
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "ticker" })
	public void save(ModelMap modelMap, @RequestParam(value = "ticker") String str, @RequestParam(value = "quantity") String quantity)
	{
		int numberOnly= Integer.valueOf(quantity.replaceAll("[^0-9]", ""));
		System.out.println(numberOnly);
		modelMap.put("response", APIImpl.topFive);
		stockService.saveStock(str, numberOnly);
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "show" })
	public void show(ModelMap modelMap, @RequestParam(value = "show") String str)
	{
		ArrayList<Stock> show = stockService.getSaved();
		modelMap.put("showall", show);
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "unsave" })
	public void unsave(ModelMap modelMap, @RequestParam(value = "unsave") String str)
	{
		ArrayList<Stock> savedStocks = stockService.getSaved();
		stockService.unsaveStock(savedStocks, str);
		savedStocks = stockService.getSaved();
		modelMap.put("showall", savedStocks);
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "logout" })
	public String logout(ModelMap modelMap, @RequestParam(value = "logout") String str)
	{
		userService.logOut(LoginController.user);
		return "redirect:/login";
	}
}

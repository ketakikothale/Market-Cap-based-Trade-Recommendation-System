package com.citi.marketcap.controller;

import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.citi.marketcap.dto.Stock;
import com.citi.marketcap.dto.Stock1;
import com.citi.marketcap.dto.Stock2;
import com.citi.marketcap.service.StockService;

@Controller
public class StockController
{

	ArrayList<Stock> topFive = new ArrayList<>();
	ArrayList<Stock1> stockSymbolsAL = new ArrayList<>();
	
	@Autowired
	StockService stockService;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcomePage()
	{
		return "welcome";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "type" })
	public void display(ModelMap modelMap, @RequestParam(value = "type") String str)
	{
		String APIkey = "6d9bce770b5f2123cc374e05f79ec341";// "f6baf1e3ee826845e485e907450fddbe";
		StringBuilder informationString = new StringBuilder();

		if (str.compareTo("small") == 0)
		{
			try
			{
				// small stocks have market cap between 300 million to 2 billion dollars
				URL marketCap = new URL(
						"https://financialmodelingprep.com/api/v3/stock-screener?marketCapLowerThan=159862800000&marketCapMoreThan=23981595000&exchange=NASDAQ&apikey="
								+ APIkey);

				HttpURLConnection conn = (HttpURLConnection) marketCap.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();

				// Check if connect is made
				int responseCode = conn.getResponseCode();

				// 200 OK
				if (responseCode != 200)
				{
					throw new RuntimeException("HttpResponseCode: " + responseCode);
				}
				else
				{
					Scanner scanner = new Scanner(marketCap.openStream());

					while (scanner.hasNext())
					{
						informationString.append(scanner.nextLine());
					}
					// Close the scanner
					scanner.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		else if (str.compareTo("mid") == 0)
		{
			try
			{
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
				if (responseCode != 200)
				{
					throw new RuntimeException("HttpResponseCode: " + responseCode);
				}
				else
				{
					Scanner scanner = new Scanner(marketCap.openStream());

					while (scanner.hasNext())
					{
						informationString.append(scanner.nextLine());
					}
					// Close the scanner
					scanner.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		else if (str.compareTo("large") == 0) 
		{
			try
			{

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
				if (responseCode != 200)
				{
					throw new RuntimeException("HttpResponseCode: " + responseCode);
				}
				else
				{
					Scanner scanner = new Scanner(marketCap.openStream());

					while (scanner.hasNext())
					{
						informationString.append(scanner.nextLine());
					}
					// Close the scanner
					scanner.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		modelMap.clear();
		stockSymbolsAL.clear();
		topFive.clear();
		
		StringBuilder stockSymbolsSB = new StringBuilder();
		JSONParser parse = new JSONParser();
		JSONArray dataObject;
		try
		{
			dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

			for (int i = 0; i < dataObject.size(); i++)
			{
				JSONObject countryData = (JSONObject) dataObject.get(i);
				
				stockSymbolsSB.append(countryData.get("symbol").toString());
				if(i!=dataObject.size()-1)
					stockSymbolsSB.append(",");
				
				Stock1 s1 = new Stock1(countryData.get("symbol").toString(),
						Double.parseDouble(countryData.get("price").toString()),
						Double.parseDouble(countryData.get("beta").toString()),
						Double.parseDouble(countryData.get("marketCap").toString()),
						Double.parseDouble(countryData.get("volume").toString()));
				
				stockSymbolsAL.add(s1);
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		StringBuffer temp=new StringBuffer();
		try
		{
			URL marketCap=new URL("https://query1.finance.yahoo.com/v7/finance/quote?symbols="+stockSymbolsSB.toString());

			HttpURLConnection conn = (HttpURLConnection) marketCap.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			// Check if connect is made
			int responseCode = conn.getResponseCode();

			// 200 OK
			if (responseCode != 200)
			{
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			}
			else
			{
				Scanner scanner = new Scanner(marketCap.openStream());

				while (scanner.hasNext())
				{
					temp.append(scanner.nextLine());
				}
				// Close the scanner
				scanner.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		ArrayList<Stock2> yahooStocksAL = new ArrayList<Stock2>();
		
		JSONParser parser = new JSONParser();
        JSONObject json=null;
        JSONObject json1=null;
        JSONArray dataObject1;
		
		try
		{
			json = (JSONObject) parser.parse(temp.toString());
            String s1=json.get("quoteResponse").toString();
            
            json1 = (JSONObject) parser.parse(s1);
            String s2=json1.get("result").toString();
            
            dataObject1 = (JSONArray) parser.parse(s2);

            for (int i = 0; i < dataObject1.size(); i++)
            {
                JSONObject countryData = (JSONObject) dataObject1.get(i);
                
                double changePercent = 0;
                String rating = "null";
                double pe = 0;
                try {
                	changePercent = Double.parseDouble(countryData.get("twoHundredDayAverageChangePercent").toString());
                	rating = countryData.get("averageAnalystRating").toString();
                	pe = Double.parseDouble(countryData.get("trailingPE").toString());
                }
                catch(Exception e) {
                	
                }
                
                
                Stock2 stock2 = new Stock2(countryData.get("symbol").toString(),
                		rating, changePercent, pe);
				
                yahooStocksAL.add(stock2);
            }
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		HashMap<String,Stock2> hmp = new HashMap<>();
		
		for(int i=0;i<stockSymbolsAL.size();i++) {
			hmp.put(stockSymbolsAL.get(i).getSymbol(), new Stock2());
		}
		
		for(int i=0;i<yahooStocksAL.size();i++) {
			if(hmp.containsKey(yahooStocksAL.get(i).getSymbol())) {
				hmp.put(yahooStocksAL.get(i).getSymbol(), yahooStocksAL.get(i));
			}
		}
		
		topFive = stockRecommendation(hmp);
		
		modelMap.put("response", topFive);
	}

	public ArrayList<Stock> stockRecommendation(HashMap<String,Stock2> hmp) {
		ArrayList<Stock> topPerformance = new ArrayList<Stock>();
		ArrayList<Stock> topRating = new ArrayList<Stock>();
		ArrayList<Stock> topFiveFinal = new ArrayList<Stock>();
		
		//convert HashMap into List   
		List<Entry<String, Stock2>> list = new LinkedList<Entry<String, Stock2>>(hmp.entrySet());
		
		sortHashMap(list); //sort hmp based on - twoHundredDayAverageChangePercent
		
		Map<String, Stock2> sortedMap = new LinkedHashMap<String, Stock2>();  
		for (Entry<String, Stock2> entry : list)   
		{  
			sortedMap.put(entry.getKey(), entry.getValue());  
		}  
		
		for (Entry<String, Stock2> entry : sortedMap.entrySet())   
		{
			String n = "";
			double mc = 0.0;
			double beta = 0.0;
			double vol = 0.0;
			double p = 0.0;
			
			for(int i=0;i<stockSymbolsAL.size();i++) {
				if(stockSymbolsAL.get(i).getSymbol().compareTo(entry.getKey()) == 0) {
					mc = stockSymbolsAL.get(i).getMarketCap();
					beta = stockSymbolsAL.get(i).getBeta();
					vol = stockSymbolsAL.get(i).getVolume(); 
					p = stockSymbolsAL.get(i).getPrice();
					break;
				}
			}
			
			Stock s = new Stock(
					entry.getKey().toString(),
					p,
					beta,
					mc,
					vol,
					entry.getValue().getAverageAnalystRating(),
					entry.getValue().getTwoHundredDayAverageChangePercent(),
					entry.getValue().getTrailingPE(),
					"",""
					);
			
			topPerformance.add(s);
		}  
	
		//check for the average analyst rating
		int count = 0;
		if(topPerformance.size() > 5) {
			for(int i=0;i<topPerformance.size();i++) {
				if((topPerformance.get(i).getAverageAnalystRating() != null)) {
					if(topPerformance.get(i).getAverageAnalystRating().contains("Buy")) {
						topRating.add(topPerformance.get(i));
						count++;
					}
				}	
			}
		}
		
		int countNew = 0;
		if(topRating.size() > 5) {
			for(int i=0;i<topRating.size();i++) {
				if((topRating.get(i).getTrailingPE() >= 15) && (topRating.get(i).getTrailingPE() <=20)) {
					topFiveFinal.add(topRating.get(i));
					countNew++;
				}	
			}
		}
		
		ArrayList<Stock> ans = new ArrayList<Stock>();
		if(topFiveFinal.size() >= 5) {
			ans.add(topFiveFinal.get(0));
			ans.add(topFiveFinal.get(1));
			ans.add(topFiveFinal.get(2));
			ans.add(topFiveFinal.get(3));
			ans.add(topFiveFinal.get(4));
			return ans;
		}
		else if(topRating.size() >= 5) {
			ans.add(topRating.get(0));
			ans.add(topRating.get(1));
			ans.add(topRating.get(2));
			ans.add(topRating.get(3));
			ans.add(topRating.get(4));
			return ans;
		}
		else {
			ans.add(topPerformance.get(0));
			ans.add(topPerformance.get(1));
			ans.add(topPerformance.get(2));
			ans.add(topPerformance.get(3));
			ans.add(topPerformance.get(4));
			return ans;
		}
	}
	
	public List<Entry<String, Stock2>> sortHashMap(List<Entry<String, Stock2>> list) {
		//sorting the list elements  
		Collections.sort(list, new Comparator<Entry<String, Stock2>>() {  
			@Override
			public int compare(Entry<String, Stock2> o1, Entry<String, Stock2> o2) {
				//sort in descending order 
				if(o1.getValue().getTwoHundredDayAverageChangePercent() == o2.getValue().getTwoHundredDayAverageChangePercent()) {
					return 0;
				}
				else if(o1.getValue().getTwoHundredDayAverageChangePercent() > o2.getValue().getTwoHundredDayAverageChangePercent()) {
					return -1;
				}
				else {
					return 1;
				}
			}
		});
		
		return list;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.POST, params = { "ticker" })
	public void save(ModelMap modelMap, @RequestParam(value = "ticker") String str)
	{
		modelMap.put("response", topFive);

		String status = "no status";

		for (int i = 0; i < topFive.size(); i++)
		{
			if (topFive.get(i).getSymbol().compareTo(str) == 0)
			{
				// get current date
				String m, date;
				int d, y;
				String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
						"Dec" };

				GregorianCalendar gcalendar = new GregorianCalendar();
				d = gcalendar.get(Calendar.DATE);
				m = months[gcalendar.get(Calendar.MONTH)];
				y = gcalendar.get(Calendar.YEAR);
				date = Integer.toString(d) + " " + m + " " + Integer.toString(y);

				// get current time
				int h, min, sec;
				String time;
				h = gcalendar.get(Calendar.HOUR);
				min = gcalendar.get(Calendar.MINUTE);
				sec = gcalendar.get(Calendar.SECOND);
				time = Integer.toString(h) + ":" + Integer.toString(min) + ":" + Integer.toString(sec);

				topFive.get(i).setDate(date);
				topFive.get(i).setTime(time);

				status = stockService.saveStock(topFive.get(i));
			}
		}
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

		String[] arrOfStr = str.split(" ");

		int index = 0;
		for (int i = 0; i < savedStocks.size(); i++)
		{
			if (savedStocks.get(i).getSymbol().equals(arrOfStr[0]))
			{
				index = i;

				savedStocks.get(index).setTime(arrOfStr[1]);

				Stock stockToUnsave = savedStocks.get(index);
				stockService.unsaveStock(stockToUnsave);
				break;
			}
		}
	}
}
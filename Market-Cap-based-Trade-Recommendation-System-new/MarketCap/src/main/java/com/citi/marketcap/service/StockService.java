package com.citi.marketcap.service;

import java.util.ArrayList;

import com.citi.marketcap.dto.Stock;
import com.citi.marketcap.dto.User;

public interface StockService
{
	public String marketCap(User user);

	public Stock stockDetails(String symbol);

	public String saveStock(Stock stock);
	
	public ArrayList<Stock> getSaved();
	
	public void unsaveStock(Stock stock);
}
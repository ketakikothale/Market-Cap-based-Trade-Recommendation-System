package com.citi.marketcap.service;

import java.util.ArrayList;

import com.citi.marketcap.dto.Stock;

public interface StockService
{

	public String saveStock(Stock stockNew);
	
	public ArrayList<Stock> getSaved();
	
	public void unsaveStock(Stock stock);
}
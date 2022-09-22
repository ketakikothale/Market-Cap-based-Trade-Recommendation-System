package com.citi.marketcap.repository;

import java.util.ArrayList;

import com.citi.marketcap.dto.Stock;

public interface StockRepository
{
	public String saveStock(Stock stock);
	
	public ArrayList<Stock> getSaved();
	
	public void unsaveStock(Stock stock);
}
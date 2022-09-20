package com.citi.marketcap.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.marketcap.dto.Stock;
import com.citi.marketcap.dto.User;
import com.citi.marketcap.repository.StockRepository;

//singleton
@Service("stockService")
public class StockServiceImpl implements StockService
{

	@Autowired
	private StockRepository stockRepository;

	@Override
	public String marketCap(User user)
	{
		return stockRepository.marketCap(user);
	}

	@Override
	public Stock stockDetails(String symbol)
	{
		return stockRepository.stockDetails(symbol);
	}

	@Override
	public String saveStock(Stock stock)
	{
		return stockRepository.saveStock(stock);
	}
	
	@Override
	public ArrayList<Stock> getSaved()
	{
		return stockRepository.getSaved();
	}

	@Override
	public void unsaveStock(String stockSymbol) {
		stockRepository.unsaveStock(stockSymbol);
	}
}
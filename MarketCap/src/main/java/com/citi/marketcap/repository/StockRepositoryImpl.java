package com.citi.marketcap.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.marketcap.dto.User;

@Repository
public class StockRepositoryImpl implements StockRepository{

	@Autowired
	DataSource dataSource;
	
	@Override
	public String marketCap(User user)
	{
		return "Hi all";
	}
}

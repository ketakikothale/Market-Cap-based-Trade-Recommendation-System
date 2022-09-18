package com.citi.marketcap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.citi.marketcap")
public class MarketCapApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MarketCapApplication.class, args);
	}
}

/*
 * 
 * { "symbol" : "FB", "companyName" : "Meta Platforms, Inc.", "marketCap" :
 * 547005300736, "sector" : "Communication Services", "industry" :
 * "Internet Content & Information", "beta" : 1.3761, "price" : 196.6400,
 * "lastAnnualDividend" : 0.0000, "volume" : 31344400, "exchange" :
 * "NASDAQ Global Select", "exchangeShortName" : "NASDAQ", "country" : "US",
 * "isEtf" : false, "isActivelyTrading" : false }
 * 
 */

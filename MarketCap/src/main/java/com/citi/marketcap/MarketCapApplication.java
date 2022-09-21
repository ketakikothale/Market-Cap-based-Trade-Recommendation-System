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
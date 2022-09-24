package com.citi.marketcap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Stock
{
	String symbol;
	String name;
	double price;
	double beta;
	double marketCap;
	double volume;
	boolean isActivelyTrading;
	String date;
	String time;
}
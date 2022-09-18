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
//	double change;
	String name;
	double price;
	double beta;
	double marketCap;

//	double changesPercentage;
//	double change;
//	double dayLow;
//	double dayHigh;
//	double yearHigh;
//	double yearLow;

//	double priceAvg50;
//	double priceAvg200;
	double volume;
//	double avgVolume;
//	String exchange;
//	double open;
//	double previousClose;
//	double eps;
//	double pe;
//	String earningsAnnouncement;
//	long sharesOutstanding;
//	long timestamp;
	boolean isActivelyTrading;
}

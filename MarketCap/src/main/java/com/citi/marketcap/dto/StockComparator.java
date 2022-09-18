package com.citi.marketcap.dto;

import java.util.Comparator;

public class StockComparator implements Comparator<Stock>
{
	// override the compare() method
	public int compare(Stock s1, Stock s2)
	{
		if (s1.volume == s2.volume) return 0;
		else if (s1.volume > s2.volume) return -1;
		else
			return 1;
	}
}

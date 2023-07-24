package in.ey.trs.service;

import java.time.LocalDate;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	Map<String, String> baseCurrencyMap = Map.of("SampleCusip", "USD");
	Map<String, String> quoteCurrencyMap = Map.of("SampleCusip", "GBP");
	Map<String, String> issueTypeMap = Map.of("SampleCusip", "OPTION");
	Map<String, String> strikePriceMap = Map.of("SampleCusip", "101");
	Map<String, LocalDate> maturityDateMap = Map.of("SampleCusip", LocalDate.parse("2022-07-10"));
	
	public String baseCurrency(String cusip) {
		return baseCurrencyMap.getOrDefault(cusip, "USD");
	}

	public String quoteCurrency(String cusip) {
		return quoteCurrencyMap.getOrDefault(cusip, "GBP");
	}
	
	public String issueType(String cusip) {
		return issueTypeMap.getOrDefault(cusip, "FUTURE");
	}
	
	public String strikePrice(String cusip) {
		return strikePriceMap.getOrDefault(cusip, "10");
	}
	
	public LocalDate maturityDate(String cusip) {
		return maturityDateMap.getOrDefault(cusip, LocalDate.parse("1999-01-01"));
	}
}

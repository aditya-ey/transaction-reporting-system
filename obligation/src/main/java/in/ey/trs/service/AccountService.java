package in.ey.trs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	List<String> eligibleCusips = List.of("");
	
	public boolean isMultiLateralTradingFacility(String cusip) {
		return eligibleCusips.contains(cusip);
	}

	public boolean isOrganisedTradingFacility(String cusip) {
		return eligibleCusips.contains(cusip);
	}
	
	public boolean isSwapExecutionFacility(String cusip) {
		return eligibleCusips.contains(cusip);
	}
}

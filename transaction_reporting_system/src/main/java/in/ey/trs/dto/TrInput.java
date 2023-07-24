package in.ey.trs.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import in.ey.trs.entity.TrInputAcquired;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = TrInput.class)
public class TrInput implements Serializable {

	private static final long serialVersionUID = -4552418342720668691L;
	String tradeId;
	String account;
	int price;
	int settlementPrice;
	String quantity;
	String cusip;
	String currency;
	String tradeState;
	Date executionTimestamp;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int strikePrice) {
		this.price = strikePrice;
	}

	public int getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(int settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public Date getExecutionTimestamp() {
		return executionTimestamp;
	}

	public void setExecutionTimestamp(Date executionTimestamp) {
		this.executionTimestamp = executionTimestamp;
	}

	
	@Override
	public String toString() {
		return "TrInput [tradeId=" + tradeId + ", account=" + account + ", strikePrice=" + price
				+ ", settlementPrice=" + settlementPrice + ", quantity=" + quantity + ", cusip=" + cusip + ", currency="
				+ currency + ", tradeState=" + tradeState + ", executionTimestamp=" + executionTimestamp + "]";
	}

	// Converts Entity into DTO
	public static TrInput valueOf(TrInputAcquired trInputAcquired) {
		TrInput trInput = new TrInput();
		trInput.setTradeId(trInputAcquired.getTradeId());
		trInput.setAccount(trInputAcquired.getAccount());
		trInput.setPrice(trInputAcquired.getPrice());
		trInput.setSettlementPrice(trInputAcquired.getSettlementPrice());
		trInput.setQuantity(trInputAcquired.getQuantity());
		trInput.setCusip(trInputAcquired.getCusip());
		trInput.setCurrency(trInputAcquired.getCurrency());
		trInput.setTradeState(trInputAcquired.getTradeState());
		trInput.setExecutionTimestamp(trInputAcquired.getExecutionTimestamp());
		return trInput;
	}

	// Converts DTO into Entity
	public TrInputAcquired createEntity() {
		TrInputAcquired trInputAcquired = new TrInputAcquired();
		trInputAcquired.setTradeId(this.getTradeId());
		trInputAcquired.setAccount(this.getAccount());
		trInputAcquired.setPrice(this.getPrice());
		trInputAcquired.setSettlementPrice(this.getSettlementPrice());
		trInputAcquired.setQuantity(this.getQuantity());
		trInputAcquired.setCusip(this.getCusip());
		trInputAcquired.setCurrency(this.getCurrency());
		trInputAcquired.setTradeState(this.getTradeState());
		trInputAcquired.setExecutionTimestamp(this.getExecutionTimestamp());
		return trInputAcquired;
	}

}

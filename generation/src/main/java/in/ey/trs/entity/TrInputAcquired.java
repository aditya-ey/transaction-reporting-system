package in.ey.trs.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class TrInputAcquired {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "trade_id", nullable = false, length = 50)
	String tradeId;
	
	@Column(nullable = false, length = 20)
	String account;
	
	@Column(name = "price", nullable = false)
	Integer price;
	
	@Column(name = "settlement_price", nullable = false)
	Integer settlementPrice;
	
	@Column(nullable = false, length = 20)
	String quantity;
	
	@Column(nullable = false, length = 10)
	String cusip;
	
	@Column(length = 10)
	String currency;
	
	@Column(nullable = false, length = 10)
	String tradeState;
	
	@Column(name = "execution_timestamp", nullable = false)
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


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public Integer getSettlementPrice() {
		return settlementPrice;
	}


	public void setSettlementPrice(Integer settlementPrice) {
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


	public TrInputAcquired() {
		super();
	}
}

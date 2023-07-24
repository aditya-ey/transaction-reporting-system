package in.ey.trs.dto;

import java.time.LocalDate;
import java.util.Date;

public class Report {

	String mandate;
	String actionType;
	String businessTransactionId;
	String productId;
	String productIdType;
	String quantity;
	String cleared;
	String deliverableCurrency;
	String notional;
	Date executionTimestamp;
	LocalDate settlementDate;
	String tradeId;

	public String getMandate() {
		return mandate;
	}

	public String getActionType() {
		return actionType;
	}

	public String getBusinessTransactionId() {
		return businessTransactionId;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductIdType() {
		return productIdType;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getCleared() {
		return cleared;
	}

	public String getDeliverableCurrency() {
		return deliverableCurrency;
	}

	public String getNotional() {
		return notional;
	}

	public Date getExecutionTimestamp() {
		return executionTimestamp;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public String getTradeId() {
		return tradeId;
	}

	protected Report(ReportBuilder reportBuilder) {
		this.mandate = reportBuilder.mandate;
		this.actionType = reportBuilder.actionType;
		this.businessTransactionId = reportBuilder.businessTransactionId;
		this.productId = reportBuilder.productId;
		this.productIdType = reportBuilder.productIdType;
		this.quantity = reportBuilder.quantity;
		this.cleared = reportBuilder.cleared;
		this.deliverableCurrency = reportBuilder.deliverableCurrency;
		this.notional = reportBuilder.notional;
		this.executionTimestamp = reportBuilder.executionTimestamp;
		this.settlementDate = reportBuilder.settlementDate;
		this.tradeId = reportBuilder.tradeId;
	}

	public static class ReportBuilder {
		String mandate;
		String actionType;
		String businessTransactionId;
		String productId;
		String productIdType;
		String quantity;
		String cleared;
		String deliverableCurrency;
		String notional;
		Date executionTimestamp;
		LocalDate settlementDate;
		String tradeId;
		
		public ReportBuilder mandate(String mandate) {
			this.mandate = mandate;
			return this;
		}

		public ReportBuilder actionType(String actionType) {
			this.actionType = actionType;
			return this;
		}

		public ReportBuilder businessTransactionId(String businessTransactionId) {
			this.businessTransactionId = businessTransactionId;
			return this;
		}

		public ReportBuilder productId(String productId) {
			this.productId = productId;
			return this;
		}

		public ReportBuilder productIdType(String productIdType) {
			this.productIdType = productIdType;
			return this;
		}

		public ReportBuilder quantity(String quantity) {
			this.quantity = quantity;
			return this;
		}

		public ReportBuilder cleared(String cleared) {
			this.cleared = cleared;
			return this;
		}

		public ReportBuilder deliverableCurrency(String deliverableCurrency) {
			this.deliverableCurrency = deliverableCurrency;
			return this;
		}

		public ReportBuilder notional(String notional) {
			this.notional = notional;
			return this;
		}

		public ReportBuilder executionTimestamp(Date executionTimestamp) {
			this.executionTimestamp = executionTimestamp;
			return this;
		}

		public ReportBuilder settlementDate(LocalDate settlementDate) {
			this.settlementDate = settlementDate;
			return this;
		}

		public ReportBuilder tradeId(String tradeId) {
			this.tradeId = tradeId;
			return this;
		}

		public Report build() {
			return new Report(this);
		}
	}
}

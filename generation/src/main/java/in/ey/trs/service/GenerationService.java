package in.ey.trs.service;

import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ey.trs.dto.Report;
import in.ey.trs.dto.TrInput;

@Service
public class GenerationService implements RabbitListenerConfigurer {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AccountService accountDetails;
	
	@Autowired
	ProductService productDetails;
	
	@RabbitListener(queues = "${spring.rabbitmq.generation-queue}")
	public void generateReport(TrInput trInput, Message message) throws Exception {
		Boolean eligibilityStatus = (Boolean) Optional.ofNullable(message.getMessageProperties().getHeader("EligibilityStatus")).orElse(Boolean.FALSE);
		if(eligibilityStatus) {
			logger.info("Eligibility Status is True, Proceeding with Generation");
			Report report = generateReport(trInput);
			logger.info("------  Report Generation Successful  ------");
			SubmissionService.writeToCsv(report);
		} else {
			logger.info("Report won't be generated as eligibity validation has failed");
		}
	}

	public Report generateReport(TrInput trInput) throws Exception {
		return new Report.ReportBuilder()
				.mandate("EMIR")
				.actionType(trInput.getTradeState())
				.businessTransactionId(UUID.randomUUID().toString())
				.productId(trInput.getCusip())
				.productIdType("CUSIP")
				.quantity(quantityWithValidations(trInput))
				.cleared("Y")
				.deliverableCurrency(deliverableCurrencyWithValidation(trInput))
				.notional(notionalCalculation(trInput))
				.executionTimestamp(trInput.getExecutionTimestamp())
				.settlementDate(productDetails.maturityDate(trInput.getCusip()))
				.tradeId(trInput.getTradeId())
				.build();
	}

	private String notionalCalculation(TrInput trInput) throws Exception {
		String cusip = trInput.getCusip();
		String p2IssueType = productDetails.issueType(cusip);
		if ("OPTION".equalsIgnoreCase(p2IssueType)) {
			return productDetails.strikePrice(cusip);
		}
		return String.valueOf(trInput.getPrice());
	}

	private String quantityWithValidations(TrInput trInput) throws Exception {
		String quantity = trInput.getQuantity();
		if (quantity == null || quantity.isBlank()) {
			throw new Exception("Quantity TRI is missing or blank");
		} else if (quantity.length() > 20) {
			throw new Exception("Quantity is greater than 20 characters");
		}
		return quantity;
	}

	private String deliverableCurrencyWithValidation(TrInput trInput) throws Exception {
		String cusip = trInput.getCusip();
		String baseCurrency = productDetails.baseCurrency(cusip);
		String quoteCurrency = productDetails.quoteCurrency(cusip);
		return Stream.of(baseCurrency, quoteCurrency).min(Comparator.naturalOrder()).orElse("");
	}

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

	}

}

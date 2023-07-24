package in.ey.trs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import in.ey.trs.dto.TrInput;

@Service
public class ObligationService implements RabbitListenerConfigurer {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AccountService accountDetails;

	private RabbitMqSender rabbitMqSender;

	@Autowired
	public ObligationService(RabbitMqSender rabbitMqSender) {
		this.rabbitMqSender = rabbitMqSender;
	}

	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void validateObligationRules(TrInput trInput, Message message) {
		logger.info("Acquired TrInput Details is.. " + trInput.toString());
		String cusip = trInput.getCusip();
		rabbitMqSender.send(trInput, validateInScopeMarketEligibility(cusip));
	}

	private Boolean validateInScopeMarketEligibility(String cusip) {
		if (accountDetails.isMultiLateralTradingFacility(cusip) || accountDetails.isOrganisedTradingFacility(cusip)
				|| accountDetails.isSwapExecutionFacility(cusip)) {
			return false;
		}
		return true;
	}

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

	}

}

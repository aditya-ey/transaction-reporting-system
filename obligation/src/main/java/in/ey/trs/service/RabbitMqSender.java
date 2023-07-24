package in.ey.trs.service;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import in.ey.trs.dto.TrInput;

@Service
public class RabbitMqSender {
	private RabbitTemplate rabbitTemplate;

	@Autowired
	public RabbitMqSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Value("${spring.rabbitmq.generation-exchange}")
	private String exchange;
	@Value("${spring.rabbitmq.generation-routing-key}")
	private String routingkey;

	@Autowired
	@Qualifier("generationResponseQueue")
	Queue generationResponseQueue;
	
	public void send(TrInput trInput, boolean eligibilityStatus) {
		MessagePostProcessor messagePostProcessor = message -> {
			MessageProperties messageProperties = message.getMessageProperties();
			messageProperties.setReplyTo(generationResponseQueue.getName());
			messageProperties.setHeader("EligibilityStatus", eligibilityStatus);
			return message;
		};
		rabbitTemplate.convertAndSend(exchange, routingkey, trInput, messagePostProcessor);
	}
}

package in.ey.trs.service;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Value("${spring.rabbitmq.exchange}")
	private String exchange;
	@Value("${spring.rabbitmq.routing-key}")
	private String routingkey;

	@Autowired
	Queue obligationResponseQueue;
	
	public void send(TrInput trInput) {
		MessagePostProcessor messagePostProcessor = message -> {
			MessageProperties messageProperties = message.getMessageProperties();
			messageProperties.setReplyTo(obligationResponseQueue.getName());
			return message;
		};
		rabbitTemplate.convertAndSend(exchange, routingkey, trInput, messagePostProcessor);
	}
}

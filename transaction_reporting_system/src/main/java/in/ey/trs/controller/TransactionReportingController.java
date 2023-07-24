package in.ey.trs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ey.trs.dto.TrInput;
import in.ey.trs.service.AcquisitionService;
import in.ey.trs.service.RabbitMqSender;

@RestController
@CrossOrigin
public class TransactionReportingController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RabbitMqSender rabbitMqSender;
	
	@Autowired
	public TransactionReportingController(RabbitMqSender rabbitMqSender) {
		this.rabbitMqSender = rabbitMqSender;
	}

	@Autowired
	AcquisitionService acqisitionService;
	
	@Value("${app.message}")
    private String message;
	
	@PostMapping(value = "/acquisition/trInput", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String acquireTrInput(@RequestBody TrInput trInput) {
		TrInput acquiredTrInput = acqisitionService.createTrInput(trInput);
		rabbitMqSender.send(acquiredTrInput);
		return message;
	}

}

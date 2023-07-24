package in.ey.trs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ey.trs.dto.TrInput;
import in.ey.trs.entity.TrInputAcquired;
import in.ey.trs.repository.AcquisitionRepository;

@Service
public class AcquisitionService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AcquisitionRepository acquisitionRepo;
	
	public TrInput createTrInput(TrInput trInput) {
		logger.info("Acquisition request for TrInput {}", trInput);
		TrInputAcquired trInputAcquired = trInput.createEntity();
		trInputAcquired = acquisitionRepo.save(trInputAcquired);
		return TrInput.valueOf(trInputAcquired);
	}
	
}

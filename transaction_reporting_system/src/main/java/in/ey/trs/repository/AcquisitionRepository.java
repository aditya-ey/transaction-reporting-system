package in.ey.trs.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ey.trs.entity.TrInputAcquired;

@Repository
public interface AcquisitionRepository extends JpaRepository<TrInputAcquired, Long> {

	

}

package transport.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import transport.model.StepInfo;

public interface StepJpaRepo extends JpaRepository<StepInfo, Long> {


}

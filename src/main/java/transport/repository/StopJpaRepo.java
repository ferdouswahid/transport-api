package transport.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import transport.model.StopInfo;

public interface StopJpaRepo extends JpaRepository<StopInfo, String> {


}

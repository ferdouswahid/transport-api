package transport.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import transport.model.StopInfo;

public interface StopJpaRepo extends JpaRepository<StopInfo, String> {

    @Query(value = "SELECT count(si) from StopInfo si")
    Integer getCount();

}

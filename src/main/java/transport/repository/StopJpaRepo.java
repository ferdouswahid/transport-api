package transport.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import transport.model.StopInfo;

public interface StopJpaRepo extends JpaRepository<StopInfo, String> {

    @Query(value = "SELECT count(si) from StopInfo si")
    Integer getCount();

    @Query(value=" SELECT si from StopInfo si ",
            countQuery=" SELECT count(si) from StopInfo si ")
    Page<StopInfo> getAllWithPage(Pageable pageable);


}

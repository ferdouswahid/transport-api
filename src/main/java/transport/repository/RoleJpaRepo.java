package transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import transport.model.Role;

public interface RoleJpaRepo extends JpaRepository<Role, Long> {

    @Query(value = "SELECT count(r) from Role r")
    Integer getCount();

    Role getFirstByName(String name);

}

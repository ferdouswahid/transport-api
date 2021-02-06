package transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transport.model.Role;

public interface RoleJpaRepo extends JpaRepository<Role, Long> {

}

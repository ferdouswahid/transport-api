package transport.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import transport.model.UserProfile;
import transport.model.UserRole;

import java.util.Optional;

public interface UserRoleJpaRepo extends JpaRepository<UserRole, Long> {


}

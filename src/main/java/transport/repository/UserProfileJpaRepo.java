package transport.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import transport.model.UserProfile;

import java.util.Optional;

public interface UserProfileJpaRepo extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findFirstByUserName(String username);

}

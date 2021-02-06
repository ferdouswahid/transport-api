package transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import transport.model.UserProfile;
import transport.repository.UserProfileJpaRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;


@SuppressWarnings("Duplicates")
@Service
@Transactional
public class UserProfileService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserProfileJpaRepo userProfileJpaRepo;

    public Optional<UserProfile> getUserInfoByUsername(String username) {

        return userProfileJpaRepo.findFirstByUserName(username);
    }

}

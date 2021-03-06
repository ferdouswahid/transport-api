package transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import transport.model.Role;
import transport.model.UserProfile;
import transport.repository.RoleJpaRepo;
import transport.repository.UserProfileJpaRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@SuppressWarnings("Duplicates")
@Service
@Transactional
public class RoleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    RoleJpaRepo roleJpaRepo;

    public Role save(Role role) {
        try {
            return roleJpaRepo.save(role);
        }catch (Exception e){
            return null;
        }
    }

    public List<Role> getAll() {
        try {
            return roleJpaRepo.findAll();
        }catch (Exception e){
            return null;
        }
    }
    public Integer getCount() {
        try {
            return roleJpaRepo.getCount();
        }catch (Exception e){
            return 0;
        }
    }

    public Role getRoleByName(String roleName) {

        return roleJpaRepo.getFirstByName(roleName);
    }
}

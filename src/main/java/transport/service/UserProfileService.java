package transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transport.dto.RegistrationDto;
import transport.dto.ResponseMessageDto;
import transport.model.Role;
import transport.model.StopInfo;
import transport.model.UserProfile;
import transport.model.UserRole;
import transport.repository.UserProfileJpaRepo;
import transport.repository.UserRoleJpaRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

import static transport.model.QUserProfile.userProfile;


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

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleJpaRepo userRoleJpaRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<UserProfile> getUserInfoByUsername(String username) {

        return userProfileJpaRepo.findFirstByUserName(username);
    }

    public ResponseMessageDto<UserProfile> save(RegistrationDto registrationDto) {
        try {
            System.out.println("registrationDto req: "+ registrationDto);

            Optional<UserProfile> userProfileDb = userProfileJpaRepo.findFirstByUserName(registrationDto.getUserName());
            if(userProfileDb.isPresent()){
                return new ResponseMessageDto<>(false,"User Already exist.",null);
            }

            UserProfile userProfileSaved =userProfileJpaRepo.save(
                    new UserProfile(registrationDto.getName(),registrationDto.getUserName(),passwordEncoder.encode(registrationDto.getPassword()))
            );

            Role role=roleService.getRoleByName("ROLE_USER");
            userRoleJpaRepo.save(new UserRole(userProfileSaved, role));

            return new ResponseMessageDto<>(true,"Saved successfully.",userProfileSaved);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessageDto<>(false,e.getMessage(),null);
        }
    }

    public UserProfile getDetail(Long userId) {
        try {

            return userProfileJpaRepo.findById(userId).orElse(null);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

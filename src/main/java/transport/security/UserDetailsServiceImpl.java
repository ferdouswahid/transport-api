package transport.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transport.model.UserProfile;
import transport.repository.RoleJpaRepo;
import transport.service.UserProfileService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired  private JdbcTemplate jdbcTemplate;
    @Autowired  private PasswordEncoder passwordEncoder;
    @Autowired private UserProfileService userProfileService;
    @Autowired private RoleJpaRepo roleJpaRepo;


    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Map<String,Object>> userList=jdbcTemplate.query(" SELECT id,user_name,password FROM UserProfile up WHERE up.user_name=? ",
                new Object[] {username}, (resultSet, rowNum) ->{
                    Map<String,Object> u=new HashMap<>();
                    u.put("userId",resultSet.getLong("id"));
                    u.put("username",resultSet.getString("user_name"));
                    u.put("password",resultSet.getString("password"));
                    return u;
                });
        if(userList.isEmpty()){  throw new UsernameNotFoundException("Incorrect username");  }

        List<String> roleList=jdbcTemplate.query(" SELECT r.name FROM UserRole ur inner join Role r on ur.role_id = r.id WHERE ur.userProfile_id=? ",
                new Object[] {userList.get(0).get("userId")}, (resultSet, rowNum) ->{ return resultSet.getString("name");         });

        if(roleList.isEmpty()){
            return new UserDetailsImpl((Long)userList.get(0).get("userId"),(String)userList.get(0).get("username"),(String)userList.get(0).get("password"),null);
        }
        else{
            return new UserDetailsImpl((Long)userList.get(0).get("userId"),(String)userList.get(0).get("username"),(String)userList.get(0).get("password"),
                    Arrays.asList(new SimpleGrantedAuthority(  roleList.stream().collect(Collectors.joining(",")))));
        }

    }


}

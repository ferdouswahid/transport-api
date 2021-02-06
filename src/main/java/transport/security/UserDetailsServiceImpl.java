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
        List<Map<String,Object>> userList=jdbcTemplate.query(" SELECT user_id,username,password FROM user_profile up WHERE up.username=? ",
                new Object[] {username}, (resultSet, rowNum) ->{
                    Map<String,Object> u=new HashMap<>();
                    u.put("user_id",resultSet.getLong("user_id"));
                    u.put("username",resultSet.getString("username"));
                    u.put("password",resultSet.getString("password"));
                    return u;
                });
        if(userList.isEmpty()){  throw new UsernameNotFoundException("Incorrect username");  }

        List<String> roleList=jdbcTemplate.query(" SELECT role_name FROM user_role ur WHERE ur.user_id=? ",
                new Object[] {userList.get(0).get("user_id")}, (resultSet, rowNum) ->{ return resultSet.getString("role_name");         });

        if(roleList.isEmpty()){
            return new UserDetailsImpl((Long)userList.get(0).get("user_id"),(String)userList.get(0).get("username"),(String)userList.get(0).get("password"),null);
        }
        else{
            return new UserDetailsImpl((Long)userList.get(0).get("user_id"),(String)userList.get(0).get("username"),(String)userList.get(0).get("password"),
                    Arrays.asList(new SimpleGrantedAuthority(  roleList.stream().collect(Collectors.joining(",")))));
        }

    }


}

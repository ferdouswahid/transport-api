package transport.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.google.gson.Gson;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import transport.dto.StopDto;
import transport.model.Role;
import transport.model.StopInfo;
import transport.model.UserProfile;
import transport.model.UserRole;
import transport.repository.UserProfileJpaRepo;
import transport.repository.UserRoleJpaRepo;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InitDBService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserProfileJpaRepo userProfileJpaRepo;

    @Autowired
    private UserRoleJpaRepo userRoleJpaRepo;

    @Autowired
    private StopService stopService;

    @Value("${stops.resource.url}")
    private String STOPS_RESOURCE_URL;


    @PostConstruct
    public void init() {

        System.out.println("PostConstruct init call..");
        try {

            if(roleService.getCount()==0){
                System.out.println("Saving role user...");

                //save role
                Role roleR1=roleService.save(new Role("ROLE_SUPERADMIN"));
                Role roleR2=roleService.save(new Role("ROLE_ADMIN"));
                Role roleR3=roleService.save(new Role("ROLE_USER"));

                //save user
                UserProfile userProfile=userProfileJpaRepo.save(new UserProfile("Super Admin", "superadmin@gmail.com", "11111111", "superadmin", passwordEncoder.encode("$123_superadmin"), true));

                //save user role
                userRoleJpaRepo.save(new UserRole(userProfile, roleR1));
            }

            if(stopService.getCount()==0){
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<?> requestEntity = new HttpEntity<>(headers);

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                ObjectMapper objectMapper = new ObjectMapper();
                String retStr = restTemplate.getForObject(STOPS_RESOURCE_URL, String.class, requestEntity);

                StopDto stopDto = objectMapper.readValue(retStr, new TypeReference<StopDto>() {});
                //System.out.println("stopDto: " + stopDto);
                System.out.println("stopDto.getStops().size(): " + stopDto.getStops().size());
                for (StopInfo stopInfo : stopDto.getStops()) {
                    System.out.println(stopInfo);
                    break;
                }
                boolean resp = stopService.saveAll(stopDto.getStops());
                System.out.println("resp: " + resp);

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

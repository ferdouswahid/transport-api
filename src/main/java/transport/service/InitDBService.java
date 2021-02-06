package transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InitDBService {

    @Autowired  private JdbcTemplate jdbcTemplate;
    @Autowired  private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        System.out.println("PostConstruct init call..");


    }

    
}

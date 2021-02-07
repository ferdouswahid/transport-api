package transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import transport.model.StopInfo;
import transport.repository.StopJpaRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("Duplicates")
@Service
@Transactional
public class StopService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    StopJpaRepo stopJpaRepo;

    public StopInfo save(StopInfo stopInfo) {
        try {
            return stopJpaRepo.save(stopInfo);
        }catch (Exception e){
            return null;
        }
    }

    public boolean saveAll(List<StopInfo> stopInfoList) {
        try {
             stopJpaRepo.saveAll(stopInfoList);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<StopInfo> getAll() {
        try {
            return stopJpaRepo.findAll();
        }catch (Exception e){
            return null;
        }
    }
    public Integer getCount() {
        try {
            return stopJpaRepo.getCount();
        }catch (Exception e){
            return 0;
        }
    }

    public StopInfo getOneById(String id) {
        try {
            return stopJpaRepo.findById(id).orElse(null);
        }catch (Exception e){
            return null;
        }
    }
}

package transport.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import transport.dto.StepDto;
import transport.model.StepInfo;
import transport.repository.StepJpaRepo;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("Duplicates")
@Service
@Transactional
public class StepService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    StepJpaRepo stepJpaRepo;

    @Value("${step.resource.url}")
    private String STEP_RESOURCE_URL;


    public List<?> getAllStepById(String stopId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> requestEntity = new HttpEntity<>(headers);

            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            ObjectMapper objectMapper = new ObjectMapper();
            String retStr = restTemplate.getForObject(STEP_RESOURCE_URL + stopId, String.class, requestEntity);

            List<StepDto> stepDtoList = objectMapper.readValue(retStr, new TypeReference<List<StepDto>>() {
            });
            System.out.println("stepDtoList: " + stepDtoList);
            System.out.println("stepDtoList.size(): " + stepDtoList.size());
            for (StepDto stepInfo : stepDtoList) {
                System.out.println(stepInfo);
                break;
            }
            return stepDtoList;

        } catch (Exception e) {
            return null;
        }
    }
}

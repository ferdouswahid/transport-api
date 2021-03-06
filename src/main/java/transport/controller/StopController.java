package transport.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import transport.dto.ResponseMessageDto;
import transport.model.StopInfo;
import transport.service.StopService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/stop")
@RestController
@CrossOrigin
@SuppressWarnings("Duplicates")
public class StopController {

    @Autowired
    StopService stopService;

    @GetMapping
    public @ResponseBody ResponseEntity<?> getAll() {
        try {

            List<StopInfo> stopInfoList= stopService.getAll();
                return ResponseEntity.ok(new ResponseMessageDto<>(true,"Data retrieve successfully.", stopInfoList));
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessageDto<>(false,"Failed to retrieve the data. Please try again.", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package transport.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transport.dto.ResponseMessageDto;
import transport.model.StepInfo;
import transport.model.StopInfo;
import transport.service.StepService;


@RequestMapping("/api/v1/step")
@RestController
@CrossOrigin
@SuppressWarnings("Duplicates")
public class StepController {

    @Autowired
    StepService stepService;

    @GetMapping(value = "/getOne")
    public @ResponseBody
    ResponseEntity<?> getOneById(@RequestParam(value = "id") String id) {
        try {

            return ResponseEntity.ok(new ResponseMessageDto<>(true,"Data retrieve successfully.", stepService.getAllStepById(id)));
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessageDto<>(false,"Failed to retrieve the data. Please try again.", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

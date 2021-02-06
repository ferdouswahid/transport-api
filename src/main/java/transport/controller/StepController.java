package transport.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import transport.service.StepService;


@RequestMapping("/api/v1/step")
@RestController
@CrossOrigin
@SuppressWarnings("Duplicates")
public class StepController {

    @Autowired
    StepService stepService;

}

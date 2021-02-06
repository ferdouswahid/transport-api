package transport.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import transport.service.StopService;

@RequestMapping("/api/v1/stop")
@RestController
@CrossOrigin
@SuppressWarnings("Duplicates")
public class StopController {

    @Autowired
    StopService stopService;

}

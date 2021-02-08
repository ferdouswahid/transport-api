package transport.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import transport.dto.RegistrationDto;
import transport.dto.ResponseMessageDto;
import transport.model.StopInfo;
import transport.model.UserProfile;
import transport.security.UserDetailsImpl;
import transport.service.UserProfileService;

import java.util.List;


@RequestMapping("/api/v1/userProfile")
@RestController
@CrossOrigin
@SuppressWarnings("Duplicates")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @PostMapping(value = "/registration")
    public @ResponseBody
    ResponseEntity<?> registration(@RequestBody RegistrationDto registrationDto) {


            ResponseMessageDto<?> rmd=userProfileService.save(registrationDto);
            if(rmd.getStatus()){
                return ResponseEntity.ok(rmd);
            }else{
                return new ResponseEntity<>(rmd, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping(value = "/getDetail")
    public @ResponseBody ResponseEntity<?>  getDetail() throws Exception {

        try {

            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("userDetailsImpl.getUserId()");
            System.out.println(userDetailsImpl.getUserId());

            UserProfile userProfile=userProfileService.getDetail(userDetailsImpl.getUserId());
            return ResponseEntity.ok(new ResponseMessageDto<>(true,"Data retrieve successfully.", userProfile));
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessageDto<>(false,"Failed to retrieve the data. Please try again.", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

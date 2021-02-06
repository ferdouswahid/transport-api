package transport.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import transport.security.JwtUtil;
import transport.security.UserDetailsImpl;
import transport.security.UserDetailsServiceImpl;


import java.io.Serializable;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest ) throws Exception {
        //System.out.println("authenticate calling");
        //System.out.println(authenticationRequest.getUsername());
        //System.out.println(authenticationRequest.getPassword());
        try {
            authenticationManager.authenticate(  new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())  );
            //System.out.println("username and password is correct");

            return ResponseEntity.ok(new AuthenticationResponse(HttpStatus.OK.value(),"Authenticated successfully.", true, jwtTokenUtil.generateToken(
                    userDetailsService.loadUserByUsername(authenticationRequest.getUsername()))));
        }
        catch (BadCredentialsException e) {
            //System.out.println("password is wrong");
            //throw new Exception("Incorrect username or password", e);
            return ResponseEntity.ok(new AuthenticationResponse(HttpStatus.OK.value(),"Authentication Failed. Username or password is incorrect.", false, null));
        }
        catch (UsernameNotFoundException e) {
            //System.out.println("username is wrong");
            //throw new Exception("Incorrect username or password", e);
            return ResponseEntity.ok(new AuthenticationResponse(HttpStatus.OK.value(),"Authentication Failed. Username or password is incorrect.", false, null));
        }

    }


    @Data  @NoArgsConstructor @AllArgsConstructor
    public static class AuthenticationRequest implements Serializable {
        private String username;
        private String password;
    }
    @AllArgsConstructor  @Getter
    public static class AuthenticationResponse implements Serializable {
        private final int httpCode;
        private final String message;
        private final Boolean status;
        private final String jwtToken;
    }

}

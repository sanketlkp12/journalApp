package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.model.User;
import net.engineeringdigest.journalApp.service.UserDetailServiceImpl;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;
    
    /*Creating user using SpringSecurity*/
    @PostMapping("/createUser")
    public ResponseEntity<User> creatUser(@RequestBody User entry) {
        try {
            userService.saveNewUser(entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    /*Creating user using JWT*/
    @PostMapping("/signup")
    public void signup(@RequestBody User user){
         userService.saveNewUser(user);
    }

    /*Authenticating user using JWT*/
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
                    return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
//            return new ResponseEntity<>("Authentication successful", HttpStatus.OK);
//        } catch (BadCredentialsException e) {
//            log.error("Authentication failed", e);
//            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
//        }
//    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
//
//            // Ensure the user is loaded correctly
//            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());
//
//            // Check if the userDetails are correct
//            log.info("Authenticated user: {}", userDetails.getUsername());
//
//            // Generate the JWT token
//            String jwt = jwtUtil.generateToken(userDetails.getUsername());
//            return new ResponseEntity<>(jwt, HttpStatus.OK);
//        } catch (Exception e) {
//            log.error("Authentication failed: Incorrect username or password", e);
//            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
//        }
//    }

}

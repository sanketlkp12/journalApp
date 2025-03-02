package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.model.User;
import net.engineeringdigest.journalApp.model.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    WeatherService weatherService;

    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.getAllEntries();
    }


    @GetMapping("/id/{myId}")
    public ResponseEntity<User> getById(@PathVariable ObjectId myId) {
        Optional<User> journalEntry = userService.getById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateEntry(@RequestBody User update) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(update.getUserName());
            userInDb.setPassword(update.getPassword());
            userService.saveNewUser(userInDb);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<User> deleteById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/externalApi")
    public ResponseEntity<?> getWeather() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greetings = "";
        if(weatherResponse!=null){
          greetings = ",weather feels like "+weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+greetings,HttpStatus.OK);

    }


}

package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.model.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /*Initiate logging for UserService class*/
   // private  static final Logger logger = LoggerFactory.getLogger(UserService.class); //Commented because of @Slf4j

    public void saveUser(User entry) {
        userRepo.save(entry);
    }

    public boolean saveNewUser(User entry) {
        try {
            entry.setPassword(passwordEncoder.encode(entry.getPassword()));
            entry.setRoles(Arrays.asList("USER"));
            userRepo.save(entry);
            return true;
        } catch (Exception e) {
            //logger.error("Error occurred for {} ",entry.getUserName(), e);
            //log.error("Error occurred for {} ",entry.getUserName(), e);
            log.debug("Error occurred for debug");
            log.error("Error occurred for error level");
            log.warn("Error occurred for warn ");
            log.trace("Error occurred for trace");
            return false;
        }
    }

    public void saveAdmin(User entry) {
        entry.setPassword(passwordEncoder.encode(entry.getPassword()));
        entry.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(entry);
    }

    public List<User> getAllEntries() {
        return userRepo.findAll();
    }

    public Optional<User> getById(ObjectId id) {
        return userRepo.findById(id);
    }

 public void  deleteById(ObjectId id){
        userRepo.deleteById(id);
 }

 public User findByUserName(String userName){

    return userRepo.findByUserName(userName);
 }


}

package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.model.JournalEntry;
import net.engineeringdigest.journalApp.model.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private  UserService userService;

    /*Initiate logging for this class*/
    private  static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional
    public void saveJournalEntry(JournalEntry entry, String userName) {
        try{
            User user = userService.findByUserName(userName);
            entry.setDate(LocalDateTime.now());
            JournalEntry save = journalEntryRepo.save(entry);
            user.getJournalEntries().add(save);
         //   user.setUserName(null);
            userService.saveUser(user);
        }
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the en0000try.",e);
        }

    }

    public void updateEntry(JournalEntry entry) {
        journalEntryRepo.save(entry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    @Transactional
     public boolean  deleteById(ObjectId id, String userName){
        boolean removed =false;
        try {
            User user = userService.findByUserName(userName);
            removed=user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            logger.error("Error ",e);
            throw new RuntimeException("An error occurred while deleting an entry.",e);
        }
  return  removed;
 }
//
// public JournalEntry updateEntry(ObjectId myId, JournalEntry newEntry){
//    Optional<JournalEntry> old = journalEntryRepo.findById(myId);
//    return null;
// }


}

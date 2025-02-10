//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.model.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//private Map<Long, JournalEntry> journalEntries = new HashMap();
//  public JournalEntryController() {
//    journalEntries.put(1L, new JournalEntry(1L, "Morning", "Better day"));
//    journalEntries.put(2L, new JournalEntry(2L, "Noon", "Lazy day"));
//  }
//
//@GetMapping()
//public List<JournalEntry> getAll(){
//  return new ArrayList<>(journalEntries.values());
//}
//
//  @GetMapping("/id/{myId}")
//  public JournalEntry getById(@PathVariable Long myId){
//    return journalEntries.get(myId);
//  }
//
//  @PostMapping("/create")
//  public boolean creatEntry(@RequestBody JournalEntry entry){
//      journalEntries.put(entry.getId(), entry);
//      return true;
//  }
//
//  @PutMapping("/update/{myId}")
//  public JournalEntry updateEntry(@PathVariable Long myId ,@RequestBody JournalEntry entry){
//    return journalEntries.put(myId, entry);
//
//  }
//  @DeleteMapping("/id/{myId}")
//  public JournalEntry  deleteById(@PathVariable Long myId){
//    return journalEntries.remove(myId);
//  }
//
//
//}

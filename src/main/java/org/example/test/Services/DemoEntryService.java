package org.example.test.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.bson.types.ObjectId;
import org.example.test.Respository.DemoEntryRepository;
import org.example.test.entity.DemoEntry;
import org.example.test.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DemoEntryService {

   @Autowired
   private DemoEntryRepository demoEntryRepository;
   @Autowired
   private UserService userService;

   private static final Logger logger= LoggerFactory.getLogger(DemoEntryService.class);
   @Transactional
   public void saveEntry(DemoEntry demoEntry, String username) {
      User user=userService.findByUserName(username);
      demoEntryRepository.save(demoEntry);
      DemoEntry entry=demoEntryRepository.save(demoEntry);
      user.getDemoEntries().add(entry);

      userService.saveEntry(user);
   }
   public void saveEntry(DemoEntry demoEntry) {

      demoEntryRepository.save(demoEntry);
      DemoEntry entry=demoEntryRepository.save(demoEntry);

   }

   public List<DemoEntry> getAll() {
      System.out.println("Fetching all entries...");
      return demoEntryRepository.findAll();
   }

   public DemoEntry getById(ObjectId id) {
      return demoEntryRepository.findById(id).orElse(null);
   }

   @Transactional
   public boolean deleteById(ObjectId id, String username) {
      boolean deleted=false;
      try {
         User user=userService.findByUserName(username);
         boolean remove= user.getDemoEntries().removeIf(entry -> entry.getId().equals(id));
         if(remove) { userService.saveEntry(user);
            demoEntryRepository.deleteById(id);
         deleted=true;}
      }
      catch(Exception e) {
         throw new RuntimeException("Error while deleting entry", e);
      }
return deleted;
   }

   public void updateById(ObjectId id, DemoEntry demoEntry) {
      if (demoEntryRepository.existsById(id)) {
         demoEntry.setId(id); // Ensure ID remains unchanged
         demoEntryRepository.save(demoEntry);
      } else {
         throw new RuntimeException("Entry with ID " + id + " not found.");
      }
   }
  /* public List<DemoEntry> getByUsername(String username) {

   }*/
}

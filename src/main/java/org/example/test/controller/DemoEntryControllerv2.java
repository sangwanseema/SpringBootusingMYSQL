package org.example.test.controller;
import org.bson.types.ObjectId;
import org.example.test.Respository.DemoEntryRepository;
import org.example.test.Services.DemoEntryService;
import org.example.test.Services.UserService;
import org.example.test.entity.DemoEntry;
import org.example.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.example.test.entity.DemoEntry;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;


@RestController
// apply mapping on class
// url look like : localhost:8080/demo/health
//localhost:8080/demo/Entries
@RequestMapping("/demo")

public class DemoEntryControllerv2 {


     @Autowired
     private DemoEntryService demoEntryService;
     @Autowired
     private UserService userService;
    @GetMapping("health")
    public String healthCheck(){
        return "OK";
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEntriesOfUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userService.findByUserName(username);
        List<DemoEntry> all=user.getDemoEntries();
        if(all!=null&&!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?>  createEntry(@RequestBody DemoEntry myEntry)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        try {

            myEntry.setDate(LocalDateTime.now());
            demoEntryService.saveEntry(myEntry,username);
            return  new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e)
        {
   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



    }



    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById( @PathVariable ObjectId id) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
       User user= userService.findByUserName(username);
    List<DemoEntry> collect=   user.getDemoEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
    if(!collect.isEmpty()){
        Optional<DemoEntry> demoEntry = Optional.ofNullable(demoEntryService.getById(id));
        if(demoEntry.isPresent()) {
            return  new ResponseEntity<>(demoEntry.get(), HttpStatus.OK);
        }
    }


        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id)
    {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user= userService.findByUserName(username);

        boolean remove=    demoEntryService.deleteById(id,username);

        if(remove){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  @PutMapping("/updateById/{id}")
    public ResponseEntity<?> UpdateEntryById(@PathVariable ObjectId id,@RequestBody DemoEntry myEntry)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user= userService.findByUserName(username);
        List<DemoEntry> collect=   user.getDemoEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<DemoEntry> demoEntry = Optional.ofNullable(demoEntryService.getById(id));
            if(demoEntry.isPresent()) {
                DemoEntry oldEntry = demoEntry.get();
                oldEntry.setDate(myEntry.getDate());
                oldEntry.setContent(myEntry.getContent());
                oldEntry.setTitle(myEntry.getTitle());
                demoEntryService.saveEntry(oldEntry);
                return new ResponseEntity<>(oldEntry, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

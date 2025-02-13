package org.example.test.controller;
import org.bson.types.ObjectId;
import org.example.test.Respository.UserEntryRepository;
import org.example.test.Services.UserService;
import org.example.test.Services.WeatherService;
import org.example.test.apiResponse.WeatherResponse;
import org.example.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
// apply mapping on class
// url look like : localhost:8080/demo/health
//localhost:8080/demo/Entries
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserEntryRepository userEntryRepository;


    @GetMapping("health")
    public String healthCheck(){
        return "OK";
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<User> all=userService.getAll();
        if(all!=null&&!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    /*
    @GetMapping("getById/{id}")
    public ResponseEntity<?> getUserById( @PathVariable ObjectId id) {
        Optional<User> user = Optional.ofNullable(userService.getById(id));
        if(user.isPresent()) {
            return  new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    *?
     */
    @Autowired
    WeatherService weatherService;
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.findByUserName(username);
            WeatherResponse delhi=weatherService.getWeather("Delhi");
            String greeting="";

              greeting=",weather feels like "+delhi.getMain().getFeelsLike()+"in Delhi";

            return new ResponseEntity<>("Hi "+user.getUserName()+greeting , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

       userEntryRepository.deleteByUserName(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<?> UpdateUser(  @RequestBody User user)
    {
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
         User oldUser =userService.findByUserName(username);
        if(oldUser != null) {

           oldUser.setUserName(user.getUserName());
           oldUser.setPassword(user.getPassword());
           userService.saveNewUser(oldUser);
            return new ResponseEntity<>(oldUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

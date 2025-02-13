package org.example.test.controller;

import org.example.test.Services.SqlUserService;
import org.example.test.Services.UserService;
import org.example.test.entity.SqlUser;
import org.example.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// apply mapping on class
// url look like : localhost:8080/demo/health
//localhost:8080/demo/Entries
@RequestMapping("/public")
public class PublicController
{
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user)
    {
        boolean  status= userService.saveNewUser(user);

        if(status) {
           return  new ResponseEntity<>(user, HttpStatus.CREATED);}
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



    }



    @Autowired
    private SqlUserService sqlUserService;
    @PostMapping("/createsql")
    public SqlUser createUser(@RequestBody SqlUser sqlUser) {
        System.out.println(sqlUser);
        sqlUserService.saveUser(sqlUser);
        return sqlUser;
    }


    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<User> all=userService.getAll();
        if(all!=null&&!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> UpdateUser(@PathVariable String username, @RequestBody User user)
    {


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

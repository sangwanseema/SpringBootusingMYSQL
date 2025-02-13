package org.example.test.controller;

import org.example.test.Services.UserService;
import org.example.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers(){
     List<User> all=  userService.getAll();
     if(all!=null&&!all.isEmpty()){
         return new ResponseEntity<>(all, HttpStatus.OK);
     }
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
 @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdminUser(@RequestBody User user){
        userService.saveAdminuser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
 }
}

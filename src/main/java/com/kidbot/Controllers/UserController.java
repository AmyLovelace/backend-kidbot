package com.kidbot.Controllers;

import com.kidbot.Entities.User;
import com.kidbot.Services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers(){
        List<User>listUser=this.userService.findAllUsers();
        return ResponseEntity.ok(listUser);
    }
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User createdUser =this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        User modifiedUser =this.userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(modifiedUser);
    }

    @GetMapping("/users/search/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long id){
        User user= this.userService.findUserById(id);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        this.userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}

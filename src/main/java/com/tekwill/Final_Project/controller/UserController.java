package com.tekwill.Final_Project.controller;

import com.tekwill.Final_Project.dto.UserDTO;
import com.tekwill.Final_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("api/users/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("api/users/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @PostMapping("api/users/new")
    public ResponseEntity<String> addNewUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
        return ResponseEntity.ok("New user created!");
    }

    @PatchMapping("api/users/update/{userId}")
    public ResponseEntity<String> updateUserData(@PathVariable Integer userId, @RequestBody UserDTO userDTO){
        userService.updateUserData(userId, userDTO);
        return ResponseEntity.ok("User data with ID: " + userId + " was updated!");
    }

    @DeleteMapping("api/users/remove/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User with ID: " + userId + " was deleted!");
    }
}

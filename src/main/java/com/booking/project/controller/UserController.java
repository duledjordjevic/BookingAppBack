package com.booking.project.controller;

import com.booking.project.model.User;
import com.booking.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> getUsers(){
        Collection<User> users = userService.findAll();
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") Long id){
        Optional<User> user = userService.findById(id);
        if(user.isEmpty()){
            return new ResponseEntity<Optional<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        User savedUser = userService.save(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) throws Exception{
        Optional<User> userForUpdate = userService.findById(id);


        if (userForUpdate.isEmpty()){
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userForUpdate.get().copyValues(user);

        return new ResponseEntity<User>(userService.save(userForUpdate.get()), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/{id}/blocked", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> blockUser(@PathVariable Long id){
        userService.block(id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }
}

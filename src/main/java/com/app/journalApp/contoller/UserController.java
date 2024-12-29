package com.app.journalApp.contoller;

import com.app.journalApp.entity.JournalEntry;
import com.app.journalApp.entity.User;
import com.app.journalApp.service.JournalEntryService;
import com.app.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName((user.getUserName()));
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
package rp.springboot.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rp.springboot.journalApp.entities.JournalEntity;
import rp.springboot.journalApp.entities.User;
import rp.springboot.journalApp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping()
    public ResponseEntity<String> updateUser( @RequestBody User updatedUser) {

        var existingUser = userService.findByUsername(updatedUser.getUsername());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        userService.save(existingUser);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
}

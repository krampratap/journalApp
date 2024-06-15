package rp.springboot.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAll();
        if(users!=null){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* Example for user creation
        {
          "username": "Tony",
          "password": "Stark",
          "journalEntities": [
            {
              "id": "666d4e1fd1d318550193f265"
            }
          ]
        }
     */
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            var userCreated = userService.save(user);
            return new ResponseEntity<>(userCreated,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id")
    public ResponseEntity<String> updateUser( @RequestBody User updatedUser) {
        var existingUser = userService.findByUsername(updatedUser.getUsername());
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty() ? updatedUser.getUsername() : existingUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty() ? updatedUser.getPassword() : existingUser.getPassword());
            userService.save(existingUser);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

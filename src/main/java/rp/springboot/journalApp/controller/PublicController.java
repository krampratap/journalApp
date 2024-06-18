package rp.springboot.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rp.springboot.journalApp.clients.QuotesClient;
import rp.springboot.journalApp.entities.User;
import rp.springboot.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuotesClient quotesClient;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
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
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        try {
            var userCreated = userService.save(newUser);
            return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/weather")
    public ResponseEntity<String> getQuotes(){
        return new ResponseEntity<>("Hi, Todays Quote"+quotesClient.getQuotesUrl("happiness").quotesResponse().getCategory(),HttpStatus.OK);
    }
}

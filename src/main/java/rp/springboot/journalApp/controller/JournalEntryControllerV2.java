package rp.springboot.journalApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rp.springboot.journalApp.entities.JournalEntity;
import rp.springboot.journalApp.entities.User;
import rp.springboot.journalApp.service.JournalEntityService;
import rp.springboot.journalApp.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntityService journalEntityService;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Get All journals per user",
            description = "Get all journals per user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All journal details"),
                    @ApiResponse(responseCode = "404", description = "No journals found")
            }
    )
    @GetMapping("/user/{username}")
    public ResponseEntity<List<JournalEntity>> getAllJournalsBy(@PathVariable String username) {
        User user = userService.findByUsername(username);
        List<JournalEntity> journalEntityList = user.getJournalEntities();
        if (!journalEntityList.isEmpty()) {
            return new ResponseEntity<>(journalEntityList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntity> getJournalById(@Parameter(description = "Id", example = "666d4e1fd1d318550193f265") @PathVariable ObjectId id) {
        Optional<JournalEntity> journalEntity = Optional.ofNullable(journalEntityService.getOne(id));
        if (journalEntity.isPresent()) {
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Save journals per user",
            description = "Save journals per user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Saved"),
                    @ApiResponse(responseCode = "404", description = "Error in saving")
            }
    )
    @PostMapping("{username}")
//    @Transactional
    public ResponseEntity<JournalEntity> createJournal(@RequestBody JournalEntity myEntity,@PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            journalEntityService.save(myEntity);
            user.getJournalEntities().addLast(myEntity);
            userService.save(user);
            return new ResponseEntity<>(myEntity, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournal(@Parameter(description = "Id", example = "666d4e1fd1d318550193f265") @PathVariable(name="id") ObjectId id) { //WildCard ? Can return anything as part of Response Entity
        List<User> journalUsers = userService.findUsersBy(journalEntityService.getOne(id));
        journalUsers.forEach(user -> user.getJournalEntities().removeIf(x-> x.getId().equals(id)));
        //If we dont delete for each user... next time when user adds a journal.. spring will see existing id does not have a
        // reference and it will delete it. But we need to delete the reference also while deleting journal.
        journalUsers.forEach(userService::save);
        journalEntityService.deleteJournalBy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntity> updateEntity(@Parameter(description = "Id", example = "666d4e1fd1d318550193f265") @PathVariable ObjectId id, @RequestBody JournalEntity updatedEntity) {
        var entity = journalEntityService.getOne(id);
        if (entity != null) {
            entity.setTitle(updatedEntity.getTitle() != null && !updatedEntity.getTitle().isEmpty() ? updatedEntity.getTitle() : entity.getTitle());
            entity.setContent(updatedEntity.getContent() != null && !updatedEntity.getContent().isEmpty() ? updatedEntity.getContent() : entity.getContent());
            journalEntityService.save(entity);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

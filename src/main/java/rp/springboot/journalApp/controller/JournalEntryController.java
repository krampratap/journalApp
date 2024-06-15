package rp.springboot.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import rp.springboot.journalApp.entities.JournalEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<ObjectId, JournalEntity > journalEntityMap = new HashMap<>();

    @GetMapping()
    public List<JournalEntity> getAll(){
        return new ArrayList<>(journalEntityMap.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntity myEntity){
        journalEntityMap.put(myEntity.getId(),myEntity);
        return true;
    }

    @GetMapping("/id/{id}")
    public JournalEntity getById(@PathVariable long id){
        return journalEntityMap.get(id);
    }
}

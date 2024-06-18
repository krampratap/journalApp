package rp.springboot.journalApp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import rp.springboot.journalApp.entities.User;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getSustainabilityUsers(){
        Query query= new Query();
        query.addCriteria(Criteria.where("username").is("Ram"));
        query.addCriteria(Criteria.where("value").ne("Tony")); // Not equal
        query.addCriteria(Criteria.where("value").lt("Black")); // Less than
        List<User> users = mongoTemplate.find(query,User.class);
        return users;
    }
}

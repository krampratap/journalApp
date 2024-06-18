package rp.springboot.journalApp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rp.springboot.journalApp.entities.JournalEntity;
import rp.springboot.journalApp.entities.User;
import rp.springboot.journalApp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteJournalBy(ObjectId id) {
        userRepository.deleteById(id);
    }

    public List<User> findUsersBy(JournalEntity journalEntity){
        return userRepository.findByJournalEntities(journalEntity);
    }
}

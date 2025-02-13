package org.example.test.Services;

import org.bson.types.ObjectId;
import org.example.test.Respository.UserEntryRepository;
import org.example.test.entity.DemoEntry;
import org.example.test.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
 private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserEntryRepository userEntryRepository;

    public void saveEntry(User user) {
        userEntryRepository.save(user);
    }
    private static final Logger logger= LoggerFactory.getLogger(UserService.class);
    public boolean saveNewUser(User user) {

        try{
            if (userEntryRepository.findByUserName(user.getUserName()) != null) {
                logger.info("hahahaha");
                logger.debug("hahahaha");
                logger.trace("hahahaha");
                logger.warn("hahahaha");
                logger.error("hahahaha");
                return false; // User already exists
            }

            user.setPassword(passwordEncoder.encode(user.getPassword())); // Ensure password is hashed
            user.setRoles(Arrays.asList("user"));
            logger.info("hahahaha");
            userEntryRepository.save(user);
            return true;
        }
        catch(Exception e){

            return false;
        }

    }


    public List<User> getAll() {
        System.out.println("Fetching all entries...");
        return userEntryRepository.findAll();
    }

    public User getById(ObjectId id) {
        return userEntryRepository.findById(id).orElse(null);
    }

    public void deleteById(ObjectId id) {
        userEntryRepository.deleteById(id);
    }

    public void updateById(ObjectId id, User user) {
        if (userEntryRepository.existsById(id)) {
            user.setId(id); // Ensure ID remains unchanged
            userEntryRepository.save(user);
        } else {
            throw new RuntimeException("Entry with ID " + id + " not found.");
        }
    }
    public void saveAdminuser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Ensure password is hashed
        user.setRoles(Arrays.asList("user","ADMIN"));
        userEntryRepository.save(user);
    }
    public User findByUserName(String username) {
        return userEntryRepository.findByUserName(username);
    }
}

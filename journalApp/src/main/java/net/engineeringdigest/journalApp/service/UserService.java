package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }catch (Exception e) {
            return false;
        }

    }
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);

    }
//public boolean saveAdmin(User user) {
//    user.setRoles(Arrays.asList("ADMIN"));
//    user.setPassword(passwordEncoder.encode(user.getPassword()));
//    userRepository.save(user);
//    return true;
//}


    public void saveUser(User user) {

        userRepository.save(user);

    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    //controller ---> service ---> repository
    public Optional<User> findById(ObjectId id) {

        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
//        User user = userRepository.findByUserName(userName);
//     user.getJournalEntries().removeIf(x -> x.getId().equals(id));
//     userService.saveEntry(user);
        return userRepository.findByUserName(userName);
    }
}

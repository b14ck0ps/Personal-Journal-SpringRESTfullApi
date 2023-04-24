package service;

import domain.User;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class UserService {
    private final Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(User user) {
        try {
            userRepository.save(user);
            return true;

        } catch (Exception e) {
            logger.warning(e.getMessage());
            return false;
        }
    }


    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }


    public User getByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
            return null;
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
}

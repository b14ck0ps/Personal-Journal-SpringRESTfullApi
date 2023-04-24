package service;

import DTOs.LoginDto;
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
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    public boolean loginUser(LoginDto user) {
        try {
            User userFromDb = userRepository.findByUsername(user.getUsername());
            if (userFromDb != null) {
                return userFromDb.getPassword().equals(user.getPassword());
            }
            return false;
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(int id) {
        try {
            userRepository.delete(userRepository.findById(id));
            return true;
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            userRepository.update(user);
            return true;
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return false;
        }
    }

    public User getUserById(int id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
}

package service;

import DTOs.LoginDto;
import domain.User;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
            if (!isImageValid(user.getImage())) user.setImage(defaultImage);
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
            if (!isImageValid(user.getImage())) user.setImage(defaultImage);
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

    private boolean isImageValid(String url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            logger.warning(e.getMessage());
            return false;
        }
    }

    private final String defaultImage = "https://i.imgur.com/qhLBsxz.png";
}

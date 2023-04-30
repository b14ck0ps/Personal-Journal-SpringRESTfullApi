package Security;

import domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null) {
            logger.warning("User not found: " + username);
            throw new UsernameNotFoundException("User not found: " + username);
        }
        try {
            List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        } catch (Exception e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
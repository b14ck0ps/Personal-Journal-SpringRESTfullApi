package controller;

import domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?>  getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?>  getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getByUsername(username));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?>  deleteUserById(@PathVariable int id) {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

    @PutMapping("/user")
    public ResponseEntity<?>  updateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (userService.updateUser(user)) {
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().body(false);
    }


}

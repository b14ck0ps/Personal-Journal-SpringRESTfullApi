package controller;

import DTOs.LoginDto;
import domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?>  registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (userService.registerUser(user)) {
            return ResponseEntity.created(null).body(true);
        }
        return ResponseEntity.badRequest().body(false);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (userService.loginUser(user)) {
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().body(false);
    }

    @GetMapping("/all")
    public ResponseEntity<?>  getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<?>  getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getByUsername(username));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>  deleteUserById(@PathVariable int id) {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

    @PutMapping("/update")
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

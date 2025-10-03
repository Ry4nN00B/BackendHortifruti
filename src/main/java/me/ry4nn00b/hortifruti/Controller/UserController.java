package me.ry4nn00b.hortifruti.Controller;

import me.ry4nn00b.hortifruti.Model.UserModel;
import me.ry4nn00b.hortifruti.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //Register Method
    @PostMapping("/registrar")
    public ResponseEntity<UserModel> register(@RequestBody UserModel user){
        return ResponseEntity.ok(userService.register(user));
    }

    //Login Method
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Optional<UserModel> user = userService.authentic(email, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(401).body("Email ou senha inválidos!");
    }

    //Method's after logging
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable String userId){
        return userService.findById(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable String userId){
        userService.deleteId(userId);
        return ResponseEntity.noContent().build();
    }



}

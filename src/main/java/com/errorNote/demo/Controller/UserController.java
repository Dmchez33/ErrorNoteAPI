package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.User;
import com.errorNote.demo.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    final private UserService userService;

    @PostMapping("/creer_compte")
    public User CreerCompte(@RequestBody User user){
        return userService.CreerCompte(user);
    }

    @PutMapping("/modifier_compte/{iduser}")
    public User modifierCompte(Long idUser, User user)
    {
        return userService.modifierCompte(idUser, user);
    }
}

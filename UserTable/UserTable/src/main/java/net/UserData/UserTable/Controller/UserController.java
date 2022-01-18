package net.UserData.UserTable.Controller;


import net.UserData.UserTable.Service.UserService;
import net.UserData.UserTable.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> GetAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public String GetById(@PathVariable String id) {
        if(userService.searchByUser(id)){
            return userService.getById(id);
        }
        else{
            return "User not found";
        }
    }

    @PostMapping
    public String CreateUser(@RequestBody User user){
        if(userService.checkUser(user)) {
            return "User Already exists";
        }
        else {
            userService.createUser(user);
            return "user saved";
        }
    }
    @PutMapping("{id}")
    public String UpdateUser(@RequestBody User user,@PathVariable String id) {
        if(userService.searchByUser(id)) {
            userService.updateUser(user, id);
            return "Updated";
        }
        else {
            return "User not found";
        }
    }
    @DeleteMapping("{id}")
    public String DeleteUser(@PathVariable String id){
        if(userService.searchByUser(id)) {
            userService.deleteUser(id);
            return "deleted";
        }
        else {
            return "User not found";
        }

    }
}

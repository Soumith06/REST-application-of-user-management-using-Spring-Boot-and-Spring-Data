package net.UserData.UserTable.Controller;

//include exception handling
import net.UserData.UserTable.Service.UserService;
import net.UserData.UserTable.exceptions.UserAlreadyExistsException;
import net.UserData.UserTable.exceptions.UserNotFoundException;
import net.UserData.UserTable.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            User newUser=userService.addUser(user);
            return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException userAlreadyExistsException){
            return new ResponseEntity<String>(userAlreadyExistsException.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> listOfAllUsers = userService.getAllUsers();
        return new ResponseEntity<List<User>>(listOfAllUsers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){

        try{
            User User=userService.getOneUser(id);
            return new ResponseEntity<User>(User,HttpStatus.OK);
        }
        catch (UserNotFoundException userNotFoundException){
            return new ResponseEntity<String>(userNotFoundException.getMessage(),HttpStatus.CONFLICT);
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user){
        User updatedUser=userService.updateUser(user,id);
        return new ResponseEntity<User>(updatedUser,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<String>("user deleted",HttpStatus.ACCEPTED);
    }
}

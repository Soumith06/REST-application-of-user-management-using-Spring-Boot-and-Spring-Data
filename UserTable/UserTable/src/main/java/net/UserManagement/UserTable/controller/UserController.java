package net.UserManagement.UserTable.controller;

import net.UserManagement.UserTable.exception.ResourceNotFoundExecption;
import net.UserManagement.UserTable.model.User;
import net.UserManagement.UserTable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }


    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundExecption("User not exist with id:"+ id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id,@RequestBody User UserUpdatingDetails){
        User updateUser= userRepository.findById(id).orElseThrow(()->new ResourceNotFoundExecption("User not exist with id:"+ id));
        updateUser.setUserName(UserUpdatingDetails.getUserName());
        updateUser.setFirstName(UserUpdatingDetails.getFirstName());
        updateUser.setLastName(UserUpdatingDetails.getLastName());
        updateUser.setMobileNumber(UserUpdatingDetails.getMobileNumber());
        updateUser.setEmailID(UserUpdatingDetails.getEmailID());
        updateUser.setAddress1(UserUpdatingDetails.getAddress1());
        updateUser.setAddress2(UserUpdatingDetails.getAddress2());

        userRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id){
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundExecption("User not exist with id:"+ id));

        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

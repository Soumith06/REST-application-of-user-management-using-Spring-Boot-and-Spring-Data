package net.UserData.UserTable.Service;

import net.UserData.UserTable.Repository.UserRepository;
import net.UserData.UserTable.exceptions.UserAlreadyExistsException;
import net.UserData.UserTable.exceptions.UserNotFoundException;
import net.UserData.UserTable.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean validateUser(User user){
        List<User> usersList=userRepository.findAll();
        for(User existingUser:usersList){
            if(user.getUserName()==existingUser.getUserName() || user.getEmailID()==existingUser.getEmailID() || user.getMobileNumber()==existingUser.getMobileNumber()){
                return true;
            }
        }
        return false;
    }
    public User addUser(User user) {
        if(validateUser(user)){
            throw  new UserAlreadyExistsException("UserAlreadyExists");
        }
        else{
            return userRepository.save(user);
        }
    }

    public User getOneUser(Long id) {
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException("User Not Found");
        }
        else{
            return userRepository.findById(id).get();
        }

    }

    public void deleteUser(Long id) {

        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException("User NotFound");
        }
        else{
            userRepository.deleteById(id);
        }

    }

    public User updateUser(User user,Long id) {
        List<User> usersList=userRepository.findAll();
        for(User existingUser:usersList){
            if(user.getUserName().equals(existingUser.getUserName()) || user.getEmailID().equals(existingUser.getEmailID()) || user.getMobileNumber().equals(existingUser.getMobileNumber())){
                throw new UserAlreadyExistsException("UserAlreadyExists");
            }
        }
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException("UserNotFound");
        }
        else{
            User existingUser=userRepository.findById(id).get();
            existingUser.setUserName(user.getUserName());
            existingUser.setAddress1(user.getAddress1());
            existingUser.setAddress2(user.getAddress2());
            existingUser.setEmailID(user.getEmailID());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setMobileNumber(user.getMobileNumber());

            return existingUser;
        }

    }
}

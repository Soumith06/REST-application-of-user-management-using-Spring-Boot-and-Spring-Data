package net.UserData.UserTable.Service;

import net.UserData.UserTable.Repository.UserRepository;
import net.UserData.UserTable.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public boolean searchByUser(String id){
        return userRepository.existsById(id);
    }
    public boolean checkUser(User user){
        List<User>list=userRepository.findAll();
        for (User existingUser:list){
            if(user.getMobileNumber()==existingUser.getMobileNumber() || user.getEmailID()==existingUser.getEmailID() || user.getMobileNumber()==existingUser.getMobileNumber()){
                return true;
            }
        }
        return false;
    }
    public String getById(String id){
        User user=userRepository.findById(id).get();
        return user.getUserName()+"\n"+user.getEmailID()+"\n"+user.getMobileNumber();
    }
    public void createUser(User user){
        userRepository.save(user);
    }
    public void updateUser(User user,String id){
        userRepository.save(user);
    }
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }


}

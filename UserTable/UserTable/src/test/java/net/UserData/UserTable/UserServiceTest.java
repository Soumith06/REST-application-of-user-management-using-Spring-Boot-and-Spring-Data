package net.UserData.UserTable;

import net.UserData.UserTable.Repository.UserRepository;
import net.UserData.UserTable.Service.UserService;
import net.UserData.UserTable.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    User newUser=new User(0L,"sou06","soum","g","10","d@gmail.com","warangal","telangana");
    User user=new User(0L,"sou06","sou","g","10","d@gmail.com","warangal","telangana");

    @Test
    public void getAllUsersTest(){
        List<User> userList=Stream.of(new User(0L,"sou06","sou","g","10","d@gmail.com","warangal","telangana")).collect(Collectors.toList());
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Assertions.assertEquals(userList,userService.getAllUsers());
    }

    @Test
    public void addUserTest(){
        User user=new User(0L,"sou06","sou","g","10","d@gmail.com","warangal","telangana");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertEquals(user,userService.addUser(user));
    }


    @Test
    public void deleteUserTest(){
        Mockito.when(userRepository.findById(0l)).thenReturn(Optional.of(user));
        userService.deleteUser(0L);
        Mockito.verify(userRepository,Mockito.times(1)).deleteById(0L);
    }

    @Test
    public void updateUserTest(){
        Mockito.when(userRepository.findById(0l)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(newUser);
        Assertions.assertEquals(newUser,userService.updateUser(newUser,0L));
    }

    @Test
    public void getOneUserTest(){
        Mockito.when(userRepository.findById(0L)).thenReturn(Optional.of(user));
        Assertions.assertEquals(user,userService.getOneUser(0L));
    }
}

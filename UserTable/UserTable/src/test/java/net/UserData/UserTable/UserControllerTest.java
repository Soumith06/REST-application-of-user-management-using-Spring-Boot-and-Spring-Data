package net.UserData.UserTable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.UserData.UserTable.Controller.UserController;
import net.UserData.UserTable.Service.UserService;
import net.UserData.UserTable.exceptions.UserNotFoundException;
import net.UserData.UserTable.model.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    User user1=new User(0L,"sou06","soum","g","10","d@gmail.com","warangal","telangana");
    User user2=new User(0L,"sou06","sou","g","10","d@gmail.com","warangal","telangana");

    @Test
    @DisplayName("Get All Users success")
    public void getAllUsersTest() throws Exception {
        List<User> userList= Stream.of(user1,user2).collect(Collectors.toList());

        Mockito.when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].userName",Matchers.is(user1.getUserName())));
    }

    @Test
    @DisplayName("usersNotFoundException in Get All Users success")
    public void usersNotFoundExceptionTest() throws Exception{
        Mockito.when(userService.getAllUsers()).thenThrow(new UserNotFoundException("Users List is Empty"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string("Users List is Empty"));

    }

    @Test
    @DisplayName("Get User by Id success")
    public void getUserByIdTest() throws Exception{
        Mockito.when(userService.getOneUser(user1.getUserId())).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/"+user1.getUserId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",Matchers.notNullValue()))
                .andExpect(jsonPath("$.userName",Matchers.is(user1.getUserName())));
    }

    @Test
    @DisplayName("userNotFoundException in Get User by Id")
    public void userNotFoundExceptionOfGetUserByIdTest() throws Exception{
        Mockito.when(userService.getOneUser(user1.getUserId())).thenThrow(new UserNotFoundException("User Not Found"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/"+user1.getUserId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string("User Not Found"));

    }

    @Test
    @DisplayName("Delete User by Id success")
    public void deleteUserByIdTest() throws Exception{
        Mockito.doNothing().when(userService).deleteUser(user1.getUserId());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user/"+user1.getUserId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string("user deleted"));

    }

    @Test
    @DisplayName("userNotFoundException in Delete User by Id")
    public void userNotFoundExceptionOfDeleteUserByIdTest() throws Exception{
        Mockito.doThrow(new UserNotFoundException("User Not Found")).when(userService).deleteUser(user1.getUserId());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user/"+user1.getUserId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string("User Not Found"));

    }

    @Test
    @DisplayName("Create User success")
    public void createUserTest() throws Exception{
        Mockito.when(userService.addUser(user1)).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(user1))
                )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Update User success")
    public void updateUserTest() throws Exception {
        Mockito.when(userService.updateUser(user2, user1.getUserId())).thenReturn(user2);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/user/"+user1.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(user1)))
                .andExpect(status().isCreated());
    }

}


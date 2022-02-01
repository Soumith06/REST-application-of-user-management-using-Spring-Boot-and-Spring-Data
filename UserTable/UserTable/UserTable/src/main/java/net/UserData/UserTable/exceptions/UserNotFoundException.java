package net.UserData.UserTable.exceptions;

import org.springframework.stereotype.Component;

@Component
public class UserNotFoundException extends RuntimeException{
    private String ErrorMessage;

    public UserNotFoundException(String message) {
        super(message);
        ErrorMessage = message;
    }

    public UserNotFoundException(){

    }


}

package net.UserData.UserTable.exceptions;

import org.springframework.stereotype.Component;

@Component
public class UserAlreadyExistsException extends RuntimeException {
    private String ErrorMessage;

    public UserAlreadyExistsException(String message) {
        super(message);
        ErrorMessage = message;
    }

    public UserAlreadyExistsException(){
    }
}

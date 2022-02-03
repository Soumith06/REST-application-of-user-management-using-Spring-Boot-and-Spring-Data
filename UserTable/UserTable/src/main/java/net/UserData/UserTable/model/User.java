package net.UserData.UserTable.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NotNull
    private String userName;
    private String firstName;
    private String lastName;
    @NotNull
    private String mobileNumber;
    @NotNull
    private String emailID;
    private String address1;
    private String address2;

    public User() {

    }

    public User(Long userId, String userName, String firstName, String lastName, String mobileNumber, String emailID, String address1, String address2) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.emailID = emailID;
        this.address1 = address1;
        this.address2 = address2;
    }
}


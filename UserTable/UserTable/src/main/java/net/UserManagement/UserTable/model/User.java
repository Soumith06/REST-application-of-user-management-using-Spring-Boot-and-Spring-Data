package net.UserManagement.UserTable.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_Name",nullable = false)
    private String userName;

    @Column(name = "first_Name",nullable = false)
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "mobile_Number",nullable = false)
    private Integer mobileNumber;

    @Column(name = "email_ID")
    private String emailID;

    @Column(name ="address_1")
    private String address1;

    @Column(name ="address_2")
    private String address2;
}

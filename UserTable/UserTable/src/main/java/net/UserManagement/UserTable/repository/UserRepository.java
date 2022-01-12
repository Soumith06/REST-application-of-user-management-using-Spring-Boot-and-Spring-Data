package net.UserManagement.UserTable.repository;

import net.UserManagement.UserTable.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}

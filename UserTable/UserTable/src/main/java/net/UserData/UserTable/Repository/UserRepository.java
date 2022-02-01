package net.UserData.UserTable.Repository;

import net.UserData.UserTable.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}

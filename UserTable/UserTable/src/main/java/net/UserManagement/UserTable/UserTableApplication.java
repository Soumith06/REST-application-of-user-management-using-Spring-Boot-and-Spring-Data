package net.UserManagement.UserTable;

import net.UserManagement.UserTable.model.User;
import net.UserManagement.UserTable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserTableApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserTableApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User user= new User();
		user.setUserName("pranav");
		user.setFirstName("Pranav");
		user.setLastName("Dola");
		user.setEmailID("pranavdola@gmail.com");
		user.setMobileNumber(12345678);
		user.setAddress1("bcdbcu");
		user.setAddress2("bdsbdef");
		userRepository.save(user);

		User user1= new User();
		user1.setUserName("pranav1");
		user1.setFirstName("Pranav1");
		user1.setMobileNumber(12345678);
		user1.setAddress1("bcdbcu1");
		userRepository.save(user1);
	}
}

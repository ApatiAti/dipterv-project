package hu.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
/*
	List<User> findByEmailOrUsername(String email, String username);
	
	User findByEmail(String email);
	
	User findByUsername(String username);
	*/
}

package hu.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}

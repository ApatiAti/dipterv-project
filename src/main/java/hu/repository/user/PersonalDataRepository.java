package hu.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.model.user.PersonalData;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {

	PersonalData findByUser_Username(String username);

	PersonalData findByUserId(Long userId);

}

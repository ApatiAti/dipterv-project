package hu.repository.security;

import org.springframework.data.repository.CrudRepository;

import hu.model.security.RoleGroup;

public interface RoleGroupRepository extends CrudRepository<RoleGroup, Long> {

	int countByUsersIdAndCode(Long id, String string);
/*
	@Query("SELECT r FROM RoleGroup r JOIN FETCH r.users WHERE r.code = (:code)")
	RoleGroup findByCodeWithUsers(@Param(value = "code") String code);
	
	RoleGroup findByCode(String code);
	*/
}

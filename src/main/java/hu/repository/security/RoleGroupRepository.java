package hu.repository.security;

import org.springframework.data.repository.CrudRepository;

import hu.model.security.RoleGroup;

public interface RoleGroupRepository extends CrudRepository<RoleGroup, Long> {

	int countByUsersIdAndCode(Long id, String string);

}

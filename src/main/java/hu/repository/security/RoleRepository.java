package hu.repository.security;

import org.springframework.data.repository.CrudRepository;

import hu.model.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}

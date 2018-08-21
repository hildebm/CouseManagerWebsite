package org.sambasoft.login.repositories;


import org.sambasoft.login.entities.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository  extends CrudRepository<Role, String> {
 
}

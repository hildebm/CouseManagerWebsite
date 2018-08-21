package org.sambasoft.login.repositories;


import org.sambasoft.login.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository  extends CrudRepository<User, Long> {

	List<User> findByNameLike(String name);
	User findByEmail (String email);

}

package org.sambasoft.login.repositories;

import org.sambasoft.login.entities.Task;
import org.sambasoft.login.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TaskRepository  extends CrudRepository<Task, Long> {

	List<Task> findByUser(User user);
	
}

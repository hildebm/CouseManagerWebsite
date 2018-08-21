package org.sambasoft.login.services;


import org.sambasoft.login.entities.Task;
import org.sambasoft.login.entities.User;
import org.sambasoft.login.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public void addTask(Task task, User user) {
		task.setUser(user);
		taskRepository.save(task);
	}
	
	public List<Task> findUserTask(User user){
		
		return taskRepository.findByUser(user);
	}

}

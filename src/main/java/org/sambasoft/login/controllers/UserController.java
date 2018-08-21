package org.sambasoft.login.controllers;

import org.sambasoft.login.entities.User;
import org.sambasoft.login.services.TaskService;
import org.sambasoft.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/users")
	public String listUsers(Model model, @RequestParam(defaultValue="")  String name) {
		model.addAttribute("users", userService.findByName(name));
		return "views/list";
	}


	@GetMapping("/profile")
	public String showProfilePage(Model model, Principal principal) {

		String email = principal.getName();
		User user = userService.findOne(email);

		model.addAttribute("tasks", taskService.findUserTask(user));
		model.addAttribute("user", user);

		return "views/profile";
	}

	/*---Update a user by id---*/
	@RequestMapping(path = "/user/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") long id, User user) {
		userService.update(id, user);
		return "redirect:/profile";
	}


}

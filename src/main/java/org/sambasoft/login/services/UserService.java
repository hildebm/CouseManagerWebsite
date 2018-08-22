package org.sambasoft.login.services;


import org.sambasoft.login.entities.Role;
import org.sambasoft.login.entities.User;
import org.sambasoft.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void createUser(User user) {
		BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword())); 
		Role userRole = new Role("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
		log.debug("I'm in UserService " + user.getId());
	}
	
	public void createAdmin(User user) {
		BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword())); 
		Role userRole = new Role("ADMIN");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}

	public void createInstructor(User user) {
		BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("USER");
		Role instructorRole = new Role("INSTRUCTOR");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		roles.add(instructorRole);
		user.setRoles(roles);
		userRepository.save(user);
	}
	
	public User findOne(String email) {
		
	  return userRepository.findByEmail(email);
	}

	public boolean isUserPresent(String email) {
		// TODO Auto-generated method stub
		User user =userRepository.findByEmail(email);
		if(user!=null)
			return true;
		
		return false;
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(userList::add);
		return userList;
	}

	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return  userRepository.findByNameLike("%"+name+"%");
	}

	public User findById(Long id){
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) {
			throw new RuntimeException("User Not Found!");
		}

		return userOptional.get();

	}

	public  void update(Long id, User user){
		User currentUser = findById(id);
		currentUser.setAddress(user.getAddress());
		currentUser.setCity(user.getCity());
		currentUser.setState(user.getState());
		currentUser.setZip(user.getZip());
		currentUser.setBirthDate(user.getBirthDate());
		currentUser.setEmail(user.getEmail());
		currentUser.setGender(user.getGender());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPhoneNumber(user.getPhoneNumber());
		currentUser.setAbout(user.getAbout());
		userRepository.save(currentUser);
	}

}

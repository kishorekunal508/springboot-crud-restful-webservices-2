package com.kunal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kunal.entity.User;
import com.kunal.exception.ResourceNotFoundException;
import com.kunal.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	//getallusers
	@GetMapping()
	public List<User> getAllusers(){
		return this.userRepository.findAll();
	}
	
	//getUserById
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User Not Found With Id :" + userId));
	}
	
	//updateId
	@PutMapping("/{id}")
	public User updateUserById(@RequestBody User user , @PathVariable("id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user not found with id :" + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
	}
	
	//deleteuserBuId
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable("id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user not found with this id :" + userId));
		   this.userRepository.delete(existingUser);
		   return ResponseEntity.ok().build();
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
}

package com.springboot.service;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.SpringBootSpringRestAppWithSpringDataJPA;
import com.springboot.entity.User;
import com.springboot.repository.UserRepository;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootSpringRestAppWithSpringDataJPA.class)
public class UserServiceUnitTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	// Below are Unit Test cases but not integretion test cases.
	

	/**
	 * ########################################################################################################################
	 * Select Operation
	 * ########################################################################################################################
	 */
	
	@Test
	public void testGgetUserById() {
		User user = new User(1,"punya", 40, "punya@gmail.com", null, "12345", null, null);
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		//OR
		//Mockito.when(userRepository.findById(1).get()).thenReturn(user);
		Assert.assertEquals(user.getEmail(), userService.getUserById1(1).getEmail());
	}

	/*@Test
	public void getAllUsersTest() {
		
		User user1 = new User(1,"punya", 40, "punya@gmail.com", new Date(), "12345", new Date(), new Date());
		User user2 = new User(2,"pankaj", 30, "pankaj@gmail.com", new Date(), "54321", new Date(), new Date());
		
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		//OR
		//Mockito.when(userRepository.findAll()).thenReturn(Stream.of(new User(1,"punya", 40, "punya@gmail.com", new Date(), "12345", new Date(), new Date()), new User(2,"pankaj", 30, "pankaj@gmail.com", new Date(), "54321", new Date(), new Date())).collect(Collectors.toList()));
		Assert.assertEquals(2,userService.getAllUsers().size());
		
	}*/
	
	/**
	 * ########################################################################################################################
	 * Insert Operation
	 * ########################################################################################################################
	 */

	@Test
	public void saveUserTest() {
		User user = new User(1,"punya", 40, "punya@gmail.com", new Date(), "12345", new Date(), new Date());
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Assert.assertEquals(user, userService.saveUser(user));

	}

	/**
	 * ########################################################################################################################
	 * Update Operation
	 * ########################################################################################################################
	 */
	
	/*@Test
	public void updateUserTest() {
		User user = new User(1,"punya", 40, "punya@gmail.com", new Date(), "12345", new Date(), new Date());
		Mockito.when(userRepository.save(user)).thenReturn(user);
		user.setEmail("punyasmruti@gmail.com");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Assert.assertEquals(user, userService.patchUser(user,user.getId()));
	}*/

	/**
	 * ########################################################################################################################
	 * Delete Operation
	 * ########################################################################################################################
	 */
/*
	@Test
	public void deleteUserByIdTest() {
		User user = new User(1,"punya",40, "punya@gmail.com", new Date(), "12345", new Date(), new Date());
		
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(userRepository.findById(1).get()).thenReturn(user);
	
		//userService.deleteUser(user.getId());
		Mockito.verify(userRepository, Mockito.times(0)).delete(user);
	}*/
	
	//@Test
	public void deleteAllUsersTest() {
		
	}
}

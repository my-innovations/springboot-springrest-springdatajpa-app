package com.springboot.service;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.springboot.entity.User;
import com.springboot.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTestUsingMockito {
	
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Mock
	UserRepository userRepository;
	
	
	/**
	 * ##############################################################################################################################
	 * Insert Operation
	 * ##############################################################################################################################
	 */
	
	//@Test(expected=NullPointerException.class)
	@Test
	public void testCreateUser( ) throws Exception{
		User user = new User(1,"punya", 40, "punya@gmail.com", null, "12345", null, null);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User created = userServiceImpl.saveUser(user);
		Assertions.assertThat(created.getEmail()).isSameAs(user.getEmail());
	}
	
	/**
	 * ##############################################################################################################################
	 * Select Operation
	 * ##############################################################################################################################
	 */
	
	/**
	 * ##############################################################################################################################
	 * Update Operation
	 * ##############################################################################################################################
	 */
	
	
	/**
	 * ##############################################################################################################################
	 * Delete Operation
	 * ##############################################################################################################################
	 */
	

}

package com.springboot.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	
	//This bean will be created by spring test f/w
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ########################################################################################################################
	 * Insert Operation
	 * ########################################################################################################################
	 * 
	 */
	
	@Test
	public void testSaveUser() {
		User user1 = new User("punya","nayak",40,"punyasmruti@gmail.com",new Date(),"54321",new Date(),new Date());
		//User user2 = testEntityManager.persist(user1);
		User user2 = userRepository.save(user1);
		Optional<User> dbUser = userRepository.findById(user2.getId());
	
		//assertThat(dbUser).isEqualTo(Optional.of(user2)); // showing error , kindly check
		Assert.assertEquals(user1.getEmail(),user2.getEmail());
		Assert.assertEquals(user1.getEmail(),dbUser.get().getEmail());
		//org.hamcrest.MatcherAssert.assertThat(dbUser.get(),user2);
	}
	
	
	@Test
	public void testSaveUser2() {
		User user = new User("pppppp","nnnnnnn",40,"punyasm@gmail.com",new Date(),"54329",new Date(),new Date());
		userRepository.save(user);
		
		Iterable<User> dbUsers = userRepository.findAll();
		Assertions.assertThat(dbUsers).extracting(User::getFirstname).contains("pppppp");
	}
	
	/**
	 * ########################################################################################################################
	 * Select Operation
	 * ########################################################################################################################
	 * 
	 */
	
	@Test
	public void testgetUserById() {
		User user1 = testEntityManager.persist(new User("pankaj123","prajapati",30,"pankaj123@gmail.com",new Date(),"12345",new Date(),new Date()));
		User user2 = testEntityManager.find(User.class, user1.getId()); //OK
		Assert.assertEquals(user1.getFirstname(),user2.getFirstname());
	}
	
	@Test
	public void testgetUserById2() {
		Optional<User> userFromDB = userRepository.findById(1);
		User u = userFromDB.get();
		Assert.assertEquals("punya@gmail.com",u.getEmail());
		
		//assertThat(userFromDB.getEmail().get()).isEqualTo(userFromDB.getEmail().get());

	}
	
	@Test
	public void testgetAllUsers() {
		Iterable<User> userFromDB = userRepository.findAll();
		List<User> list = new ArrayList<>();
		for(User u:userFromDB) {
			list.add(u);
		}
		Assert.assertEquals(6,list.size());
		
		
	}
	
	
	@Test
	public void testgetAllUsers2() {
		List<User> userFromDB = (List<User>) userRepository.findAll();
		Assert.assertEquals(6,userFromDB.size());
	}
	
	/**
	 * ########################################################################################################################
	 * Update Operation
	 * ########################################################################################################################
	 * 
	 */
	@Test
	public void testUpdateUser() {
		User user = new User("punyasmruti","nayak",40,"punyasmruti@gmail.com",new Date(),"12345",new Date(),new Date());
		User user2 = testEntityManager.persist(user);
		Optional<User> userFromDB = userRepository.findById(user2.getId());
		User u2;
		if(userFromDB.isPresent()) {
		u2= userFromDB.get();
		u2.setEmail("punyasmrutinayak@gmail.com");
		User u3=testEntityManager.persist(u2);
		Assert.assertEquals("punyasmrutinayak@gmail.com",u3.getEmail());
		}
	}
	
	/**
	 * ########################################################################################################################
	 * Delete Operation
	 * ########################################################################################################################
	 * 
	 */
	
	@Test
	public void testDeleteUserById() {
		User user = new User("manas","behera",40,"manas@gmail.com",new Date(),"12345",new Date(),new Date());
		User u = testEntityManager.persist(user);
		testEntityManager.remove(u);
		Optional<User> userFromDB = userRepository.findById(u.getId());
		Assert.assertEquals(false,userFromDB.isPresent());
	}
	
	
	@Test
	public void testDeleteAllUsers() {
		userRepository.deleteAll();
		Assertions.assertThat(userRepository.findAll()).isEmpty();
		//OR
		Iterable<User> allUsers = userRepository.findAll();
		List<User> list = new ArrayList<>();
		for(User u:allUsers) {
			list.add(u);
		}
		Assert.assertEquals(0,list.size());
	}
}

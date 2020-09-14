package com.springboot.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.springboot.entity.User;
import com.springboot.exception.UserNotFoundException;

public interface UserService {
	
	/*##############################################################################################################################################################################
	SELECT
	#########################################################################################################################################################################*/
	
	//################## byID ##################
	public User getUserById1(Integer id);
	public Optional<User> getUserById2(Integer id);
	public ResponseEntity<User> getUserById3(Integer id);
	public ResponseEntity<?> getUserById4(Integer id);
	public ResponseEntity<?> getUserById5(Integer id);
	
	//################## by firstname ##################
	public List<User> findUsersByFirstname(String firstname);
	public List<User> findUsersByFirstnameWithSorting(String firstname);
	public Page<User> findUsersByFirstnameWithSortingAndPagination(String firstname, Pageable pageable);
	//Async Demo
	public CompletableFuture<User> findOneByFirstname(String firstname);
	

	//################## by lastname ##################
	public List<User> findUsersByLastnameWithoutSortingAndPagination(String lastname);
	public List<User> findUsersByLastnameWithSorting(String lastname);
	//Pagination concept
	public Page<User> findUsersByLastnameWithSortingAndPagination(String lastname,Pageable pageRequest);
	
	
	//################## by email ##################
	public boolean findUserByEmail(String email) ;
	public Stream<User> findUserByEmailReturnStream(String email);
	
	//################## find all  ##################
	public List<User> getAllUsers();
	//pagination
	List<User> getAllUsersWithPagination(int pageNo, int pageSize, String sortBy);
	Page<User> getAllUsersWithPaginationAndSorting(Pageable pageable);
	
	/*########################################################################################################################################################################
	INSERT
	#########################################################################################################################################################################*/
	
	public User saveUser(User user);
	public ResponseEntity<?> saveUser2(User user);
	
	
	/*#########################################################################################################################################################################
	UPDATE
	#########################################################################################################################################################################*/
	public User updateOrSave(User newUser, Integer id);
	public User updateUser(User u, Integer id) throws UserNotFoundException;
	
	/*##########################################################################################################################################################################
	PATCH
	#########################################################################################################################################################################*/
	
	public User patchEmailOnly(Map<String, String> update, Integer id);
	
	/*#########################################################################################################################################################################
	DELETE
	#########################################################################################################################################################################*/
	public void deleteUserById1(Integer id);
	public ResponseEntity<String> deleteUserById2(Integer id);
	public ResponseEntity<Object> deleteUserById3(Integer id);
	public ResponseEntity<Object> deleteUserById4(Integer id);
	
	public void deleteUsersInRange(List<User> userList);
	
	public void deleteAllUsers1();
	public void deleteAllUsers2() ;
	public ResponseEntity<Object> deleteAllUsers3();
	
}

package com.springboot.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.springboot.entity.User;

//This annotation is optional
//@Repository("userRepository") //OK
//@Repository(value="userRepository") //OK
@Repository
//public interface UserRepository extends Repository<User, Integer> {
//public interface UserRepository extends CrudRepository<User, Integer> {
//public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
public interface UserRepository extends JpaRepository<User, Integer> {

	// ########################### by firstname  #########################################################################################################################
	
	// public Iterable<User> findOneByFirstname(String firstname);
	// public List<User> findOneByFirstname(String firstname);
	// public Optional<List<User>> findOneByFirstname(String firstname);
	public Optional<List<User>> findByFirstname(String firstname);
	
	//using async
	//@Async 
	//public Future<User> findOneByFirstname(String firstname);
	
	@Async
	CompletableFuture<User> findOneByFirstname(String firstname);
	
	//@Async 
	//public ListenableFuture<User> findOneByFirstname(String firstname);
	
	// using Order by
	public Optional<List<User>> findByFirstnameOrderByLastnameAsc(String firstname);
	public Optional<List<User>> findByFirstnameOrderByLastnameDesc(String firstname);
	public Optional<List<User>> findByFirstnameOrderByEmailAsc(String firstname);
	public Optional<List<User>> findByFirstnameOrderByEmailDesc(String firstname);
	public Optional<List<User>> findByFirstnameOrderByAgeAsc(String firstname);
	public Optional<List<User>> findByFirstnameOrderByAgeDesc(String firstname);
	public Optional<List<User>> findByFirstnameOrderByDobAsc(String firstname);
	public Optional<List<User>> findByFirstnameOrderByDobDesc(String firstname);
	
	//using custom query
	@Query("select u from User u where u.firstname = ?1") // this is mandatory here
	public Optional<List<User>> findByFirstname1(String firstname);
	
	@Query("select u from User u where u.firstname = :firstname") // this is mandatory here
	public Optional<List<User>> findByFirstname2(@Param("firstname") String firstname);
	
	//using sorting only
	public List<User> findByFirstname(String firstname, Sort sort);
	
	//using pagination and sorting
	//public List<User> findByFirstname(String firstname, Pageable pageable); 
	
	public Page<User> findByFirstname(String firstname,Pageable pageable);
	
	//public Slice<User> findByFirstname(String firstname,Pageable pageable);
	
	// ########################### by lastname  #########################################################################################################################
	//public Iterable<User> findByLastname(String lastname);
	public List<User> findByLastname(String lastname);
	
	
	//public Optional<List<User>> findByLastname(String lastname);
	
	public Iterable<User> findOneByLastname(String lastname);
	//public List<User> findOneByLastname(String lastname);
	//public Optional<List<<User>> findOneByLastname(String lastname);
	
	// using Order by
	public Optional<List<User>> findByLastnameOrderByFirstnameAsc(String lastname);
	public Optional<List<User>> findByLastnameOrderByFirstnameDesc(String lastname);
	public Optional<List<User>> findByLastnameOrderByEmailAsc(String lastname);
	public Optional<List<User>> findByLastnameOrderByEmailDesc(String lastname);
	public Optional<List<User>> findByLastnameOrderByAgeAsc(String lastname);
	public Optional<List<User>> findByLastnameOrderByAgeDesc(String lastname);
	public Optional<List<User>> findByLastnameOrderByDobAsc(String lastname);
	public Optional<List<User>> findByLastnameOrderByDobDesc(String lastname);
	
	//using LIKE
	
	//using custom query
	@Query("select u from User u where u.lastname = ?1") // this is mandatory here
	public Optional<List<User>> findByLastname1(String lastname);
		
	@Query("select u from User u where u.lastname = :lastname") // this is mandatory here
	public Optional<List<User>> findByLastname2(@Param("lastname") String lastname);
		
	// sorting
	public List<User> findByLastname(String lastname, Sort sort);

	//Page vs Slice
	// pagination
	public Page<User> findByLastname(String lastname, Pageable pageable);
	
	// public Slice<User> findByLastname2(String lastname,Pageable pageable);
	
	//AND
	Iterable<User> findOneByFirstnameAndLastname(String firstname, String lastname);
	// List<User> findOneByFirstnameAndLastname(String firstname,String lastname);
	
	//OR
	Iterable<User> findOneByFirstnameOrLastname(String firstname, String lastname);

	// ########################### by email ################################################################################################################################

	public Optional<User> findByEmail(String email);

	@Query("select u from User u where u.email = ?1") // this is mandatory here
	public Optional<User> findByEmail1(String email);
		
	@Query("select u from User u where u.email = :email")
	Stream<User> findByEmailReturnStream(@Param("email") String email);
	
	//using like

	// ########################### by age ################################################################################################################################
	//using BETWEEN

	// ########################### by dob  ################################################################################################################################
	//using BETWEEN

}

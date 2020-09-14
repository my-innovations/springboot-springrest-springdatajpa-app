package com.springboot.service;

import static com.springboot.error.ErrorMessages.ALL_USERS_DELETED;
import static com.springboot.error.ErrorMessages.EMAIL_ALREADY_EXISTS;
import static com.springboot.error.ErrorMessages.NO_USERS_FOUND_IN_DB;
import static com.springboot.error.ErrorMessages.USER_DELETED_WITH_ID;
import static com.springboot.error.ErrorMessages.USER_NOT_FOUND_WITH_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.entity.User;
import com.springboot.exception.EmailAlreadyExistsException;
import com.springboot.exception.UserNotFoundException;
import com.springboot.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	// @Autowired //OK
	// private DataSource dataSource; //OK

	@Autowired
	private UserRepository userRepository;

	/**
	 * ####################################################################################################################################
	 * Insert / save  Operation
	 * ####################################################################################################################################
	 */

	@Transactional
	@Override
	public User saveUser(User user) {
		
		Optional<User> u = userRepository.findByEmail(user.getEmail());
		if(u.isPresent()) {
			throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS);
			//OR
			//new UserRegistrationUnsuccessfulException(EMAIL_ALREADY_EXISTS);
		}
		return userRepository.save(user);
	}
	
	
	@Transactional
	@Override
	public ResponseEntity<?> saveUser2(User user) {
		
		boolean isExists = findUserByEmail(user.getEmail());
		
		if (isExists)
			return new ResponseEntity<>(new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS),HttpStatus.CONFLICT);
		
		User u = userRepository.save(user);

		// HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/create-user/{id}").buildAndExpand(user2.getId()).toUri());
		// return new ResponseEntity<String>(headers, HttpStatus.CREATED);

		//return new ResponseEntity<>(user,HttpStatus.CREATED);
		//OR
		return new ResponseEntity<User>(u,new HttpHeaders(), HttpStatus.CREATED);
	}

	/**
	 * ####################################################################################################################################
	 * Select Operation
	 * ####################################################################################################################################
	 */

	//################################################ by ID ###########################################################################
	@Override
	public User getUserById1(Integer id) {
		return userRepository.findById(id).map(user -> {
			return user;
		}).orElseGet(() -> {throw new UserNotFoundException(USER_NOT_FOUND_WITH_ID + id);});
	}
	 
	@Override
	public Optional<User> getUserById2(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException(USER_NOT_FOUND_WITH_ID + id);
		}
		return user;
	}

	@Override
	public ResponseEntity<User> getUserById3(Integer id){
		// Optional<User> user = userRepository.findById(id);
		// return new ResponseEntity(user, HttpStatus.OK);
		//or
		// return new ResponseEntity.ok().body(user);//OK
		//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		// OR
		return userRepository.findById(id).map(user -> ResponseEntity.ok().body(user)).orElse(ResponseEntity.notFound().build());
		/*return userRepository.findById(id).map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> {
			throw new UserNotFoundException("user not found with id :" + id);
		});*/
	}
	
	@Override
	public ResponseEntity<?> getUserById4(Integer id){
	return userRepository.findById(id).map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> {
		return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		});
	}
	
	@Override
	public ResponseEntity<?> getUserById5(Integer id){
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	//################################################ by firstname ###########################################################################
	
	/**
	 * without Sorting Concept
	 */
	
	@Override
	public List<User> findUsersByFirstname(String firstname) {
		return userRepository.findByFirstname(firstname).get();
	}
	
	/**
	 * with Sorting Concept
	 */
	@Override
	public List<User> findUsersByFirstnameWithSorting(String firstname) {
		return userRepository.findByFirstname(firstname,Sort.by("createdAtDate"));
	}
	
	/**
	 * with Pagenation Concept
	 */
	@Override
	public Page<User> findUsersByFirstnameWithSortingAndPagination(String firstname, Pageable pageable) {
		return userRepository.findByFirstname(firstname, pageable);
	}
	
	/**
	 * Async Concept
	 */

	@Override
	public CompletableFuture<User> findOneByFirstname(String firstname) {
		return userRepository.findOneByFirstname(firstname);
	}
	
	//############################################by lastname ###############################################################################
	
	/**
	 * without Sorting and pagination Concept
	 */
	
	@Override
	public List<User> findUsersByLastnameWithoutSortingAndPagination(String lastname) {
		return userRepository.findByLastname(lastname);
	}
	
	/**
	 * with Sorting Concept
	 */
	@Override
	public List<User> findUsersByLastnameWithSorting(String lastname) {
		return userRepository.findByLastname(lastname,Sort.by("createdAtDate"));
	}
	
	/**
	 * with Pagenation Concept
	 */
	@Override
	public Page<User> findUsersByLastnameWithSortingAndPagination(String lastname, Pageable pageable) {
		return userRepository.findByLastname(lastname, pageable);
	}
	
	
	//############################################### by email ############################################################################
	
	/**
	 * returns single record because unique.
	 */
	
	@Override
	public boolean findUserByEmail(String email) {

		Optional<User> u = userRepository.findByEmail(email);
		if (u.isPresent())
			throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS);
		else
		return false;
	}	
	
	
	@Transactional
	@Override
	public Stream<User> findUserByEmailReturnStream(String email) {
		return userRepository.findByEmailReturnStream(email);
	}

	//################################################## all users #########################################################################
	
	/**
	 * with sorting only
	 */
	
	@Override
	public List<User> getAllUsers() {
		
		//List<User> usersList = userRepository.findAll();
		//OR
		/*List<User> usersList = new ArrayList<>();
		userRepository.findAll().forEach(usersList::add);
		if (usersList.size() == 0) {
			//return new ResponseEntity<>(HttpStatus.NO_CONTENT);//OK
			//return new ResponseEntity<>(null,HttpStatus.NO_CONTENT); //OK
			throw new UserNotFoundException("No Users exists");
		}*/
		//using only Sort
		//List<User> usersList =  userRepository.findAll(Sort.by("firstname"));
		//List<User> usersList =  userRepository.findAll(Sort.by("lastname"));
		//List<User> usersList =  userRepository.findAll(Sort.by("email"));
		//List<User> usersList = userRepository.findAll(Sort.by("age"));
		//List<User> usersList = userRepository.findAll(Sort.by("dob"));
		List<User> usersList = userRepository.findAll(Sort.by("createdAtDate"));
		//List<User> usersList = userRepository.findAll(Sort.by("fistname").ascending().and(Sort.by("lastname").descending()));
		return usersList;
	}
	
	/**
	 * with both sorting and pagination
	 */
	
	@Override
	public Page<User> getAllUsersWithPaginationAndSorting(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	/**
	 * with both sorting and pagination
	 */
	
	@Override
	public List<User> getAllUsersWithPagination(int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<User> pagedResult = userRepository.findAll(pageable);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }
	}

	/**
	 * ####################################################################################################################################
	 * Update Operation
	 * ####################################################################################################################################
	 */
	
	/**
	 * update only opern
	 */

	@Transactional
	@Override
	public User updateUser(User u, Integer id) {
		return userRepository.findById(id).map(user -> {
			user.setFirstname(u.getFirstname());
			user.setLastname(u.getLastname());
			user.setAge(u.getAge());
			user.setEmail(u.getEmail());
			user.setDob(u.getDob());
			user.setMobile_no(u.getMobile_no());
			user.setCreatedAtDate(u.getCreatedAtDate());
			user.setUpdatedAtDate(u.getUpdatedAtDate());
			return userRepository.save(user);
		}).orElseGet(() -> {
			throw new UserNotFoundException(USER_NOT_FOUND_WITH_ID+ id);
		});

	}

	/**
	 * update or save opern
	 */
	
	@Override
	public User updateOrSave(User newUser, Integer id) {

		return userRepository.findById(id).map(x -> {
			x.setFirstname(newUser.getFirstname());
			x.setLastname(newUser.getLastname());
			x.setEmail(newUser.getEmail());
			// update operation
			return userRepository.save(x); //update opern
		}).orElseGet(() -> {
			// newUser.setId(id);
			// save operation
			return userRepository.save(newUser); // save opern
		});
	}

	
	/**
	 * ####################################################################################################################################
	 * Patch Operation
	 * ####################################################################################################################################
	 */
	/**
	 * update email only , no other fiekds.
	 */
	@Transactional
	@Override
	public User patchEmailOnly(Map<String, String> map, Integer id) {

		return userRepository.findById(id).map(user -> {

			String email = map.get("email");
			
			if (!StringUtils.isEmpty(email)) {
				user.setEmail(email);
				return userRepository.save(user);
			} else {
				// throw new BookUnSupportedFieldPatchException(update.keySet());
				return null;
			}
		}).orElseGet(() -> {
			throw new UserNotFoundException(USER_NOT_FOUND_WITH_ID + id);
		});

	}

	/**
	 * ####################################################################################################################################
	 * Delete Operation
	 * ####################################################################################################################################
	 */
	/**
	 * delete user by is with no return type.
	 */

	@Transactional
	@Override
	public void deleteUserById1(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException(USER_NOT_FOUND_WITH_ID + id);
		}
		userRepository.deleteById(id);
		//or 
		//userRepository.delete(user);
	}
	
	/**
	 * delete user by is with return type ResponseEntity<String>.
	 */

	@Transactional
	@Override
	public ResponseEntity<String> deleteUserById2(Integer id) {
		return userRepository.findById(id).map(user -> {
			// userRepository.deleteById(id);
			//OR
			userRepository.delete(user);			
			//return ResponseEntity.ok().build();
			 return ResponseEntity.ok(USER_DELETED_WITH_ID +id);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * delete user by is with return type ResponseEntity<Object>.
	 */
	
	@Transactional
	@Override
	public ResponseEntity<Object> deleteUserById3(Integer id) {
		return userRepository.findById(id).map(user -> {
			// userRepository.deleteById(id);//OK
			userRepository.delete(user);
			return ResponseEntity.ok().build();
			//OR
			//return ResponseEntity.ok(USER_DELETED_WITH_ID + id);
		}).orElseGet(() -> new ResponseEntity<>(null,HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	@Override
	public ResponseEntity<Object> deleteUserById4(Integer id) {
		return userRepository.findById(id).map(user -> {
			// userRepository.deleteById(id);
			//OR
			userRepository.delete(user);
			return ResponseEntity.ok().build();
			//ot
			//return ResponseEntity.ok(USER_DELETED_WITH_ID + id);
		}).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID + id));
	}
	
	@Transactional
	@Override
	public void deleteUsersInRange(List<User> userList) {
		//userRepository.deleteAll(userList);
		//OR
		userRepository.deleteInBatch(userList);
	}
	
	@Transactional
	@Override
	public void deleteAllUsers1() {
		userRepository.deleteAll(); 
		//or
		//userRepository.deleteAllInBatch();
	}

	@Transactional
	@Override
	public void deleteAllUsers2() {
		List<User> list  = (List<User>) userRepository.findAll();
		if (list.size() == 0) {
			throw new UserNotFoundException(NO_USERS_FOUND_IN_DB);
		}
		userRepository.deleteAll();
		//or
		//userRepository.deleteAllInBatch(); //OK
	}
	
	@Transactional
	@Override
	public ResponseEntity<Object> deleteAllUsers3() {
		List<User> list  = (List<User>) userRepository.findAll();
		if (list.size() == 0) {
			//return new ResponseEntity<>(NO_USERS_FOUND_IN_DB, new HttpHeaders(),HttpStatus.NOT_FOUND);
			//OR
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NO_USERS_FOUND_IN_DB);
		}
		userRepository.deleteAll();
		//oR
		//userRepository.deleteAllInBatch();
		return new ResponseEntity<>(ALL_USERS_DELETED,HttpStatus.OK);
		//OR
		//return ResponseEntity.ok(ALL_USERS_DELETED);
	}
}

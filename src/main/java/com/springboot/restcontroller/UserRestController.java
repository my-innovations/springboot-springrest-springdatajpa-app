package com.springboot.restcontroller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.entity.User;
import com.springboot.exception.UserNotFoundException;
import com.springboot.service.UserService;

/*
	200 - OK
	201 - Created
	202 - Accepted
	204 - No Content
	205 - Reset Content
	207 - Multi Status
	400 - Bad request
	401 - Un Authorized Access
	402 - Payment Required
	403 - Forbidden Access
	404 - Not Found
	406 - Not Acceptable
	415 - MediaType Not Supported
	500 - Internal Server Error
	501 - Not implemented
	502 - Bad Gateway
	503 - Service Un avialable
	504 - Gateway Timeout
	505 - Http Version not supported
	507 - Insufficient Storage
	
	Content-Type header is for  req.
	Accept header is for response.
	
	request payload:
	
	{
        "firstname": "punyasmruti",
        "lastname": "nayak",
        "email": "punyasmruti@gmail.com",
        "dob": "1980-05-10T18:30:00.000+0000",
        "mobile_no": "9962428121",
		"age":40,
        "createdAtDate": "1980-05-10T18:30:00.000+0000",
        "updatedAtDate": "1980-05-10T18:30:00.000+0000"
    }
    
    GET:
    http://localhost:8081/api/rest/user/getLocalServerPort
    http://localhost:8081/api/rest/user/jsonString
    
    http://localhost:8081/api/rest/user/getUserById1/{id}
    http://localhost:8081/api/rest/user/getUserById2/{id}
    http://localhost:8081/api/rest/user/getUserById3/{id}
    http://localhost:8081/api/rest/user/getUserById4/{id}
    http://localhost:8081/api/rest/user/getUserById5/{id}
    
    http://localhost:8081/api/rest/user/getUserByFirstname/{firstname}
    
    http://localhost:8081/api/rest/user/getUserByLastname/{lastname}
    http://localhost:8081/api/rest/user/find-by-lastname-with-pagination/{lastname}
    http://localhost:8081/api/rest/user/find-by-lastname-with-pagination?pageNo-1&lastname=nayak
    http://localhost:8081/api/rest/user/find-by-lastname-with-pagination/nayak
	http://localhost:8081/api/rest/user/find-by-lastname-with-pagination/nayak?pageNo=0&pageSize=1&sortBy=id
	http://localhost:8081/api/rest/user/find-by-lastname-with-pagination/nayak?pageNo=1&pageSize=1&sortBy=id
	http://localhost:8081/api/rest/user/find-by-lastname-with-pagination/nayak?pageNo=2&pageSize=1&sortBy=id
	
    http://localhost:8081/api/rest/user/getUserByEmail/{email}
    
    http://localhost:8081/api/rest/user/find-all-with-pagination??pageNo=0&pageSize=3&sortBy=id
	http://localhost:8081/api/rest/user/find-all-with-pagination?pageNo=0&pageSize=3&sortBy=lastname
    http://localhost:8081/api/rest/user/all1
    http://localhost:8081/api/rest/user/all2
    http://localhost:8081/api/rest/user/get-all-users-with-pagination?page=0&size=3&sort=firstname
	http://localhost:8081/api/rest/user/get-all-users-with-pagination?page=0&size=3&sort=id
	http://localhost:8081/api/rest/user/get-all-users-with-pagination
	http://localhost:8081/api/rest/user/get-all-users-with-pagination?pageNo=0&pageSize=3&sortBy=id
	http://localhost:8081/api/rest/user/get-all-users-with-pagination?pageNo=0&pageSize=3&sortBy=firstname
	ttp://localhost:8081/api/rest/user/get-all-users-with-pagination?pageNo=0&pageSize=3&sortBy=lastname
	
	POST::
	http://localhost:8081/api/rest/user/save1
	http://localhost:8081/api/rest/user/save2
	http://localhost:8081/api/rest/user/save3
    
    PUT:
    http://localhost:8081/api/rest/user/update/{id}
    http://localhost:8081/api/rest/user/updateOrSave/{id}
    
    PATCH:
    http://localhost:8081/api/rest/user/patch1/{id}
    http://localhost:8081/api/rest/user/patchEmailOnly/{id}
    
    DELETE:
    http://localhost:8081/api/rest/user/deleteuser1?id=1
    http://localhost:8081/api/rest/user/deleteuser2/{id}
    http://localhost:8081/api/rest/user/deleteuser3/{id}
    http://localhost:8081/api/rest/user/deleteuser4/{id}
    http://localhost:8081/api/rest/user/delete/all
    
*/

//@Slf4j
@Validated // class level
@RestController
@RequestMapping("/api/rest/user")
public class UserRestController implements ErrorController {

	//@Autowired //OK
	private UserService userService;

	// @Autowired
	// private UserRepository userRepository; //OK
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService=userService;
	}
	
	@Autowired
	Environment env;

	
	
	/**
	 * #############################################################################################################################################################################################
	 * Default Error message display.(string/json response)
	 * #############################################################################################################################################################################################
	 */
	// Here error Response is string msg.
	@Override
	public String getErrorPath() {
		return "/error";
	}

	@GetMapping("/error")
	public String defaultErrorMessage() {
		return "Requested resource not found";
	}

	// OR , Here error Response is json msg.

	/*
	 * @Autowired private ErrorAttributes errorAttributes;
	 * 
	 * @GetMapping("/error") public ErrorJson error(HttpServletRequest
	 * req,HttpServletResponse res) { return new
	 * ErrorJson(res.getStatus(),getErrorAttributes(req,true)); } private
	 * Map<String,Object> getErrorAttributes(HttpServletRequest req,boolean b){
	 * RequestAttributes attrs = new ServletRequestAttributes(req); //WebRequest
	 * webReq= return errorAttributes.getErrorAttributes(attrs,b); }
	 */
	
	@RequestMapping("/getLocalServerPort")
	public String port() {
		System.out.println(UUID.randomUUID().toString());
		return env.getProperty("local.server.port");
	}// http://localhost:8081/getLocalServerPort
	
	@RequestMapping(value = "/jsonString", method = RequestMethod.GET)
	public ResponseEntity<String> hello2() {
		return ResponseEntity.ok("{\"firstname\":\"punya\",\"lastname\":\"nayak\"}");
	}


	/**
	 * ######################################################################################################################################################################################################
	 * Select / GET operations
	 * ######################################################################################################################################################################################################
	 */

	// @GetMapping(value = "/getUserById1/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	@GetMapping(value = "/getUserById1/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	//@RequestMapping(value = "/getUserById1/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	// @RequestMapping(value = "/getUserById1/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserById1(@PathVariable @Min(1) Integer id) {
		return  userService.getUserById1(id);
	}
	
	// @GetMapping(value = "/getUserById2/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	@GetMapping(value = "/getUserById2/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	//@RequestMapping(value = "/getUserById2/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	// @RequestMapping(value = "/getUserById2/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping("/getUserById2/{id}")
	public Optional<User> getUserById2(@PathVariable @Min(1) Integer id) {
		return userService.getUserById2(id);
	}

	//@GetMapping(value = "/getUserById3/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	@GetMapping(value = "/getUserById3/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	//@RequestMapping(value = "/getUserById3/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	// @RequestMapping(value = "/getUserById3/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<User> getUserById3(@PathVariable @Min(1) Integer id) {
		Optional<User> user = userService.getUserById2(id);
		//mark here
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers1());
		resource.add(linkTo.withRel("all1"));
		return resource;
	}

	//@GetMapping(value = "/getUserById4/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@GetMapping(value = "/getUserById4/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	//@RequestMapping(value = "/getUserById4/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	// @RequestMapping(value = "/getUserById4/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping("/getUserById4/{id}")
	public ResponseEntity<User> getUserById4(@PathVariable @Min(1) Integer id) {
		return userService.getUserById3(id);
	}
	
	//@GetMapping(value = "/getUserById5/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@GetMapping(value = "/getUserById5/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping(value = "/getUserById5/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping( "/getUserById5/{id}")
	public ResponseEntity<?> getUserById5(@PathVariable @Min(1) Integer id) {
		return userService.getUserById5(id);
	}
	
	@GetMapping(value = "/getUserByFirstname/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping(value = "/getUserByFirstname/{firstname}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping( "/getUserByFirstname/{firstname}")
	public List<User> findUserByFirstname(@PathVariable("firstname") String firstname){
		return userService.findUsersByFirstname(firstname);
	}
	
	@GetMapping(value = "/findUserByFirstname/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping(value = "/findUserByFirstname/{firstname}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping( "/findUserByFirstname/{firstname}")
	public List<User> findUserByFirstnameWithSorting(@PathVariable("firstname") String firstname){
		return userService.findUsersByFirstnameWithSorting(firstname);
	}
	
	@GetMapping(value = "/find-by-firstname-with-sorting-pagination/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping(value = "/find-by-firstname-with-sorting-pagination/{firstname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)//OK
	public Page<User> findUserByFirstnameWithSortingAndPagination(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,@RequestParam(value = "pageSize", defaultValue = "3") int pageSize,@RequestParam(value = "sortBy", defaultValue = "id") String sortBy,@PathVariable("firstname") String firstname) {
		
		//only pagination
		//Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		// pagination and sorting together
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		//Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("fistname").ascending().and(Sort.by("lastname").descending());
		return userService.findUsersByFirstnameWithSortingAndPagination(firstname,pageable);
	}
	
	
	@GetMapping(value = "/findUserByLastnameWithoutSorting/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> findUsersByLastname(@PathVariable("lastname") String lastname){
		return userService.findUsersByLastnameWithoutSortingAndPagination(lastname);
	}
	
	@GetMapping(value = "/getUserByLastnameWithSorting/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> findUsersByLastnameWithSorting(@PathVariable("lastname") String lastname){
		return userService.findUsersByLastnameWithSorting(lastname);
	}
	
	@GetMapping(value = "/find-by-lastname-with-sorting-and-pagination/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping(value = "/find-by-lastname-with-pagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)//OK
	public Page<User> findUserByLastnameWithSortingAndPagination(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,@RequestParam(value = "pageSize", defaultValue = "5") int pageSize,@RequestParam(value = "sortBy", defaultValue = "id") String sortBy,@PathVariable("lastname") String lastname) {
		
		//only pagination
		//Pageable pageWithTwoElements = PageRequest.of(pageNo, pageSize);
		
		// sorting and pagination together
		Pageable sortedBy = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		//Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("fistname").ascending().and(Sort.by("lastname").descending());
		return userService.findUsersByLastnameWithSortingAndPagination(lastname,sortedBy);
	}
	
	//check this
	@GetMapping(value = "/getUserByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> findUserByEmail(@PathVariable("email") String email){
		List<User> list = userService.findUserByEmailReturnStream(email).filter(user -> user.getEmail().equalsIgnoreCase(email)).collect(Collectors.toList());
		return list;
	}
	
	//@GetMapping(value="/all1",produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	@GetMapping(value="/all1",produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping(value = "/users",Method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)//OK
	// @RequestMapping(value = "/users")//OK
	// @RequestMapping("/users")//OK
	public List<User> getAllUsers1() {
		return userService.getAllUsers();
	}

	//@RequestMapping(value = "/all2", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	@RequestMapping(value = "/all2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers2() {
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			//return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			//return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
			// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value = "/find-all-with-pagination", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> findAllUsersWithPagination3(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,@RequestParam(value = "pageSize", defaultValue = "3") int pageSize,@RequestParam(value="sortBy",defaultValue = "id") String sortBy) {
		List<User> list = userService.getAllUsersWithPagination(pageNo, pageSize, sortBy);
		return list;
	}
	
	@GetMapping(value = "/get-all-users-with-pagination", produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping(value = "/get-users-with-pagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)//OK
	public Page<User> getAllUsersWithPaginationAndSorting(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,@RequestParam(value = "pageSize", defaultValue = "2") int pageSize,@RequestParam(value="sortBy",defaultValue = "id") String sortBy) {
		
		// using only pagination
		//Pageable pageable = PageRequest.of(pageNo, pageSize);
		//Pageable pageable2 = new PageRequest(pageNo, pageSize, Sort.Direction.ASC, "firstname", "age")
		//Pageable pageable3 =  new PageRequest(pageNo, pageSize, new Sort(Sort.Direction.DESC, "lastname") .and(new Sort(Sort.Direction.ASC, "age")));
		
		// using both pagination and sorting
		//Pageable pageable = PageRequest(pageNo,pageSize,Direction.ASC,sortBy)
		Pageable pageable =   PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		//Pageable sortedByFirstname = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		//Pageable sortedByLastname = PageRequest.of(pageNo, pageSize, Sort.by("lastname"));
		//Pageable sortedByAgeDesc =  PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		//Pageable sortedByAgeeDescFirstnameAsc =  PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending().and(Sort.by("firstname")));
		return userService.getAllUsersWithPaginationAndSorting(pageable);
	}
	
	/**
	 * ################################################################################################################################################################################
	 * Insert/POST  Operation
	 * ################################################################################################################################################################################
	 */
	@PostMapping(value = "/save1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PostMapping(value = "/save1", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	// @RequestMapping(value = "/add-user", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })//OK
	public User saveUser1(@Valid @RequestBody User user) {
		return userService.saveUser(user);
	}

	@RequestMapping(value = "/save2", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE },produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> saveUser2(@Valid @RequestBody User user, UriComponentsBuilder ucBuilder) {
		return userService.saveUser2(user);
	}

	/**
	 * ####################################################################################################################################################################################################
	 * Update/PUT Operation
	 * ####################################################################################################################################################################################################
	 */

	//@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,	MediaType.APPLICATION_XML_VALUE })
	@PutMapping(value = "/update/{id}", consumes =  MediaType.APPLICATION_JSON_VALUE,produces =  MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@Valid @RequestBody User user, @PathVariable Integer id) {
		Optional<User> ou = userService.getUserById2(id);
		if (!ou.isPresent())
			throw new UserNotFoundException("User not exists");
		user.setId(id);
		return userService.updateUser(user,id);
		// return ResponseEntity.noContent().build();
	}

	/*@PutMapping(value = "/update-this-user/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> updateThisUser(@Valid @RequestBody User u, @PathVariable Integer id) {
		
		User cu = userService.getUserById2(id);
		
		if ( null == cu)
			return new ResponseEntity<>(new UserNotFoundException("User not exists with id:" + id),	HttpStatus.NOT_FOUND);
		
		cu.setFirstname(u.getFirstname());
		cu.setLastname(u.getLastname());
		cu.setAge(u.getAge());
		cu.setEmail(u.getEmail());
		
		User userUpdated = userService.addUser(cu);
		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
	}*/
	
	// update or save
    @PutMapping("/updateOrSave/{id}")
    public User updateOrSaveUser(@RequestBody User newUser, @PathVariable Integer id) {
        return userService.updateOrSave(newUser, id);
    }

	/**
	 * ####################################################################################################################################################################################################
	 * Update/Patch Operation
	 * ####################################################################################################################################################################################################
	 */
	
	//@PatchMapping(value = "/patch1/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    @PatchMapping(value = "/patch1/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@RequestMapping(value = "/patch1/{id}", method = RequestMethod.PATCH)
	public User patchUser1(@Valid @RequestBody User user, @PathVariable Integer id) {
		return userService.updateUser(user, id);
	}
	
	// update email only, not other fields
    @PatchMapping("/patchEmailOnly/{id}")
    //@RequestMapping(value = "/patchEmailOnly/{id}", method = RequestMethod.PATCH)
    User patchUserEmailOnly(@RequestBody Map<String, String> update, @PathVariable Integer id) {
        return userService.patchEmailOnly(update,id);
    }
	
	/**
	 * ####################################################################################################################################################################################################
	 * Remove/DELETE Operation
	 * ####################################################################################################################################################################################################
	 * 
	 */
    
    //@DeleteMapping(value = "/deleteuser1")
    @RequestMapping(value = "/deleteuser1", method = RequestMethod.DELETE)
	public void deleteUserById1(@RequestParam(value = "id", defaultValue = "0",required=false) Integer id) {
		 userService.deleteUserById1(id);
	}

	//@DeleteMapping(value = "/deleteuser3/{id}")
	@RequestMapping(value = "/deleteuser3/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteUserById3(@PathVariable Integer id) {
		Optional<User> ou = userService.getUserById2(id);
		//we should not exception here, we are throwing this exception from service layer.
		if (!ou.isPresent())
			return new ResponseEntity<>(new UserNotFoundException("User not exists with id :" + id),	new HttpHeaders(),HttpStatus.NOT_FOUND);
		else
		return userService.deleteUserById3(id);
		// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	//@DeleteMapping(value = "/deleteuser2/{id}")
	@RequestMapping(value = "/deleteuser2/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUserById2(@PathVariable Integer id) {
		return userService.deleteUserById2(id);
	}

	// @DeleteMapping("/deleteuser4/{id}")
	@RequestMapping(value = "/deleteuser4/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUserById4(@PathVariable Integer id) {
		return userService.deleteUserById2(id);
	}

	// @DeleteMapping(value = "/delete/all")
	// @DeleteMapping("/delete/all")
	@RequestMapping(value = "/delete/all", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllUsers() {
		return userService.deleteAllUsers3();
	}
	
	/**
	 * ####################################################################################################################################################################################################
	 * Exception Handling
	 * ####################################################################################################################################################################################################
	 *
	 */
	//This class has moved to ControllerAdvice 
	/*
	 * @ExceptionHandler(ValidationException.class) ResponseEntity<String>
	 * exceptionHandler(ValidationException ex){ return new
	 * ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST); }
	 */
	
	//OR
	
	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class)
	 * ResponseEntity<List<ErrResponse>>
	 * exceptionHandler(MethodArgumentNotValidException ex){ List<FieldError>
	 * fieldErrors = ex.getBindingResult().getFieldErrors(); List<ErrResponse> list
	 * = fieldErrors.stream().map(fieldError -> new
	 * ErrResponse(fieldError.getField(),fieldError.getDefaultMessage())).collect(
	 * Collectors.toList()); return new
	 * ResponseEntity<>(list,HttpStatus.BAD_REQUEST); }
	 */
}

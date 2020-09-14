package com.springboot.restcontroller;
/*package com.springboot.restcontroller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.entity.User;
import com.springboot.service.UserService;


// @SpringBootTest
 * //@AutoConfigureMockMvc
@RunWith(SpringRunner.class)

@WebMvcTest(value=UserRestController.class)
public class UserRestControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	//if we are not using @AutoConfigureMockMvc at class level and @Autowired for mockMvc then use below.
	@Autowired
	WebApplicationContext webApplicationContext;
	
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	private static List<User> userList;
	@BeforeClass
	public static void setUp() {
	
		//Some Dummy data for testing purpose
		userList.add(new User(1,"punyasmruti", 40, "punyasmruti@gmail.com", new Date(), "9962428121", new Date(), new Date()));
		userList.add(new User(2,"partha", 40, "partha@gmail.com", new Date(), "12345", new Date(), new Date()));
		userList.add(new User(3,"pankaj", 30, "pankaj@gmail.com", new Date(), "54321", new Date(), new Date()));
		
	}
	
	@Test
	public void testHi() {
		
	}
	
	*//**
	 * ########################################################################################################################
	 * Select Operation Test
	 * ########################################################################################################################
	 *//*
	
	@Test
	public void testGetUserById() throws Exception {
		User user1 = new User(1,"punyasmruti", 40, "punyasmruti@gmail.com", new Date(), "9962428121", new Date(), new Date());
		
		BDDMockito.given(userService.getUserById(1)).willReturn(Optional.of(user1));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get-user/1").accept(MediaType.APPLICATION_JSON_VALUE);
		 mockMvc.perform(requestBuilder)
		 .andExpect(MockMvcResultMatchers.status().isOk())
		 .andExpect(MockMvcResultMatchers.jsonPath("$.id",CoreMatchers.is(1)))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.email",CoreMatchers.is("punyasmruti@gmail.com")));
		
	}
	
	@Test
	public void testUserNotFound() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get-user/99");
		 mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	
	@Test
	public void testGetAllUsers_1() throws Exception {
		
		//Mockito.when(userService.getAllUsers()).thenReturn(Collections.emptyList());
		
		User user1 = new User(1,"punyasmruti", 40, "punyasmruti@gmail.com", new Date(), "9962428121", new Date(), new Date());
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get-all-users").accept(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		
		Assert.assertEquals(200,status);
		
		Mockito.verify(userService).getAllUsers();
	}
	
	@Test
	public void testGetAllUsers_2() throws Exception {
		User user1 = new User("punya", 40);
		
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		
		BDDMockito.given(userService.getAllUsers()).willReturn(userList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/get-all-users").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().json("[{'firstname':'punya','age':40}]"));
		
	}
	

	@Test
	public void testGetAllUsers_3() throws Exception {
		Mockito.when(userService.getAllUsers()).thenReturn(Collections.emptyList());
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/get-all-users").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(204,status); //204 - No Content
		Mockito.verify(userService).getAllUsers();
	}
	
	
	*//**
	 * ########################################################################################################################
	 * Insert Operation Test
	 * ########################################################################################################################
	 *//*
	
	@Test
	public void testCreateUser() throws Exception {
		
		User mockUser = new User(1,"punya",40,"punya@gmail.com",new Date(),"12345",new Date(),new Date());
		String jsonInStr = this.mapToJson(mockUser);
		Mockito.when(userService.addUser(Mockito.any(User.class))).thenReturn(mockUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-user").accept(MediaType.APPLICATION_JSON).content(jsonInStr).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		
		int status = result.getResponse().getStatus();
		Assert.assertEquals(200, status);
		Assert.assertEquals(HttpStatus.OK.value(), status);
		
		//OR
		
		MockHttpServletResponse response = result.getResponse();
		String jsonOutStr = response.getContentAsString();
		
		//assertThat(jsonOutStr).isEqualTo(jsonInStr);
		Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());
		
		//Assert.assertEquals("http://localhost:8081/add-user",response.getHeader(HttpHeaders.LOCATION));
	}
	
	*//**
	 * ########################################################################################################################
	 * Update Operation Test
	 * ########################################################################################################################
	 *//*
	
	@Test
	public void testUpdateUser() throws Exception {
		
		User user = new User(1,"punya",40,"punya@gmail.com",new Date(),"12345",new Date(),new Date());
		String jsonInStr = this.mapToJson(user);
		
		Mockito.when(userService.addUser(Mockito.any(User.class))).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/update-user/1").accept(MediaType.APPLICATION_JSON).content(jsonInStr).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		int status = result.getResponse().getStatus();
		Assert.assertEquals(200, status);
	
	}
	 
	
	*//**
	 * ########################################################################################################################
	 * Delete Operation Test
	 * ########################################################################################################################
	 * @throws Exception 
	 *//*
	@Test
	public void testDeleteUser() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/del-this-user/1")).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
	}

	//****************************************************************************************************************************
	private String mapToJson(Object obj) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
}
*/
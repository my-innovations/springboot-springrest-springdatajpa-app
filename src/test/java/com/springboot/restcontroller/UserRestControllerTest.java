package com.springboot.restcontroller;

/*
import org.junit.Before;

import com.springboot.AbstractTest2;

public class UserRestControllerTest extends AbstractTest2 {
	
	@Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	
	@Test
	   public void getUsersList() throws Exception {
	      String uri = "/users";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      User[] userlist = super.mapFromJson(content, User[].class);
	      assertTrue(userlist.length > 0);
	   }
	
	
	@Test
	   public void createUser() throws Exception {
	      String uri = "/users";
	      User u = new User();
	      u.setId(1);
	      u.setFirstname("punya");
	      u.setLastname("nayak");
	      u.setEmail("punya@gmail.com");
	      u.setAge(40);
	      
	      String inputJson = super.mapToJson(u);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(201, status);
	    
	      //String content = mvcResult.getResponse().getContentAsString();
	      //assertEquals(content, "User is created successfully");
	   }
	
	@Test
	   public void updateProduct() throws Exception {
	      String uri = "/products/2";
	      Product product = new Product();
	      product.setName("Lemon");
	      String inputJson = super.mapToJson(product);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Product is updated successsfully");
	   }
	   @Test
	   public void deleteProduct() throws Exception {
	      String uri = "/products/2";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Product is deleted successsfully");
	   }
}
*/
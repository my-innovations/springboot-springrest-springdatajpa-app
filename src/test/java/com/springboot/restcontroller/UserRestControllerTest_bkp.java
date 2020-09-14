package com.springboot.restcontroller;
/*
 * package com.springboot.controller;
 * 
 * import static org.junit.Assert.assertEquals; import static
 * org.junit.Assert.assertTrue;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.springframework.http.MediaType; import
 * org.springframework.test.web.servlet.MvcResult; import
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 * 
 * import com.springboot.AbstractTest; import com.springboot.entity.User;
 * 
 * public class UserRestControllerTest extends AbstractTest {
 * 
 * @Override
 * 
 * @Before public void setUp() { super.setUp(); }
 * 
 * @Test public void getUsersList() throws Exception { String uri = "/users";
 * MvcResult mvcResult =
 * mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.
 * APPLICATION_JSON_VALUE)) .andReturn();
 * 
 * int status = mvcResult.getResponse().getStatus(); assertEquals(200, status);
 * 
 * String content = mvcResult.getResponse().getContentAsString(); User[]
 * userlist = super.mapFromJson(content, User[].class);
 * assertTrue(userlist.length >= 0); }
 * 
 * @Test public void createUser() throws Exception { String uri =
 * "/create-user"; User u = new User();
 * 
 * u.setName("punya"); u.setAge(35); u.setEmail("punya@gmail.com");
 * //u.setDob("11/05/1980"); u.setMobile_no("996-242-8121"); //
 * u.setCreatedAtDate(); //u.setUpdatedAtDate();
 * 
 * 
 * String inputJson = super.mapToJson(u);
 * 
 * MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
 * .contentType(MediaType.APPLICATION_JSON_VALUE)
 * .content(inputJson)).andReturn();
 * 
 * int status = mvcResult.getResponse().getStatus();
 * 
 * assertEquals(201, status);
 * 
 * //String content = mvcResult.getResponse().getContentAsString(); //
 * assertEquals(content, "User is created successfully"); } }
 * //Refer:https://www.tutorialspoint.com/spring_boot/
 * spring_boot_rest_controller_unit_test.htm
 */
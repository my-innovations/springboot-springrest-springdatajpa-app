package com.springboot.restcontroller;
/*package com.springboot.restcontroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springboot.SpringBootSpringRestApp;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootSpringRestApp.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRestControllerITTests {

	@Autowired
	MockMvc mockMvc;
	
	//This is intigretion Test. Here we are actually calling to service and dao then DB and getting the data. Here there is no Mocking
	//This will get data from data.sql file.
	@Test
	public void testGetAllUsers() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/get-all-users")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		//Assert.assertEquals(204, status);
		Assert.assertEquals(200, status);
		System.out.println("======>"+mvcResult.getResponse().getContentAsString());
	}
}

*/
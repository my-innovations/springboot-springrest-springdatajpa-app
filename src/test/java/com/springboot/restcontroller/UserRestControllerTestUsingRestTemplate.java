package com.springboot.restcontroller;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserRestControllerTestUsingRestTemplate {
	
	@LocalServerPort
	int randomServerPort;
	
	@Test
	public void getAllUsers() throws URISyntaxException{
		RestTemplate t= new RestTemplate();
		URI uri = new URI("http://localhost:"+randomServerPort+"/get-users");
		ResponseEntity<String> result = t.getForEntity(uri, String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
}

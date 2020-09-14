package com.springboot.restcontroller;
/*package com.springboot.restcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springboot.entity.Item;
import com.springboot.service.ItemService;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ItemService itemService;
	
	@Test
	public void dummyItem_basic() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/dummy-item")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
				.andReturn();
		
		JSONAssert.assertEquals("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100,\"value\":0}", result.getResponse().getContentAsString(), false);
		
	}

	@Test
	public void itemFromBusinessService_basic() throws Exception {
		
		when(itemService.retreiveHardcodedItem()).thenReturn(new Item(2,"Item2",10,10));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/item-from-business-service")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":2,\"name\":\"Item2\",\"price\":10,\"quantity\":10}"))
				.andReturn();
		
		JSONAssert.assertEquals("{\"id\":2,\"name\":\"Item2\",\"price\":10,\"quantity\":10,\"value\":0}", result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void retrieveAllItems_basic() throws Exception {
		when(itemService.retrieveAllItems()).thenReturn(
				Arrays.asList(new Item(2,"Item2",10,10),
						new Item(3,"Item3",20,20))
				);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/all-items-from-database")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[{id:3,name:Item3,price:20}, {id:2,name:Item2,price:10}]"))
				.andReturn();
		
		//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}

	@Test
	public void retrieveAllItems_noitems() throws Exception {
		when(itemService.retrieveAllItems()).thenReturn(Arrays.asList());
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/all-items-from-database")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[]"))
				.andReturn();
		
		//JSONAssert.assertEquals("[{id:10001,name:Item1,price:5,quantity:10}]", result.getResponse().getContentAsString(), false);
		
	}

}
*/
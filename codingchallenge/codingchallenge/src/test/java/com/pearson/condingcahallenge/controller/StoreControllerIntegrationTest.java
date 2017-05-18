package com.pearson.condingcahallenge.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pearson.codingchallenge.application.Application;
import com.pearson.codingchallenge.bean.StoreData;
import com.pearson.codingchallenge.controller.StoreController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class StoreControllerIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetStore() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stores/1525eec4-7bed-4597-bf5a-e06fcede5f4f"))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.postCode").value("AB11 5PS"));
	}

	@Test
	public void testGetStoreWithNonExistentStore() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stores/1525eec4-7bed-4597-bf5a-e06fcede5f4fxys"))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message")
						.value("Store with id1525eec4-7bed-4597-bf5a-e06fcede5f4fxys does not exist"));

	}

	@Test
	public void testGetStoresByOpenedDate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stores?orderBy=OPENED_DATE"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(186)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].postCode").value("B23 6SA"));

	}

	@Test
	public void testGetStoresByCity() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stores")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(186)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].postCode").value("CF44 7DG"));

	}

	@Test
	public void testGetStoreWithWrongParam() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stores?orderBy=storeId"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid value for orderBy parameter"));

	}

	@Test
	public void testCreateStore() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/stores").contentType(MediaType.APPLICATION_JSON)
                .content("{\"storeId\": \"testStore\"}")).andExpect(MockMvcResultMatchers.status().isCreated());
	}

}

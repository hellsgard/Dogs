package com.hg.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hg.domain.Dog;

// boots the entire context - random port to avoid collisions (two apps running on the same)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMVC object
@Sql(scripts = { "classpath:dog-schema.sql",
		"classpath:dog-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class DogControllerIntegrationTest {

	@Autowired // pulls the MockMVC object from the context
	private MockMvc mvc; // class that perform the request (kind of a postman equivalent)

	@Autowired
	private ObjectMapper mapper; // Converts to JSON

	@Test
	void testCreate() throws Exception {

		Dog testDog = new Dog("Jack", "Jack Russel", 15);
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);

		Dog testCreatedDog = new Dog(2, "Jack", "Jack Russel", 15);
		String testCreatedDogAsJSON = this.mapper.writeValueAsString(testCreatedDog);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedDogAsJSON);

		// sends request - checks the status - checks the body
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void getAllTest() throws Exception {
//		List<Dog> testDogs = new ArrayList<>();
//		testDogs.add(new Dog(1, "Testdog", "Rottweiler", 5));
////		String testDogsAsJSON = this.mapper.writeValueAsString(testDogs);
//		String content = this.mvc.perform(request(HttpMethod.GET, "/getAll").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
////		RequestBuilder req = post("/getAll").contentType(MediaType.APPLICATION_JSON).content(testDogsAsJSON);
////
////		ResultMatcher checkStatus = status().isOk();
////		ResultMatcher checkBody = content().json(testDogsAsJSON);
//
////		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
//
//		assertEquals(this.mapper.writeValueAsString(testDogs), content);

		List<Dog> testDogs = List.of(new Dog(1, "Testdog", "Rottweiler", 5));
		String json = this.mapper.writeValueAsString(testDogs);

		RequestBuilder req = get("/getAll");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void getByIdTest() throws Exception {
		Dog testDog = new Dog(1, "Testdog", "Rottweiler", 5);
		String json = this.mapper.writeValueAsString(testDog);

		RequestBuilder req = get("/get/1");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void getByNameTest() throws Exception {
		List<Dog> testDogs = List.of(new Dog(1, "Testdog", "Rottweiler", 5));
		String json = this.mapper.writeValueAsString(testDogs);

		RequestBuilder req = get("/getByName/Testdog");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void getByBreedTest() throws Exception {
		List<Dog> testDogs = List.of(new Dog(1, "Testdog", "Rottweiler", 5));
		String json = this.mapper.writeValueAsString(testDogs);

		RequestBuilder req = get("/getByBreed/Rottweiler");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}


	@Test
	void updateTest() throws Exception {
		Dog updateDog = new Dog(1, "Gerald", "Pug", 6);
		String json = this.mapper.writeValueAsString(updateDog);

		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(json);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testDelete() throws Exception {
//		Dog deleteDog = new Dog(1, "Testdog", "Rottweiler", 5);
//		String json = this.mapper.writeValueAsString(deleteDog);

		RequestBuilder req = delete("/remove/1");

		ResultMatcher checkStatus = status().isNoContent();

		this.mvc.perform(req).andExpect(checkStatus);

	}

}

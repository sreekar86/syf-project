package com.syf.project;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.syf.proj.dto.request.user.UserInfoRequestDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest extends AbstractTest {

	@Test
	public void test01_createUser_whenMockMVC_thenVerifyResponse() throws Exception {
		String uri = "/user/create";

		UserInfoRequestDTO request = new UserInfoRequestDTO("testuser1", "12345", "TestFirst1", "TestLast1", "M",
				"testuser1@test.com", "91898998");
		String inputJson = mapToJson(request);
		mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.userName", is(request.getUserName())))
				.andExpect(jsonPath("$.firstName", is(request.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(request.getLastName())))
				.andExpect(jsonPath("$.gender", is(request.getGender())))
				.andExpect(jsonPath("$.email", is(request.getEmail())))
				.andExpect(jsonPath("$.phoneNumber", is(request.getPhoneNumber())))
				.andExpect(jsonPath("$.insertedTs", notNullValue())).andExpect(jsonPath("$.modifiedTs", notNullValue()))
				.andDo(print());
	}

	@Test
	public void test02_createUser_whenMockMVC_thenResourceConflictError() throws Exception {
		String uri = "/user/create";

		UserInfoRequestDTO request = new UserInfoRequestDTO("testuser1", "12345", "TestFirst1", "TestLast1", "M",
				"testuser1@test.com", "91898998");
		String inputJson = mapToJson(request);
		mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isConflict())
				.andExpect(jsonPath("$.key").value("RESOURCE_CONFLICT"))
				.andExpect(jsonPath("$.description").value("User already exists")).andDo(print());
	}

	@Test
	public void test01_getProfile_whenMockMVC_thenVerifyResponse() throws Exception {

		String uri = "/user/{userName}/my-profile";

		mockMvc.perform(MockMvcRequestBuilders.get(uri, "testuser1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.userName").value("testuser1"))
				.andExpect(jsonPath("$.firstName").value("TestFirst1"))
				.andExpect(jsonPath("$.lastName").value("TestLast1")).andExpect(jsonPath("$.gender").value("M"))
				.andExpect(jsonPath("$.email").value("testuser1@test.com"))
				.andExpect(jsonPath("$.phoneNumber").value("91898998")).andExpect(jsonPath("$.insertedTs").exists())
				.andExpect(jsonPath("$.modifiedTs").exists()).andDo(print());
	}

	@Test
	public void test02_getProfile_whenMockMVC_thenUserNotFoundError() throws Exception {

		String uri = "/user/{userName}/my-profile";

		mockMvc.perform(MockMvcRequestBuilders.get(uri, "sssss").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.key").value("RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.description").value("Resource could not be found")).andDo(print());
	}

	@Test
	public void test03_getImages_whenMockMVC_thenInvalidUserError() throws Exception {

		String uri = "/user/{userName}/images";

		mockMvc.perform(MockMvcRequestBuilders.get(uri, "sssss").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.key").value("AUTHENTICATION_FAILED"))
				.andExpect(jsonPath("$.description").value("User not found")).andDo(print());

	}
}

package com.syf.project;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.syf.proj.dto.response.image.ImageDeleteResponseDTO;
import com.syf.proj.dto.response.image.ImageResponseDTO;
import com.syf.proj.exception.InvalidUserException;
import com.syf.proj.exception.ResourceNotFoundException;
import com.syf.proj.exception.UnauthorizedException;
import com.syf.proj.service.image.ImageService;
import com.syf.proj.service.user.UserInfoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageControllerTest extends AbstractTest {

	@MockBean
	ImageService imageService;

	@MockBean
	UserInfoService userService;

	@Test
	public void test01_viewImage_whenMockMVC_thenVerifyResponse() throws Exception {

		String uri = "/image/{imageId}/view/{userName}";

		ImageResponseDTO mockResponse = new ImageResponseDTO(1, "http://www.imgur.com/image1", "image/png",
				"07/22/2019 05:21:33");

		Mockito.when(imageService.viewImage(Mockito.anyInt(), Mockito.anyString())).thenReturn(mockResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri, 1, "test").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());

		String expected = "{\"imageId\":1,\"imageUrl\":\"http://www.imgur.com/image1\",\"imageType\":\"image/png\",\"insertedTs\":\"07/22/2019 05:21:33\"}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void test02_viewImage_whenMockMVC_thenResourceNotFoundError() throws Exception {

		String uri = "/image/{imageId}/view/{userName}";

		Mockito.when(imageService.viewImage(Mockito.anyInt(), Mockito.anyString()))
				.thenThrow(new ResourceNotFoundException("Image not found"));

		mockMvc.perform(MockMvcRequestBuilders.get(uri, 11, "test").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.key").value("RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.description").value("Image not found")).andDo(print());

	}

	@Test
	public void test03_viewImage_whenMockMVC_thenInvalidUserError() throws Exception {

		String uri = "/image/{imageId}/view/{userName}";

		Mockito.when(imageService.viewImage(Mockito.anyInt(), Mockito.anyString()))
				.thenThrow(new InvalidUserException("User not found"));

		mockMvc.perform(MockMvcRequestBuilders.get(uri, 11, "test").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.key").value("AUTHENTICATION_FAILED"))
				.andExpect(jsonPath("$.description").value("User not found")).andDo(print());

	}

	@Test
	public void test04_viewImage_whenMockMVC_thenUnauthorizedError() throws Exception {

		String uri = "/image/{imageId}/view/{userName}";

		Mockito.when(imageService.viewImage(Mockito.anyInt(), Mockito.anyString()))
				.thenThrow(new UnauthorizedException("You are not authorized to view the resource"));

		mockMvc.perform(MockMvcRequestBuilders.get(uri, 11, "test").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.key").value("UNAUTHORIZED"))
				.andExpect(jsonPath("$.description").value("You are not authorized to view the resource"))
				.andDo(print());

	}

	@Test
	public void test01_deleteImage_whenMockMVC_thenVerifyResponse() throws Exception {

		String uri = "/image/{imageId}/delete/{userName}";

		ImageDeleteResponseDTO mockResponse = new ImageDeleteResponseDTO("true", "200");

		Mockito.when(imageService.deleteImage(Mockito.anyInt(), Mockito.anyString())).thenReturn(mockResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uri, 1, "test")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());

		String expected = "{\"success\":\"true\",\"status\":\"200\"}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void test02_deleteImage_whenMockMVC_thenResourceNotFoundError() throws Exception {

		String uri = "/image/{imageId}/delete/{userName}";

		Mockito.when(imageService.deleteImage(Mockito.anyInt(), Mockito.anyString()))
				.thenThrow(new ResourceNotFoundException("Image not found"));

		mockMvc.perform(MockMvcRequestBuilders.delete(uri, 11, "test").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.key").value("RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.description").value("Image not found")).andDo(print());

	}

	@Test
	public void test03_deleteImage_whenMockMVC_thenInvalidUserError() throws Exception {

		String uri = "/image/{imageId}/delete/{userName}";

		Mockito.when(imageService.deleteImage(Mockito.anyInt(), Mockito.anyString()))
				.thenThrow(new InvalidUserException("User not found"));

		mockMvc.perform(MockMvcRequestBuilders.delete(uri, 11, "test").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.key").value("AUTHENTICATION_FAILED"))
				.andExpect(jsonPath("$.description").value("User not found")).andDo(print());

	}

	@Test
	public void test04_deleteImage_whenMockMVC_thenUnauthorizedError() throws Exception {

		String uri = "/image/{imageId}/delete/{userName}";

		Mockito.when(imageService.deleteImage(Mockito.anyInt(), Mockito.anyString()))
				.thenThrow(new UnauthorizedException("You are not authorized to view the resource"));

		mockMvc.perform(MockMvcRequestBuilders.delete(uri, 11, "test").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.key").value("UNAUTHORIZED"))
				.andExpect(jsonPath("$.description").value("You are not authorized to view the resource"))
				.andDo(print());

	}
}

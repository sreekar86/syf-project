package com.syf.proj.controller.user;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.syf.proj.dto.request.user.UserInfoRequestDTO;
import com.syf.proj.dto.response.image.ImageResponseDTO;
import com.syf.proj.dto.response.user.ProfileResponseDTO;
import com.syf.proj.exception.CommonExcpetion;
import com.syf.proj.service.user.IUserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author sreekar
 *
 */
@RestController
@RequestMapping("/user")
@Api(value = "Operations pertaining to Users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	IUserInfoService userInfoService;

	/**
	 * This method is used to create a new user
	 * 
	 * @param request
	 * @return
	 * @throws CommonExcpetion
	 */
	@ApiOperation("Creates a new user")

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created new user"),
			@ApiResponse(code = 409, message = "The request could not be completed due to a conflict with the current state of the resource") })

	@PostMapping("/create")
	public ResponseEntity<ProfileResponseDTO> createUser(@Validated @RequestBody UserInfoRequestDTO request)
			throws CommonExcpetion {

		logger.info("Executing createUser method...");

		return new ResponseEntity<ProfileResponseDTO>(userInfoService.createUser(request), HttpStatus.CREATED);

	}

	/**
	 * This method is used to fetch the basic details of user with provided username
	 * 
	 * @param userName
	 * @return
	 * @throws CommonExcpetion
	 */
	@ApiOperation("Finds user info by user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully fetched user profile"),
			@ApiResponse(code = 409, message = "The resource you were trying to reach is not found") })
	@GetMapping("/{userName}/my-profile")
	public ResponseEntity<ProfileResponseDTO> getUserProfile(@NotBlank @PathVariable("userName") String userName)
			throws CommonExcpetion {

		logger.info("Executing getUserProfile method...");

		return new ResponseEntity<ProfileResponseDTO>(userInfoService.getUserInfo(userName), HttpStatus.OK);

	}

	/**
	 * This method is used to fetch all uploaded images of the user
	 * 
	 * @param userName
	 * @return
	 * @throws CommonExcpetion
	 */
	@ApiOperation("Finds images by user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of images or no images"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden") })
	@GetMapping("/{userName}/images")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ImageResponseDTO>> getImages(@NotBlank @PathVariable("userName") String userName)
			throws CommonExcpetion {

		logger.info("Executing getImages method...");

		return new ResponseEntity<List<ImageResponseDTO>>(userInfoService.getImages(userName), HttpStatus.OK);

	}
}

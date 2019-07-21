package com.syf.proj.controller.image;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.syf.proj.dto.response.image.ImageDeleteResponseDTO;
import com.syf.proj.dto.response.image.ImageResponseDTO;
import com.syf.proj.exception.CommonExcpetion;
import com.syf.proj.service.image.IImageService;

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
@RequestMapping("/image")
@Api(value = "Operations pertaining to Images")
public class ImageController {

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	IImageService imageService;

	@ApiOperation("Uploads an image to the gallery")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully uploaded image"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource") })
	@PostMapping("/upload/{userName}")
	public ResponseEntity<ImageResponseDTO> uploadImage(@NotBlank @RequestParam("file") MultipartFile file,
			@NotBlank @PathVariable("userName") String userName) throws CommonExcpetion {

		logger.info("Executing uploadImage method...");

		return new ResponseEntity<ImageResponseDTO>(imageService.uploadImage(file, userName), HttpStatus.ACCEPTED);
	}

	@ApiOperation("Finds an image from gallery")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully fetched image"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/{imageId}/view/{userName}")
	public ResponseEntity<ImageResponseDTO> viewImage(@NotBlank @PathVariable("imageId") Integer imageId,
			@NotBlank @PathVariable("userName") String userName) throws CommonExcpetion {

		logger.info("Executing viewImage method...");

		return new ResponseEntity<ImageResponseDTO>(imageService.viewImage(imageId, userName), HttpStatus.OK);
	}

	@ApiOperation("Deletes an image from gallery")
	@DeleteMapping("/{imageId}/delete/{userName}")
	public ResponseEntity<ImageDeleteResponseDTO> deleteImage(@NotBlank @PathVariable("imageId") Integer imageId,
			@NotBlank @PathVariable("userName") String userName) throws CommonExcpetion {

		logger.info("Executing deleteImage method...");

		return new ResponseEntity<ImageDeleteResponseDTO>(imageService.deleteImage(imageId, userName), HttpStatus.OK);
	}

}

package com.syf.proj.service.imgur;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.syf.proj.dto.response.imgur.ImgurDeleteResponseDTO;
import com.syf.proj.dto.response.imgur.ImgurResponseDTO;
import com.syf.proj.exception.CommonExcpetion;
import com.syf.proj.exception.UnknownException;
import com.syf.proj.service.image.ImageService;

/**
 * 
 * @author sreekar
 *
 */
@Component
public class ImgurService implements IImgurService {

	private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	@Value("${imgur.api.url.image}")
	private String imgUrl;

	@Value("${imgur.api.url.upload}")
	private String uploadUrl;

	@Value("${imgur.api.auth.token}")
	private String authToken;

	@Override
	public ImgurResponseDTO uploadImage(MultipartFile file) throws CommonExcpetion {
		ImgurResponseDTO uploadImgResp = null;
		try {
			logger.info("Calling imgur image upload api..");
			long startTime = System.currentTimeMillis();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.add("Authorization", "Bearer " + authToken);
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			Base64.Encoder encoder = Base64.getEncoder();
			body.add("image", encoder.encode(file.getBytes()));

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			uploadImgResp = restTemplate.postForObject(uploadUrl, requestEntity, ImgurResponseDTO.class);
			long stopTime = System.currentTimeMillis();
			logger.info("{} milli seconds time taken to upload an image to imgur gallery", stopTime - startTime);
		} catch (Exception e) {
			logger.error("Error occured whilecalling imgur image upload api", e.getMessage());
			throw new UnknownException(e.getMessage());
		}

		return uploadImgResp;
	}

	@Override
	public ImgurResponseDTO viewImage(String id) throws CommonExcpetion {
		ImgurResponseDTO viewImgResp = null;
		ResponseEntity<ImgurResponseDTO> response = null;
		try {
			logger.info("Calling imgur image view api..");
			long startTime = System.currentTimeMillis();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + authToken);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			response = restTemplate.exchange(imgUrl + id, HttpMethod.GET, entity, ImgurResponseDTO.class);
			viewImgResp = response.getBody();
			long stopTime = System.currentTimeMillis();
			logger.info("{} milli seconds time taken to view an image from imgur gallery", stopTime - startTime);
		} catch (Exception e) {
			logger.error("Error occured while calling imgur image view api", e.getMessage());
			throw new UnknownException(e.getMessage());
		}
		return viewImgResp;
	}

	@Override
	public ImgurDeleteResponseDTO deleteImage(String imgurId) throws CommonExcpetion {
		ImgurDeleteResponseDTO deleteImgResp = null;

		try {
			logger.info("Calling imgur image delete api..");
			long startTime = System.currentTimeMillis();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + authToken);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<ImgurDeleteResponseDTO> response = restTemplate.exchange(imgUrl + imgurId, HttpMethod.DELETE,
					entity, ImgurDeleteResponseDTO.class);
			deleteImgResp = response.getBody();
			long stopTime = System.currentTimeMillis();
			logger.info("{} milli seconds time taken to delete an image from imgur gallery", stopTime - startTime);
		} catch (Exception e) {
			logger.error("Error occured while calling imgur image delete api", e.getMessage());
			throw new UnknownException(e.getMessage());
		}
		return deleteImgResp;
	}

}

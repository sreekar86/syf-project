package com.syf.proj.service.image;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.syf.proj.dto.response.image.ImageDeleteResponseDTO;
import com.syf.proj.dto.response.image.ImageResponseDTO;
import com.syf.proj.dto.response.imgur.ImgurDataDTO;
import com.syf.proj.dto.response.imgur.ImgurDeleteResponseDTO;
import com.syf.proj.dto.response.imgur.ImgurResponseDTO;
import com.syf.proj.exception.CommonExcpetion;
import com.syf.proj.exception.InvalidUserException;
import com.syf.proj.exception.ResourceNotFoundException;
import com.syf.proj.exception.UnauthorizedException;
import com.syf.proj.model.image.Image;
import com.syf.proj.model.user.UserInfo;
import com.syf.proj.repo.image.ImageRepo;
import com.syf.proj.repo.user.UserInfoRepo;
import com.syf.proj.service.imgur.ImgurService;
import com.syf.proj.util.CoreUtil;

/**
 * 
 * @author sreekar
 *
 */
@Service
public class ImageService implements IImageService {

	private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

	@Autowired
	UserInfoRepo userInfoRepo;

	@Autowired
	ImageRepo imageRepo;

	@Autowired
	ImgurService imgurService;

	@Autowired
	private ModelMapper modelMapper;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * Method to upload image to imgur gallery and store the image info into
	 * database
	 */
	@Override
	@Transactional(rollbackOn = { CommonExcpetion.class, Exception.class })
	public ImageResponseDTO uploadImage(MultipartFile file, String username) throws CommonExcpetion {

		// validate user
		logger.info("Validating user..");
		Optional<UserInfo> userInfo = userInfoRepo.findByUserName(username);
		if (!userInfo.isPresent()) {
			logger.error("User not found");
			throw new InvalidUserException("User not found");
		}
		logger.info("Successfully validated user");

		// Upload image to Imgur gallery
		logger.info("Uploading image to Imgur gallery..");
		ImgurResponseDTO imgurResponseDTO = imgurService.uploadImage(file);
		ImgurDataDTO data = imgurResponseDTO.getData();
		logger.info("Successfully uploaded image to Imgur gallery");

		// Save uploaded image info in DB
		logger.info("Saving image info into database..");
		Image image = new Image();
		image.setImgurId(data.getId());
		image.setImageUrl(data.getLink());
		image.setImageType(data.getType());
		image.setInsertedTs(new Date());
		image.setUserInfo(userInfo.get());
		imageRepo.save(image);
		logger.info("Successfully saved image info into database");

		// build response entity
		ImageResponseDTO response = new ImageResponseDTO(image.getImageId(), data.getLink(), data.getType(),
				CoreUtil.convertDateToString(image.getInsertedTs()));

		return response;
	}

	/**
	 * Method to retrieve image info by image id
	 */
	@Override
	public ImageResponseDTO viewImage(Integer imageId, String username) throws CommonExcpetion {
		ImageResponseDTO response = null;
		// validate user
		logger.info("Validating user..");
		Optional<UserInfo> userInfo = userInfoRepo.findByUserName(username);
		if (!userInfo.isPresent()) {
			logger.error("User not found");
			throw new InvalidUserException("User not found");
		} else {
			Optional<Image> image = imageRepo.findByImageId(imageId);
			if (!image.isPresent()) {
				logger.error("Image not found");
				throw new ResourceNotFoundException("Image not found");
			}

			if (userInfo.get().getUserId() != image.get().getUserInfo().getUserId()) {
				logger.error("Unauthorized error");
				throw new UnauthorizedException();
			}

			// View image from Imgur site
			logger.info("Fetching image info from Imgur gallery..");
			ImgurResponseDTO imgurResponseDTO = imgurService.viewImage(image.get().getImgurId());
			ImgurDataDTO data = imgurResponseDTO.getData();
			logger.info("Successfully fetched image info from Imgur gallery");

			// build response entity
			response = new ImageResponseDTO(image.get().getImageId(), data.getLink(), data.getType(),
					CoreUtil.convertDateToString(image.get().getInsertedTs()));
		}
		return response;
	}

	/**
	 * Method to delete image from imgur gallery and database
	 */
	@Override
	@Transactional(rollbackOn = { CommonExcpetion.class, Exception.class })
	public ImageDeleteResponseDTO deleteImage(Integer imageId, String username) throws CommonExcpetion {
		ImageDeleteResponseDTO response = null;
		// validate user
		logger.info("Validating user");
		Optional<UserInfo> userInfo = userInfoRepo.findByUserName(username);
		if (!userInfo.isPresent()) {
			logger.error("User not found");
			throw new InvalidUserException("User not found");
		} else {
			Optional<Image> image = imageRepo.findByImageId(imageId);
			if (!image.isPresent()) {
				logger.error("Image not found");
				throw new ResourceNotFoundException("Image not found");
			}

			if (userInfo.get().getUserId() != image.get().getUserInfo().getUserId()) {
				logger.error("Unauthorized error");
				throw new UnauthorizedException();
			}

			// Delete image from Imgur gallery
			logger.info("Deleting image from Imgur gallery..");
			ImgurDeleteResponseDTO imgurDeleteResponseDTO = imgurService.deleteImage(image.get().getImgurId());
			if (imgurDeleteResponseDTO.getStatus().equals("200")) {
				imageRepo.delete(image.get());
				logger.info("Image successfully deleted from Imgur gallery");
			} else {
				logger.error("Failed to delete image from Imgur gallery");
			}

			// build response entity
			response = modelMapper.map(imgurDeleteResponseDTO, ImageDeleteResponseDTO.class);
		}

		return response;
	}

}

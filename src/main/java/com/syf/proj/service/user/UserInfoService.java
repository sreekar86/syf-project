package com.syf.proj.service.user;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.syf.proj.dto.request.user.UserInfoRequestDTO;
import com.syf.proj.dto.response.image.ImageResponseDTO;
import com.syf.proj.dto.response.user.ProfileResponseDTO;
import com.syf.proj.exception.CommonExcpetion;
import com.syf.proj.exception.InvalidUserException;
import com.syf.proj.exception.ResourceConflictException;
import com.syf.proj.exception.ResourceNotFoundException;
import com.syf.proj.model.image.Image;
import com.syf.proj.model.user.UserInfo;
import com.syf.proj.repo.user.UserInfoRepo;
import com.syf.proj.service.image.ImageService;

/**
 * 
 * @author sreekar
 *
 */
@Service
public class UserInfoService implements IUserInfoService {

	private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

	@Autowired
	UserInfoRepo userInfoRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional(rollbackOn = { CommonExcpetion.class, Exception.class })
	public ProfileResponseDTO createUser(UserInfoRequestDTO request) throws CommonExcpetion {

		Optional<UserInfo> userInfo = userInfoRepo.findByUserName(request.getUserName());
		if (userInfo.isPresent()) {
			logger.error("User already exists");
			throw new ResourceConflictException("User already exists");
		}

		// save user into database
		logger.info("Creating user..");
		UserInfo user = modelMapper.map(request, UserInfo.class);
		Date currentDate = new Date();
		user.setInsertedTs(currentDate);
		user.setModifiedTs(currentDate);
		user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		userInfoRepo.save(user);
		logger.info("Successfully created user");

		// build response entity
		ProfileResponseDTO response = modelMapper.map(user, ProfileResponseDTO.class);

		return response;
	}

	@Override
	public ProfileResponseDTO getUserInfo(String username) throws CommonExcpetion {

		// validate user
		logger.info("Validating user..");
		Optional<UserInfo> userInfo = userInfoRepo.findByUserName(username);
		if (!userInfo.isPresent()) {
			logger.error("User not found");
			throw new ResourceNotFoundException();
		}
		logger.info("Successfully validated user");

		// build response entity
		ProfileResponseDTO response = modelMapper.map(userInfo.get(), ProfileResponseDTO.class);

		return response;
	}

	@Override
	public List<ImageResponseDTO> getImages(String username) throws CommonExcpetion {

		// validate user
		logger.info("Validating user..");
		Optional<UserInfo> userInfo = userInfoRepo.findByUserName(username);
		if (!userInfo.isPresent()) {
			logger.error("User not found");
			throw new InvalidUserException("User not found");
		}
		logger.info("Successfully validated user");

		// build response entity
		List<Image> images = userInfo.get().getImages();
		List<ImageResponseDTO> response = images.stream().map(image -> modelMapper.map(image, ImageResponseDTO.class))
				.collect(Collectors.toList());

		return response;
	}

}

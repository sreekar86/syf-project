package com.syf.proj.service.user;

import java.util.List;

import com.syf.proj.dto.request.user.UserInfoRequestDTO;
import com.syf.proj.dto.response.image.ImageResponseDTO;
import com.syf.proj.dto.response.user.ProfileResponseDTO;
import com.syf.proj.exception.CommonExcpetion;

/**
 * 
 * @author sreekar
 *
 */
public interface IUserInfoService {

	public ProfileResponseDTO createUser(UserInfoRequestDTO request) throws CommonExcpetion;

	public ProfileResponseDTO getUserInfo(String username) throws CommonExcpetion;

	public List<ImageResponseDTO> getImages(String username) throws CommonExcpetion;

}

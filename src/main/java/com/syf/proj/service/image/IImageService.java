package com.syf.proj.service.image;

import org.springframework.web.multipart.MultipartFile;

import com.syf.proj.dto.response.image.ImageDeleteResponseDTO;
import com.syf.proj.dto.response.image.ImageResponseDTO;
import com.syf.proj.exception.CommonExcpetion;

/**
 * 
 * @author sreekar
 *
 */
public interface IImageService {

	public ImageResponseDTO uploadImage(MultipartFile file, String userName) throws CommonExcpetion;

	public ImageResponseDTO viewImage(Integer imageId, String userName) throws CommonExcpetion;

	public ImageDeleteResponseDTO deleteImage(Integer imageId, String userName) throws CommonExcpetion;

}

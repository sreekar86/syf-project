package com.syf.proj.service.imgur;

import org.springframework.web.multipart.MultipartFile;

import com.syf.proj.dto.response.imgur.ImgurDeleteResponseDTO;
import com.syf.proj.dto.response.imgur.ImgurResponseDTO;
import com.syf.proj.exception.CommonExcpetion;

/**
 * 
 * @author sreekar
 *
 */
public interface IImgurService {

	public ImgurResponseDTO uploadImage(MultipartFile file) throws CommonExcpetion;

	public ImgurResponseDTO viewImage(String imgurId) throws CommonExcpetion;

	public ImgurDeleteResponseDTO deleteImage(String imgurId) throws CommonExcpetion;
}

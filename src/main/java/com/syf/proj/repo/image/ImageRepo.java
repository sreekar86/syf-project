package com.syf.proj.repo.image;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syf.proj.model.image.Image;

/**
 * 
 * @author sreekar
 *
 */
@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {

	Optional<Image> findByImageId(Integer imageId);
	
}

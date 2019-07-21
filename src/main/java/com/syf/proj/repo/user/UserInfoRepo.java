package com.syf.proj.repo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syf.proj.model.user.UserInfo;

/**
 * 
 * @author sreekar
 *
 */
@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, String> {

	Optional<UserInfo> findByUserName(String username);

}

package com.phsz.userservice.userserviceprovider.repository;

import com.phsz.userservice.userserviceprovider.entity.AppUser;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

    Page<AppUser> findAll(Pageable pageable);

    AppUser save(AppUser appUser);

    @Transactional
    @Modifying
    @Query("update AppUser u set u.email = :email where u.id = :id")
    int updateUserNormalById(@Param("id") Long id, @Param("email") String email);


    @Transactional
    @Modifying
    @Query("update AppUser u set u.avatar = :fileUrl where u.id = :id")
    int updateUserAvatarById(@Param("id") Long id, @Param("fileUrl") String fileUrl);
}

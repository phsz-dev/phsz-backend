package com.phsz.playservice.playserviceprovider.repository;

import com.phsz.playservice.playserviceprovider.pojo.LearnedSubResponsibility;
import com.phsz.playservice.playserviceprovider.pojo.Role;
import feign.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LearnedSubResponsibilityRepository extends CrudRepository<LearnedSubResponsibility,Long>{

    @Query("SELECT l FROM LearnedSubResponsibility l WHERE l.userId = :userId AND l.subResponsibilityId IN (SELECT sr.id FROM Responsibility r JOIN SubResponsibility sr ON r.id = sr.responsibility.id WHERE r.role = :role)")
    List<LearnedSubResponsibility> findByUserId(@Param("userId") long userId, @Param("role") Role role);

}

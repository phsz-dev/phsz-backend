package com.phsz.roleplayservice.roleplayserviceprovider.repository;

import com.phsz.roleplayservice.roleplayserviceprovider.pojo.LearnedSubResponsibility;
import com.phsz.roleplayservice.roleplayserviceprovider.pojo.Role;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LearnedSubResponsibilityRepository extends CrudRepository<LearnedSubResponsibility,Long>{

    @Query("SELECT l FROM LearnedSubResponsibility l WHERE l.userId = :userId AND l.subResponsibilityId IN (SELECT sr.id FROM Responsibility r JOIN SubResponsibility sr ON r.id = sr.responsibility.id WHERE r.role = :role)")
    List<LearnedSubResponsibility> findByUserId(@Param("userId") long userId, @Param("role") Role role);

}

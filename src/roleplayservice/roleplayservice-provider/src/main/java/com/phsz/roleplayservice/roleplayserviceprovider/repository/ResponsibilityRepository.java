package com.phsz.roleplayservice.roleplayserviceprovider.repository;

import com.phsz.roleplayservice.roleplayserviceprovider.pojo.Responsibility;
import com.phsz.roleplayservice.roleplayserviceprovider.pojo.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ResponsibilityRepository extends JpaRepository<Responsibility, Long>, PagingAndSortingRepository<Responsibility, Long> {
    Page<Responsibility> findAllByNameLike(String responsibilityName, Pageable pageable);

    Page<Responsibility> findAllByRole(Role role, Pageable pageable);

    List<Responsibility> findByRole(Role role);

    @Query("SELECT r FROM Responsibility r LEFT JOIN FETCH r.subResponsibilities sr LEFT JOIN FETCH sr.procedures WHERE r.role = :role")
    List<Responsibility> findFullResponsibilityByRole(Role role);

    Optional<Responsibility> deleteResponsibilityById(Long responsibilityId);

    // 首先join三张表，然后按照responsibility的id分组，判断分组的每一个是否都是已经学了的，如果是的话，就返回这个responsibility的id
    @Query("SELECT r.id FROM Responsibility r JOIN SubResponsibility sr ON r.id = sr.responsibility.id LEFT JOIN LearnedSubResponsibility lsr ON sr.id = lsr.subResponsibilityId WHERE lsr.userId = :userId AND r.role = :inRole GROUP BY r.id HAVING COUNT(DISTINCT sr.id) = (SELECT COUNT(DISTINCT sr2.id) FROM Responsibility r2 JOIN SubResponsibility sr2 ON r2.id = sr2.responsibility.id WHERE r2.id = r.id)")
    List<Long> findLearnedResponsibility(long userId, Role inRole);
}

package com.famly.repository;

import com.famly.entity.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRelationRepository extends JpaRepository<UserRelation, Long> {
    // Use non-ID fields to fetch results
    List<UserRelation> findByUserXid(Long userXid);
    List<UserRelation> findAll();
    //void deleteById(String userXid);
}

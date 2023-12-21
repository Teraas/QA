package com.famly.repository;

import com.famly.entity.User;
import com.famly.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    Optional<UserDetail> findById(Long id);
    List<UserDetail> findAll();
    void deleteById(Long id);
    UserDetail save(User user);

}

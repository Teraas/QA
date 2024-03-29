package com.famly.repository;

import com.famly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    User save(User user);
    //User findByemail(EmailAddress emailAddress);
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findOneByEmail(String email);
}

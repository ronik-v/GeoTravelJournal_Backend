package com.geotraveljournal.app.dao.auth;

import com.geotraveljournal.app.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :userEmail")
    User getByEmail(@Param("userEmail") String userEmail);
}

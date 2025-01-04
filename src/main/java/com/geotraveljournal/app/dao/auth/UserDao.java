package com.geotraveljournal.app.dao.auth;

import com.geotraveljournal.app.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :userEmail")
    User getByEmail(@Param("userEmail") String userEmail);

    @Query("SELECT u FROM User u JOIN UserToken ut ON ut.userId = u.id WHERE ut.token = :requestToken")
    User getByAuthToken(@Param("requestToken") String requestToken);
}
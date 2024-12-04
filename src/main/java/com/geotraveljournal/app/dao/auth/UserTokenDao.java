package com.geotraveljournal.app.dao.auth;

import com.geotraveljournal.app.model.auth.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenDao extends JpaRepository<UserToken, Long> {
    @Query("SELECT ut FROM UserToken ut WHERE ut.userId = :currentUserId")
    UserToken getCurrentUserToken(@Param("currentUserId") Long currentUserId);

    @Modifying
    @Query("UPDATE UserToken ut SET ut.token = :newToken WHERE ut.userId = :currentUserId")
    void updateUserToken(@Param("newToken") String newToken, @Param("currentUserId") Long currentUserId);
}

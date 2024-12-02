package com.geotraveljournal.app.service.auth;

import com.geotraveljournal.app.dao.auth.UserDao;
import com.geotraveljournal.app.dao.auth.UserTokenDao;
import com.geotraveljournal.app.dto.auth.UserDto;
import com.geotraveljournal.app.model.auth.User;
import com.geotraveljournal.app.model.auth.UserToken;
import com.geotraveljournal.app.utils.UserPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    public UserDto add(String email, String password) {
        if (userDao.getByEmail(email) != null) {
            throw new RuntimeException("User with email " + email + " already exists.");
        }

        UserPassword userPassword = new UserPassword(password, "TODO");
        User user = new User(email, userPassword.hashed());
        user = userDao.save(user);

        String token = UUID.randomUUID().toString();
        UserToken userToken = new UserToken(token, user.getId());
        userToken = userTokenDao.save(userToken);

        return new UserDto(user.getId(), user.getEmail(), userToken.getToken(), user.getCreatedAt());
    }

    @Override
    public UserDto getByEmail(String email, String password) {
        UserPassword userPassword = new UserPassword(password, "TODO");
        User user = userDao.getByEmail(email);

        if (user == null) {
            throw new RuntimeException("User with email " + email + " not found.");
        }

        if (!userPassword.isValid(user.getPassword())) {
            throw new RuntimeException("Wrong user password");
        }

        UserToken userToken = userTokenDao.getCurrentUserToken(user.getId());

        return new UserDto(user.getId(), user.getEmail(), userToken.getToken(), user.getCreatedAt());
    }
}

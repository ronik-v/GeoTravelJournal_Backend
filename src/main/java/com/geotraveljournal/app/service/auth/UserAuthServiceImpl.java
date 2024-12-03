package com.geotraveljournal.app.service.auth;

import com.geotraveljournal.app.dao.auth.UserDao;
import com.geotraveljournal.app.dao.auth.UserTokenDao;
import com.geotraveljournal.app.dto.auth.UserDto;
import com.geotraveljournal.app.errors.core.CustomException;
import com.geotraveljournal.app.errors.core.messages.Auth;
import com.geotraveljournal.app.model.auth.User;
import com.geotraveljournal.app.model.auth.UserToken;
import com.geotraveljournal.app.utils.UserPassword;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserTokenDao userTokenDao;

    @Transactional
    @Override
    public UserDto add(String email, String password) {
        if (userDao.getByEmail(email) != null) {
            throw new CustomException(false, Auth.userIsNotExists);
        }

        UserPassword userPassword = new UserPassword(password, "TODO");
        User user = new User(email, userPassword.hashed());
        user = userDao.save(user);

        String token = UUID.randomUUID().toString();
        UserToken userToken = new UserToken(token, user.getId());
        userToken = userTokenDao.save(userToken);

        return new UserDto(user.getId(), user.getEmail(), userToken.getToken(), user.getCreatedAt());
    }

    @Transactional
    @Override
    public UserDto getByEmail(String email, String password) {
        UserPassword userPassword = new UserPassword(password, "TODO");
        User user = userDao.getByEmail(email);

        if (user == null) {
            throw new CustomException(false, Auth.userIsNotExists);
        }

        if (!userPassword.isValid(user.getPassword())) {
            throw new CustomException(false, Auth.passwordIsNotValid);
        }

        UserToken userToken = userTokenDao.getCurrentUserToken(user.getId());

        return new UserDto(user.getId(), user.getEmail(), userToken.getToken(), user.getCreatedAt());
    }
}

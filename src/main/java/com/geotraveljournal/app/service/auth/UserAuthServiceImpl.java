package com.geotraveljournal.app.service.auth;

import com.geotraveljournal.app.dao.auth.UserDao;
import com.geotraveljournal.app.dao.auth.UserTokenDao;
import com.geotraveljournal.app.dto.auth.UserDto;
import com.geotraveljournal.app.responses.core.CustomException;
import com.geotraveljournal.app.responses.core.messages.AuthErrors;
import com.geotraveljournal.app.model.auth.User;
import com.geotraveljournal.app.model.auth.UserToken;
import com.geotraveljournal.app.utils.UserPassword;
import io.github.cdimascio.dotenv.Dotenv;
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
    private final Dotenv envConfig;

    public UserAuthServiceImpl() {
        this.envConfig = Dotenv.load();
    }

    @Transactional
    @Override
    public UserDto add(String email, String password) {
        if (userDao.getByEmail(email) != null) {
            throw new CustomException(false, AuthErrors.thisUserIsAlreadyExists);
        }

        UserPassword userPassword = new UserPassword(password, envConfig.get("PASSWORD_SOLE"));
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
        UserPassword userPassword = new UserPassword(password, envConfig.get("PASSWORD_SOLE"));
        User user = userDao.getByEmail(email);

        if (user == null) {
            throw new CustomException(false, AuthErrors.userIsNotExists);
        }

        if (!userPassword.isValid(user.getPassword())) {
            throw new CustomException(false, AuthErrors.passwordIsNotValid);
        }
        String token = UUID.randomUUID().toString();
        UserToken userToken = new UserToken(token, user.getId());
        userToken = userTokenDao.save(userToken);

        return new UserDto(user.getId(), user.getEmail(), userToken.getToken(), user.getCreatedAt());
    }
}

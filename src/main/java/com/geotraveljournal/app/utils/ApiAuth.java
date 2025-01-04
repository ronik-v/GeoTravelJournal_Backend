package com.geotraveljournal.app.utils;

import com.geotraveljournal.app.dao.auth.UserDao;
import com.geotraveljournal.app.model.auth.User;
import com.geotraveljournal.app.responses.core.CustomException;
import com.geotraveljournal.app.responses.core.messages.AuthErrors;

import jakarta.servlet.http.HttpServletRequest;

public class ApiAuth {
    public static User getCurrentUser(HttpServletRequest request, UserDao userDao) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new CustomException(false, AuthErrors.emptyAuthHeader);
        }
        token = token.replace("Bearer ", "");
        User user = userDao.getByAuthToken(token);
        if (user == null) {
            throw new CustomException(false, AuthErrors.userAuthError);
        }
        return user;
    }

}

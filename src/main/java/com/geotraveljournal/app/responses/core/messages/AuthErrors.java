package com.geotraveljournal.app.responses.core.messages;

public class AuthErrors {
    public static final String thisUserIsAlreadyExists = "Пользователь с таким email уже зарегистрирован";
    public static final String userIsNotExists = "Такой пользователь не существует";
    public static final String passwordIsNotValid = "Некорректный пароль";
    public static final String userAuthError = "Пользователь не найден";
    public static final String emptyAuthHeader = "Отсутствует токен авторизации";
}

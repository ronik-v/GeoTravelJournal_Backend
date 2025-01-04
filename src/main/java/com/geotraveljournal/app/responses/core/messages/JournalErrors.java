package com.geotraveljournal.app.responses.core.messages;

public class JournalErrors {
    public static final String INVALID_JOURNAL_DATA = "Неверные данные журнала для добавления";
    public static final String INVALID_ID_OR_DATA = "Неверный ID или данные журнала для обновления";
    public static final String JOURNAL_NOT_FOUND = "Журнал с указанным ID не найден";
    public static final String INVALID_USER_ID = "Идентификатор пользователя не может быть пустым";
    public static final String INVALID_ID_OR_USER_ID = "Неверный ID или ID пользователя для удаления";
    public static final String USER_MISMATCH = "Идентификатор пользователя не совпадает с владельцем журнала";
}

package ui.lang;

import java.util.ListResourceBundle;

public class Bundle_spa_ECU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                //Socket
                {"numb_err", "Неправильный формат данных"},
                // LoginPage
                {"server_broke", "Извините, что-то не так с сервером. Пожалуйста, попробуйте позже"},
                {"server_unavailable", "Связь с сервером потеряна, подождите минуту, мы пытаемся ее восстановить"},
                {"smth_wrong", "Что-то пошло не так, пожалуйста, попробуйте еще раз"},

                {"sign_up", "Зарегистрироваться"},
                {"sign_in", "Войти"},
                {"question_enter", "Нет аккаунта?"},
                {"lang", "Язык"},
                {"login", "Логин"},
                {"password", "Пароль"},
                {"question_reg", "Уже есть аккаунт?"},
                {"username", "Имя пользователя"},
                // MainPage
                {"update_error", "Невозможно обновить значение таблицы"}
        };
    }
}

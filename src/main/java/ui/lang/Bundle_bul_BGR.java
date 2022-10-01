package ui.lang;

import java.util.ListResourceBundle;

public class Bundle_bul_BGR extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                //General
                {"numb_err", "Неправильный формат данных"},
                {"try_again", "Пожалуйста, попробуйте снова"},
                // LoginPage
                {"server_broke", "Извините, что-то не так с сервером. Пожалуйста, попробуйте позже"},
                {"server_unavailable", "Связь с сервером потеряна, подождите минуту, мы пытаемся ее восстановить"},
                {"smth_wrong", "Что-то пошло не так, пожалуйста, попробуйте еще раз"},

                {"sign_up", "Зарегистрироваться"},
                {"sign_in", "Войти"},
                {"login", "Логин"},
                {"password", "Пароль"},
                {"username", "Имя пользователя"},
                // MainPage
                {"update_error", "Невозможно обновить значение таблицы"},
                {"add", "Добавить элемент"},
                {"add_if_max", "Добавить если больше"},
                {"add_if_min", "Добавить если меньше"},
                {"clear", "Очистить"},
                {"count_greater_than_annual_turnover", "Количество организаций с годовым оборотом больше заданного"},
                {"execute_script", "Выполнить код из файла"},
                {"exit", "Выход"},
                {"history", "История запросов"},
                {"info", "Информация о коллекции"},
                {"show_ascending", "Показать по возрастанию"},
                {"show_descending", "Показать по убыванию"},
                {"remove_by_id", "Удалить по id"},
                {"show", "Показать"},
                {"update_id", "Обновить id"},
                {"help", "Помощь"},
                //Commands
                {"name", "Имя"},
                {"x_cord", "x - координата"},
                {"y_cord", "y - координата"},
                {"annual_turnover", "Годовой оборот"},
                {"type", "Тип"},
                {"zipcode", "Почтовый индекс"},
                {"public", "Публичный"},
                {"private_limited", "Частное общество с ограниченной ответственностью"},
                {"open_joint_stock", "Открытое акционерное общество"},
                {"filename", "Имя файла"},
                {"execute", "Выполнить"}


                //Socket
                {"numb_err", "Грешен формат на данните"},
                // LoginPage
                {"server_broke", "Съжаляваме, нещо не е наред със сървъра. Моля, опитайте отново по-късно"},
                {"server_unavailable", "Връзката със сървъра е прекъсната, моля, изчакайте малко, опитваме се да я възстановим"},
                {"smth_wrong", "Нещо се обърка.Моля опитайте отново"},

                {"sign_up", "Регистрирам"},
                {"sign_in", "Да вляза"},
                {"lang", "Язык"},
                {"login", "Влизам"},
                {"password", "Парола"},
                {"username", "Потребителско име"},
                // MainPage
                {"update_error", "Стойността на таблицата не може да се актуализира"}
        };
    }
}

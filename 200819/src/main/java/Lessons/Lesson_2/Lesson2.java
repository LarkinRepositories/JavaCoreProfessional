package Lessons.Lesson_2;
/*
1.Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java приложения
2.При запуске приложения очистить таблицу и заполнить 10 000 товаров
3.Написать консольное приложение которое позволяет узнать цену товара по его имени, либо вывести сообщение "Такого товара нет", если товар не обнаружен в базе.
Консольная команда "/цена товар545".
4.Добавить возможность изменения цены товара. Указываем имя товара и новую цену
Консольная команда "/сменитьцену товар10 10000"
5.Вывести твоары в заданном ценовом диапазоне.
Консольная команда: "/товарыпоцене 100 600";

 */

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lesson2 {
    private static final String DB_PATH = "lesson2.db" ;
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
        statement = connection.createStatement();
    }

    private static void disconnect() throws SQLException {
        connection.close();
    }

    private static void createTable(String tableName, String primaryKey, String...column) throws SQLException {
            String[] columns = column;
            preparedStatement = connection.prepareStatement("CREATE IF NOT EXIST TABLE ? (? INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ? INTEGER NOT NULL, ? TEXT, ? INTEGER NOT NULL) ");
            preparedStatement.setString(1, tableName);
            preparedStatement.setString(2, primaryKey);
    }

    public static void main(String[] args) {
        try {
            connect();
            createTable("users", "id", "prodid", "title", "cost");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
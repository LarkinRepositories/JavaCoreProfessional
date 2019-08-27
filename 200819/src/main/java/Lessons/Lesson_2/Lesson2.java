package Lessons.Lesson_2;
/*
1.Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java приложения //DONE
2.При запуске приложения очистить таблицу и заполнить 10 000 товаров
3.Написать консольное приложение которое позволяет узнать цену товара по его имени, либо вывести сообщение "Такого товара нет", если товар не обнаружен в базе.
Консольная команда "/цена товар545".
4.Добавить возможность изменения цены товара. Указываем имя товара и новую цену
Консольная команда "/сменитьцену товар10 10000"
5.Вывести твоары в заданном ценовом диапазоне.
Консольная команда: "/товарыпоцене 100 600";

 */

import org.sqlite.JDBC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class Lesson2 {
    private static final String DB_PATH = "lesson2";
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
        if (connection != null) {
            DatabaseMetaData metaData = connection.getMetaData();
        }
        statement = connection.createStatement();
        System.out.println(String.format("Соединение с базой данных %s установлено", DB_PATH));
    }

    private static void disconnect() throws SQLException {
        connection.close();
        System.out.println("Соединение с базой завершено");
    }

    private static void createTable() throws SQLException {
        statement.execute("CREATE  TABLE  IF NOT EXISTS `products` (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "prodid INTEGER NOT NULL UNIQUE," +
                "title TEXT NOT NULL UNIQUE," +
                "cost INTEGER NOT NULL); ");
    }

    private static void dropTable(String tableName) throws SQLException {
        statement.execute(String.format("DROP TABLE IF EXISTS %s", tableName));
    }

    private static void clearTable(String tableName) throws SQLException {
        statement.execute(String.format("DELETE from %s", tableName));
    }

    private static void fillTheTableWithProducts(int productCount) throws SQLException {
        if (productCount < 0 || productCount == 0) return;
        else {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products ('prodid', 'title', 'cost') VALUES (?, ?, ?)");
            for (int i = 0; i < productCount; i++) {
                preparedStatement.setInt(1, (int) Math.random() * 100 + 1 + i);
                preparedStatement.setString(2, "товар" + i);
                preparedStatement.setInt(3, (int) Math.random() * 10000 + 1 +  i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        }
    }

    private static void getProductPrice(String productTitle) throws SQLException {
        ResultSet rs = statement.executeQuery(String.format("SELECT cost from products WHERE title='%s'", productTitle));
        if (rs.next()) System.out.println(String.format("Цена за товар %s равна %s", productTitle, rs.getInt(1)));
        else System.out.println(String.format("Товара с именем %s нет в базе", productTitle));
    }

    private static void setProductPrice(String productTitle, int productPrice) throws SQLException {
        statement.executeUpdate(String.format("UPDATE products SET cost=%s WHERE title='%s'", productPrice, productTitle));
        System.out.println(String.format("Успешно изменена цена товара %s", productTitle));
    }

    private static void getProductsWithPriceRange(int from, int to) throws SQLException {
        if (from < 0 || to < 0) return;
        ResultSet rs = statement.executeQuery(String.format("SELECT title FROM products WHERE cost BETWEEN %s AND %s", from, to));
        List<String> productsWithPriceRange = new ArrayList<>();
        while (rs.next()) {
            productsWithPriceRange.add(rs.getString(1));
        }
        System.out.println(productsWithPriceRange);
    }

    public static void main(String[] args) {
        try {
            connect();
            dropTable("products");
            createTable();
            clearTable("products");
            fillTheTableWithProducts(10000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String consoleInput = reader.readLine();
                if (consoleInput.startsWith("/цена ")) {
                    String[] tokens = consoleInput.split(" ");
                    if (tokens.length == 2) { getProductPrice(tokens[1]); continue; }
                    else System.out.println("Введите наименование товара");
                    continue;
                }
                if (consoleInput.startsWith("/сменитьцену ")) {
                    String[] tokens = consoleInput.split(" ");
                    if (tokens.length == 3) {
                        setProductPrice(tokens[1], Integer.parseInt(tokens[2]));
                    }
                    else if (tokens.length == 2) {
                        System.out.println("Введите цену товара");
                    }
                    else if (tokens.length == 1) {
                        System.out.println("Введите наименование и цену товара");
                    }
                }
                if (consoleInput.startsWith("/товарыпоцене ")) {
                    String[] tokens = consoleInput.split(" ");
                    getProductsWithPriceRange(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                }
                if (consoleInput.equalsIgnoreCase("/exit")) break;
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
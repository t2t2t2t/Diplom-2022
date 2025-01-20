package com.database;
import java.sql.*;

public class DBConnect {
    public Connection databaseLink;
     public Connection getConnection(){

       String databaseName="theory-game";
        String databaseUser="dbc";
        String databasePassword="1234";
      //   String databaseUser="root";
       //  String databasePassword="root";
      //  String url="jdbc:mysql://localhost:3306/"+databaseName;
         //127.0.0.1
         String url="jdbc:mysql://192.168.1.101:3306/"+databaseName+"?allowPublicKeyRetrieval=true&useSSL=false";

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to load driver class");
        }

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url,databaseUser,databasePassword);
            System.out.println(databaseLink);

            if(! databaseLink.isClosed()){
                System.out.println("Connection established");
            }
        }catch (Exception e){
            System.err.println("Connection not established");
            e.printStackTrace();
        }
        System.out.println("Connection close");
        return databaseLink;
    }

    public boolean checkExampleExistence(int userId, int exampleId) {
        String query = "SELECT COUNT(*) AS count FROM statistics WHERE user_id = ? AND example_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, exampleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void addStatisticLecture(int userId, int exampleId) {
        String query = "INSERT INTO statistics (user_id, lecture_id) " +
                "SELECT ?, ? FROM DUAL " +
                "WHERE NOT EXISTS (SELECT * FROM statistics WHERE user_id = ? AND lecture_id = ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, exampleId);
            statement.setInt(3, userId);
            statement.setInt(4, exampleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addStatisticTask(int userId, int taskId) {
        String query = "INSERT INTO statistics (user_id, task_id) " +
                "SELECT ?, ? FROM DUAL " +
                "WHERE NOT EXISTS (SELECT * FROM statistics WHERE user_id = ? AND task_id = ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, taskId);
            statement.setInt(3, userId);
            statement.setInt(4, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStatisticExample(int userId, int exampleId) {
        String query = "DELETE FROM statistics WHERE user_id = ? AND example_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, exampleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLecture(String name, String file, int topicId) {
        String query = "INSERT INTO lectures (name, file, topic_id) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, file);
            statement.setInt(3, topicId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addExample(String name, String file, int topicId) {
        String query = "INSERT INTO examples (name, file, topic_id) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, file);
            statement.setInt(3, topicId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addTest(String name, String file, int topicId) {
        String query = "INSERT INTO tests (name, file, lecture_id) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, file);
            statement.setInt(3, topicId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addTask(String name, String file, int topicId) {
        String query = "INSERT INTO tasks (name, file, topic_id) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, file);
            statement.setInt(3, topicId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int findTopicIdByName(String topicName) {
        String query = "SELECT id FROM topics WHERE name = ?";
        int topicId = -1; // Значение по умолчанию, если id не будет найден

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, topicName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                topicId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topicId;
    }

    public void addStatisticExample(int userId, int exampleId) {
        String query = "INSERT INTO statistics (user_id, example_id) " +
                "SELECT ?, ? FROM DUAL " +
                "WHERE NOT EXISTS (SELECT * FROM statistics WHERE user_id = ? AND example_id = ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, exampleId);
            statement.setInt(3, userId);
            statement.setInt(4, exampleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticateUser(String username, String password) {
        try {
            // Проверяем, существует ли пользователь с таким username
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = databaseLink.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Пользователь с таким username существует

                // Проверяем правильность пароля
                String storedPassword = resultSet.getString("password");
                if (storedPassword.equals(password)) {
                    // Правильный пароль
                    System.out.println("Authentication successful!");
                    return true;
                } else {
                    // Неправильный пароль
                    System.err.println("Incorrect password!");
                    return false;
                }
            } else {
                // Пользователь с таким username не существует
                System.err.println("User does not exist!");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Failed to authenticate user.");
            e.printStackTrace();
            return false;
        }
    }

    public int getUserIdByUsername(String username) {
        try {
            // Проверяем, существует ли пользователь с таким username
            String query = "SELECT id FROM users WHERE username = ?";
            PreparedStatement preparedStatement = databaseLink.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Возвращаем user_id пользователя
                return resultSet.getInt("id");
            } else {
                // Пользователь с таким username не существует
                System.err.println("User does not exist!");
                return -1; // Возвращаем -1 в случае ошибки
            }
        } catch (SQLException e) {
            System.err.println("Failed to get user ID.");
            e.printStackTrace();
            return -1; // Возвращаем -1 в случае ошибки
        }
    }


    public int insertData(String username, String password) {
        try {
            // Проверяем, существует ли уже запись с таким username
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = databaseLink.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Запись с таким username уже существует
                System.err.println("Username already exists!");
                return -1; // Остановка метода, чтобы не выполнять вставку данных
            }

            // Подготовленное выражение для вставки данных
            query = "INSERT INTO users (username, password) VALUES (?, ?)";
            preparedStatement = databaseLink.prepareStatement(query);

            // Устанавливаем значения параметров
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Выполняем операцию вставки
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to insert data into the database.");
            e.printStackTrace();
        }
        return 0;
    }
}

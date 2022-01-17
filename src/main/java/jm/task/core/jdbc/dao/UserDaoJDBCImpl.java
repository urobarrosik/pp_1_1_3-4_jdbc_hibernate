package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE Users (" +
            "id int auto_increment primary key," +
            "name varchar(10) not null, " +
            "lastName varchar(10) not null, " +
            "age int not null)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Users (name,lastName,age) values ('"
                    + name +"','"
                    + lastName + "',"
                    + age + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("Delete from Users where id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            List<User> users = new ArrayList<User>();
            while(resultSet.next()) {
                users.add(new User(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4)));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("Delete from Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package org.example.daos;


import org.example.models.LoginRequest;
import org.example.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao {
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public User getUser(LoginRequest loginRequest) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String query = "SELECT username, password, loginID FROM User WHERE username = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, loginRequest.getUsername());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPasswordHash = resultSet.getString("password");

                System.out.println("hash from db: " + storedPasswordHash);

                if (BCrypt.checkpw(loginRequest.getPassword(), storedPasswordHash)) {

                    return new User(
                            resultSet.getString("username"),
                            storedPasswordHash,
                            resultSet.getInt("loginID")
                    );
                } else {
                    System.err.println("Password verification failed");
                }
            } else {
                System.err.println("User not found");
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
        return null;
    }


}
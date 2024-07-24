package org.example.daos;

import org.example.models.RoleDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDetailDao {
    public List<RoleDetails> getAllRoleDetails(final Connection connection)
            throws SQLException {
        List<RoleDetails> roleDetailsList = new ArrayList<>();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(
                "SELECT roleName, location, capability, band, "
                        + "closingDate, status FROM `Role` "
                        + "where status='open';");

        while (resultSet.next()) {
            RoleDetails details = new RoleDetails(
                    resultSet.getString("roleName"),
                    resultSet.getString("location"),
                    resultSet.getString("capability"),
                    resultSet.getString("band"),
                    resultSet.getInt(1)
            );

            roleDetailsList.add(details);
        }

        return roleDetailsList;
    }
}

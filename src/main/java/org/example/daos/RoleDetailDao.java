package org.example.daos;

import org.example.models.RoleDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDetailDao {
    public RoleDetail getRoleInformation(final int detailId)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "Select D.roleDetailId, D.detailName, "
                    + "D.description, D.responsibilities, "
                    + "D.link, R.location, R.capability, "
                    + "R.band, R.closingDate " + "From Role_Detail D "
                    + "JOIN Role R on D.roleDetailId = ?;";

            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, detailId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new RoleDetail(resultSet.getInt("roleDetailId"),
                        resultSet.getString("detailName"),
                        resultSet.getString("description"),
                        resultSet.getString("responsibilities"),
                        resultSet.getString("link"),
                        resultSet.getString("location"),
                        resultSet.getString("capability"),
                        resultSet.getString("band"),
                        resultSet.getDate("closingDate"));
            }
        }
        return null;
    }
}


package org.example.daos;

import org.example.models.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    public List<JobRole> getAllJobRoles(
            final Connection connection) throws SQLException {
        List<JobRole> jobRolesList = new ArrayList<>();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, roleName, location, capability, band, "
                            + "closingDate, status FROM `Role` "
                            + "where status='open';");
            while (resultSet.next()) {
                JobRole role = new JobRole(
                        resultSet.getInt("id"),
                        resultSet.getString("roleName"),
                        resultSet.getString("location"),
                        resultSet.getString("capability"),
                        resultSet.getString("band"),
                        resultSet.getDate("closingDate"),
                        resultSet.getString("status")
                );

                jobRolesList.add(role);
            }

        return jobRolesList;
    }
}

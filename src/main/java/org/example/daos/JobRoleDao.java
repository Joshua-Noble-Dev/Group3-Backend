
package org.example.daos;

import org.example.models.JobRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            "SELECT Role.id, Role.roleName, "
                    + "Role.location, Capability.capabilityName, "
                    + "Band.bandName, Role.closingDate, "
                    + "Role.status, Role.jobSpec, "
                    + "Role.responsibilities, Role.description "
                    + "FROM Role "
                    + "JOIN Capability ON "
                    + "Role.capabilityID = Capability.capabilityID "
                    + "JOIN Band ON Role.bandID = Band.bandID "
                    + "ORDER BY id ASC");

            while (resultSet.next()) {
                JobRole role = new JobRole.Builder()
                        .id(resultSet.getInt("id"))
                        .roleName(resultSet.getString("roleName"))
                        .location(resultSet.getString("location"))
                        .capabilityName(resultSet.getString("capabilityName"))
                        .bandName(resultSet.getString("bandName"))
                        .closingDate(resultSet.getDate("closingDate"))
                        .status(resultSet.getString("status"))
                        .description(resultSet.getString("description"))
                        .responsibilities(
                                resultSet.getString("responsibilities"))
                        .jobSpec(resultSet.getString("jobSpec"))
                        .build();

                jobRolesList.add(role);
            }

        return jobRolesList;
    }
    public JobRole getJobRoleById(final int id,
                                  final Connection connection)
        throws SQLException {
        String query =
                "SELECT Role.id, Role.roleName, "
                        + "Role.location, Capability.capabilityName, "
                        + "Band.bandName, Role.closingDate, "
                        + "Role.status, Role.jobSpec, "
                        + "Role.responsibilities, Role.description "
                        + "FROM Role "
                        + "JOIN Capability ON "
                        + "Role.capabilityID = Capability.capabilityID "
                        + "JOIN Band ON Role.bandID = Band.bandID "
                        + "WHERE Role.id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            return new JobRole.Builder()
                    .id(resultSet.getInt("id"))
                    .roleName(resultSet.getString("roleName"))
                    .location(resultSet.getString("location"))
                    .capabilityName(resultSet.getString("capabilityName"))
                    .bandName(resultSet.getString("bandName"))
                    .closingDate(resultSet.getDate("closingDate"))
                    .status(resultSet.getString("status"))
                    .description(resultSet.getString("description"))
                    .responsibilities(
                            resultSet.getString("responsibilities"))
                    .jobSpec(resultSet.getString("jobSpec"))
                    .build();
        }
        return null;
    }

    public void createApplication(final int jobId, final String applicantId,
                                  final String cvUrl, final String status,
                                  final Connection connection)
            throws SQLException {
        String sql = "INSERT INTO Applications ("
                + "job_id, applicant_id, cv_url, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, jobId);
            statement.setString(2, applicantId);
            statement.setString(3, cvUrl);
            statement.setString(4, status);
            statement.executeUpdate();
        }
    }
}

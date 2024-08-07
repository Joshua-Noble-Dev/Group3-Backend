
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
                    + "Role.responsibilities, Role.description, "
                    + "Role.positions FROM Role "
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
                        .positions(resultSet.getInt("positions"))
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
                        + "Role.responsibilities, Role.description, "
                        + "Role.positions FROM Role "
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
                    .positions(resultSet.getInt("positions"))
                    .build();
        }
        return null;
    }

    public int createApplication(final int roleId,
                                 final int userId,
                                 final String cvUrl,
                                 final String status,
                                 final Connection connection)
            throws SQLException {
        String sql = "INSERT INTO Application ("
                + "id, userId, cvUrl, status) VALUES (?, ?, ?, ?)";
        PreparedStatement st = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, roleId);
        st.setInt(2, userId);
        st.setString(3, cvUrl);
        st.setString(4, status);
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
       return -1;
    }
}

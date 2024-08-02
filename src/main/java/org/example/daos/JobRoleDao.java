
package org.example.daos;

import org.example.models.JobRole;
import org.example.models.JobRoleRequest;

import java.sql.Connection;
import java.sql.Date;
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
                    "SELECT id, roleName, location, "
                            + "capabilityID, bandID, "
                            + "closingDate, status, description, "
                            + "responsibilities, jobSpec FROM `Role` "
                            + "where status='open';");
            while (resultSet.next()) {
                JobRole role = new JobRole.Builder()
                        .id(resultSet.getInt("id"))
                        .roleName(resultSet.getString("roleName"))
                        .location(resultSet.getString("location"))
                        .capabilityID(resultSet.getInt("capabilityID"))
                        .bandID(resultSet.getInt("bandID"))
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
                "SELECT id, roleName, location, "
                        + "capabilityID, bandID, "
                        + "closingDate, status, description, "
                        + "responsibilities, jobSpec FROM `Role` "
                        + "WHERE id=?;";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            return new JobRole.Builder()
                    .id(resultSet.getInt("id"))
                    .roleName(resultSet.getString("roleName"))
                    .location(resultSet.getString("location"))
                    .capabilityID(resultSet.getInt("capabilityID"))
                    .bandID(resultSet.getInt("bandID"))
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

    public int createJobRole(JobRoleRequest jobRoleRequest, final Connection connection)
            throws SQLException {

        String insertStatement = "INSERT INTO `Role` "
                + "(roleName,location,capabilityID,"
                + "bandID,closingDate,status,jobSpec,"
                + "responsibilities,description,positions)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement st = connection.prepareStatement(
                insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1,jobRoleRequest.getRoleName());
        st.setString(2,jobRoleRequest.getLocation());
        st.setInt(3,jobRoleRequest.getCapabilityID());
        st.setInt(4,jobRoleRequest.getBandID());
        st.setDate(5, new Date(jobRoleRequest.getClosingDate().getTime()));
        st.setString(6, "open");
        st.setInt(7,jobRoleRequest.getPositions());
        st.setString(8,jobRoleRequest.getResponsibilities());
        st.setString(9,jobRoleRequest.getDescription());
        st.setInt(10,jobRoleRequest.getPositions());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if(rs.next()) {
            return rs.getInt(1);
        }

        return -1;

    }


}

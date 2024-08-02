package org.example.daos;

import org.example.models.Capability;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapabilityDao {

    public List<Capability> getAllCapabilities(final Connection connection) throws
            SQLException {
        List<Capability> capabilities = new ArrayList<>();

        Statement st = connection.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT capabilityID, capabilityName FROM Capability;");

        while (rs.next()) {
            Capability capability = new Capability(
                    rs.getInt("capabilityID"),
                    rs.getString("capabilityName")
            );
            capabilities.add(capability);
        }

        return capabilities;
    }

}

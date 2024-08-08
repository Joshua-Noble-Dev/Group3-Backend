package org.example.daos;

import org.example.models.Band;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BandDao {

    public List<Band> getAllBands(final Connection connection)
            throws SQLException {
        List<Band> bands = new ArrayList<>();

        Statement st = connection.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT bandID, bandName FROM Band;");

        while (rs.next()) {
            Band band = new Band(
                    rs.getInt("bandID"),
                    rs.getString("bandName")
            );
            bands.add(band);
        }

        return bands;
    }
}

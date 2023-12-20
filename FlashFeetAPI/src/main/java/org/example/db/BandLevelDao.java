package org.example.db;

import org.example.cli.BandLevel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BandLevelDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<BandLevel> getAllBandLevels() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT bandLevel FROM BandLevel;");

        List<BandLevel> bandLevelList = new ArrayList<>();

        while (rs.next()){
            BandLevel bandLevel = new BandLevel(
                    rs.getString("bandLevel")
            );

            bandLevelList.add(bandLevel);
        }

        return bandLevelList;
    }
}

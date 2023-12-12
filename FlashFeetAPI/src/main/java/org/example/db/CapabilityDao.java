package org.example.db;

import org.example.cli.Capability;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapabilityDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<Capability> getAllCapabilities() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT capability FROM JobRole;");

        List<Capability> capabilityList= new ArrayList<>();

        while (rs.next()){
            Capability capability = new Capability(
                    rs.getString("capability")
            );

            capabilityList.add(capability);
        }

        return capabilityList;
    }
}

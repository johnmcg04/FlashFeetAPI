package org.example.db;

import org.example.cli.JobRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {


    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<JobRole> getAllJobRoles() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT jobRole FROM JobRole;");

        List<JobRole> jobRoleList= new ArrayList<>();

        while (rs.next()){
            JobRole jobRole = new JobRole(
                    rs.getString("jobRole")
            );

            jobRoleList.add(jobRole);
        }

        return jobRoleList;
    }

    public String getCapabilityByJobRole(String jRole) throws SQLException {
        String capability = "";
        Connection conn = databaseConnector.getConnection();
        Statement st = conn.createStatement();

        String sql = "SELECT capability FROM JobRole WHERE jobRole = ?";
        PreparedStatement prepState = conn.prepareStatement(sql);
        prepState.setString(1, jRole);

        ResultSet rs = prepState.executeQuery();

            if (rs.next()) {
                capability = rs.getString("capability");
            }


        return capability;
    }

}

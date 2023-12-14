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

        ResultSet rs = st.executeQuery("SELECT jobRole, jobSpecification, capability FROM JobRole");

        List<JobRole> jobRoleList= new ArrayList<>();

        while (rs.next()){
            JobRole jobRole = new JobRole(
                    rs.getString("jobRole"),
                    rs.getString("jobSpecification"),
                    rs.getString("capability")
            );

            jobRoleList.add(jobRole);
        }

        return jobRoleList;
    }


    public List<String> getAllCapabilities() throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        assert c != null;
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT capability FROM JobRole");

        List<String> capabilitiesList = new ArrayList<>();

        while (rs.next()){
            String cap = new String(
                    rs.getString("capability")
            );
            capabilitiesList.add(cap);
        }

        return capabilitiesList;
    }




}

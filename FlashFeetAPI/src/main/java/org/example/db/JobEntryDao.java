package org.example.db;

import org.example.cli.JobEntry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobEntryDao {


    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<JobEntry> getAllJobEntries() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT jobRole, jobSpecification FROM JobRole;");

        List<JobEntry> jobEntryList = new ArrayList<>();

        while (rs.next()){
            JobEntry jobEntry = new JobEntry(
                    rs.getString("jobRole"),
                    rs.getString("jobSpecification")
            );

            jobEntryList.add(jobEntry);
        }

        return jobEntryList;
    }

    public JobEntry getJobEntryByJobRole(String jobRole) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery( "SELECT jobRole, jobSpecification FROM JobRole WHERE JobRole = '" + jobRole +"'");

        while (rs.next()){
            return new JobEntry(
                    rs.getString("jobRole"),
                    rs.getString("jobSpecification")
            );
        }
        return null;
    }
}

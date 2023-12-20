package org.example.db;

import org.example.cli.JobEntry;
import org.example.cli.JobEntryRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobEntryDao {


    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<JobEntry> getAllJobEntries() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT jobRole, jobSpecification, capability, bandLevel, jobFamily, responsibilities, jobSpecSummary FROM JobRole;");

        List<JobEntry> jobEntryList = new ArrayList<>();

        while (rs.next()){
            JobEntry jobEntry = new JobEntry(
                    rs.getString("jobRole"),
                    rs.getString("jobSpecification"),
                    rs.getString("capability"),
                    rs.getString("bandLevel"),
                    rs.getString("jobFamily"),
                    rs.getString("responsibilities"),
                    rs.getString("jobSpecSummary")
            );

            jobEntryList.add(jobEntry);
        }

        return jobEntryList;
    }

    public JobEntry getJobEntryByJobRole(String jobRole) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery( "SELECT jobRole, jobSpecification, capability, bandLevel, jobFamily, responsibilities, jobSpecSummary FROM JobRole WHERE JobRole = '" + jobRole +"'");

        while (rs.next()){
            return new JobEntry(
                    rs.getString("jobRole"),
                    rs.getString("jobSpecification"),
                    rs.getString("capability"),
                    rs.getString("bandLevel"),
                    rs.getString("jobFamily"),
                    rs.getString("responsibilities"),
                    rs.getString("jobSpecSummary")
            );
        }
        return null;
    }

    public void updateJobEntry(String jobRole, JobEntryRequest jobEntry) throws SQLException{
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE JobRole SET jobRole = ?, jobSpecification = ?, capability = ?, bandLevel = ?, responsibilities = ?, jobSpecSummary = ? WHERE jobRole = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, jobEntry.getJobRole() );
        st.setString(2, jobEntry.getJobSpecification());
        st.setString(3, jobEntry.getCapability());
        st.setString(4, jobEntry.getBandLevel());
        st.setString(5, jobEntry.getResponsibilities());
        st.setString(6, jobEntry.getJobSpecSummary());
        st.setString(7, jobRole);

        st.executeUpdate();
    }
}

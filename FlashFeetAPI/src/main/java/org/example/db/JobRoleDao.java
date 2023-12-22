package org.example.db;

import org.example.cli.JobRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<JobRole> getAllJobRoles(Connection connection) throws SQLException {

        Statement st = connection.createStatement();

        ResultSet rs = st.executeQuery("SELECT jobRole FROM JobRole");

        List<JobRole> jobRoleList= new ArrayList<>();

        while (rs.next()){
            JobRole jobRole = new JobRole(
                    rs.getString("jobRole")
            );

            jobRoleList.add(jobRole);
        }

        return jobRoleList;
    }

    public JobRole getJobRole(String jobRole) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String selectStatement = "SELECT jobRole FROM JobRole where jobRole= ?" ;

        PreparedStatement st = c.prepareStatement(selectStatement);

        st.setString(1, jobRole);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            return new JobRole(
                    rs.getString("jobRole")
            );
        }
        return null;
    }

    public int deleteJobRole(String jobRole) throws SQLException{
        try{
            Connection c = databaseConnector.getConnection();

            String deleteStatement = "DELETE FROM JobRole WHERE jobRole = ?";

            PreparedStatement st = c.prepareStatement(deleteStatement);

            st.setString(1, jobRole);

            st.executeUpdate();
            return 1;
        }


        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return -1;
        }
    }

}

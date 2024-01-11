package org.example.db;

import org.example.cli.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<JobRole> getAllJobRoles(Connection connection) throws SQLException {

        // Connection c = databaseConnector.getConnection();

        Statement st = connection.createStatement();

        ResultSet rs = st.executeQuery("SELECT jobRole FROM JobRole ORDER BY jobRole ASC;");

        List<JobRole> jobRoleList= new ArrayList<>();

        while (rs.next()){
            JobRole jobRole = new JobRole(
                    rs.getString("jobRole")
            );

            jobRoleList.add(jobRole);
        }

        return jobRoleList;
    }

}

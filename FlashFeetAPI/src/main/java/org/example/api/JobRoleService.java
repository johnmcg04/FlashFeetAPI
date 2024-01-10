package org.example.api;

import org.example.cli.JobRole;
import org.example.client.FailedToDeleteJobRoleException;
import org.example.client.FailedToGetJobsException;
import org.example.client.JobRoleDoesNotExistException;
import org.example.db.DatabaseConnector;
import org.example.db.JobRoleDao;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private JobRoleDao jobRoleDao = new JobRoleDao();

    public DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobRoleService(){

    }

    public JobRoleService(JobRoleDao jobRoleDao, DatabaseConnector databaseConnector) {
        this.jobRoleDao = jobRoleDao;
        this.databaseConnector = databaseConnector;
    }

    public List<JobRole> getAllJobRoles() throws FailedToGetJobsException, SQLException {
        return jobRoleDao.getAllJobRoles(databaseConnector.getConnection());
    }

    public int deleteJobRole(String jobRole) throws JobRoleDoesNotExistException, FailedToDeleteJobRoleException {
        try {
            JobRole deleteJobRole = jobRoleDao.getJobRole(jobRole);
            if (deleteJobRole == null) {
                return -1;
            }
            else {
                jobRoleDao.deleteJobRole(jobRole);
                return 1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;

        }
    }
}

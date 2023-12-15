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

    public List<JobRole> getAllJobRoles() throws FailedToGetJobsException {
        try {
            List<JobRole> jobRoleList = jobRoleDao.getAllJobRoles();

            return jobRoleList;
        } catch (SQLException e) {
            throw new FailedToGetJobsException();
        }
    }

    public void deleteJobRole(String jobRole) throws JobRoleDoesNotExistException, FailedToDeleteJobRoleException {
        try {
            JobRole deleteJobRole = jobRoleDao.getJobRole(jobRole);
            if (deleteJobRole == null) {
                throw new JobRoleDoesNotExistException();
            }

            jobRoleDao.deleteJobRole(jobRole);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteJobRoleException();
        }
    }
}

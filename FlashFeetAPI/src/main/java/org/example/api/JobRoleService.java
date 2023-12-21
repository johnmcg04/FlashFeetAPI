package org.example.api;

import org.example.cli.JobRole;
import org.example.client.FailedToGetJobsException;
import org.example.db.DatabaseConnector;
import org.example.db.JobRoleDao;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private JobRoleDao jobRoleDao = new JobRoleDao();

    public JobRoleService(){

    }

    public JobRoleService(JobRoleDao jobRoleDao, DatabaseConnector databaseConnector) {
    }

    public List<JobRole> getAllJobRoles() throws FailedToGetJobsException {
        try {

            List<JobRole> jobRoleList = jobRoleDao.getAllJobRoles();

            return jobRoleList;
        } catch (SQLException e) {
            throw new FailedToGetJobsException();
        }
    }
}

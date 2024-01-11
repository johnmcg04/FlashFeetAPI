package org.example.api;

import org.example.cli.JobRole;
import org.example.client.FailedToGetJobsException;
import org.example.db.DatabaseConnector;
import org.example.db.JobRoleDao;

import java.sql.Connection;
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

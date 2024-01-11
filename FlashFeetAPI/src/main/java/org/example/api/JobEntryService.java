package org.example.api;

import org.example.cli.JobEntry;
import org.example.cli.JobEntryRequest;
import org.example.client.*;
import org.example.db.DatabaseConnector;
import org.example.db.JobEntryDao;
import org.example.exception.DatabaseConnectionException;
import org.example.validator.JobEntryValidator;

import java.sql.SQLException;
import java.util.List;

public class JobEntryService {

    private JobEntryDao jobEntryDao;
    private JobEntryValidator jobEntryValidator;


    public JobEntryService(JobEntryDao jobEntryDao, JobEntryValidator jobEntryValidator) {
        this.jobEntryDao = jobEntryDao;
        this.jobEntryValidator = jobEntryValidator;
    }

    public List<JobEntry> getAllJobEntries() throws FailedToGetJobEntriesException {
        try {

            List<JobEntry> jobEntryList = jobEntryDao.getAllJobEntries();

            return jobEntryList;
        } catch (SQLException e) {
            throw new FailedToGetJobEntriesException();
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    public JobEntry getJobEntryByJobRole(String jobRole) throws FailedToGetJobEntriesException, JobEntryDoesNotExistException {
        try {
            JobEntry jobEntry = jobEntryDao.getJobEntryByJobRole(jobRole);
            if (jobEntry == null){
                throw new JobEntryDoesNotExistException();
            }
            return jobEntry;
        } catch (SQLException e) {
            throw new FailedToGetJobEntriesException();
        }
    }

    public boolean createJobEntry(JobEntryRequest jobEntry) throws FailedToCreateJobEntryException, InvalidJobEntryException, SQLException {
        try {
            String validation = jobEntryValidator.isValidJobRole(jobEntry);

            if (validation != null) {
                throw new InvalidJobEntryException(validation);
            }
            return jobEntryDao.createJobEntry(jobEntry);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new FailedToCreateJobEntryException();
        }
    }

    public void updateJobEntry(String jobRole, JobEntryRequest jobEntry) throws InvalidJobEntryException,JobEntryDoesNotExistException, FailedToUpdateJobEntryException {
        try {
            String validation = jobEntryValidator.isValidJobRole(jobEntry);

            if (validation != null){
                throw new InvalidJobEntryException(validation);
            }

            JobEntry orderToUpdate = jobEntryDao.getJobEntryByJobRole(jobRole);

            if (orderToUpdate == null){
                throw new JobEntryDoesNotExistException();
            }

            jobEntryDao.updateJobEntry(jobRole, jobEntry);

        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateJobEntryException();
        }
    }

}

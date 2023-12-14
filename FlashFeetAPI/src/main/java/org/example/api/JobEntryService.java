package org.example.api;

import org.example.cli.JobEntry;
import org.example.client.FailedToGetJobEntriesException;
import org.example.client.JobEntryDoesNotExistException;
import org.example.db.DatabaseConnector;
import org.example.db.JobEntryDao;

import java.sql.SQLException;
import java.util.List;

public class JobEntryService {

    private JobEntryDao jobEntryDao = new JobEntryDao();

    public JobEntryService(){

    }

    public JobEntryService(JobEntryDao jobEntryDao, DatabaseConnector databaseConnector) {
    }

    public List<JobEntry> getAllJobEntries() throws FailedToGetJobEntriesException {
        try {

            List<JobEntry> jobEntryList = jobEntryDao.getAllJobEntries();

            return jobEntryList;
        } catch (SQLException e) {
            throw new FailedToGetJobEntriesException();
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

}

package org.example.api.service;

import org.example.api.JobEntryService;
import org.example.cli.JobEntry;
import org.example.cli.JobEntryRequest;
import org.example.client.FailedToCreateJobEntryException;
import org.example.client.FailedToGetJobEntriesException;
import org.example.client.InvalidJobEntryException;
import org.example.db.JobEntryDao;
import org.example.exception.DatabaseConnectionException;
import org.example.validator.JobEntryValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobEntryServiceTests {

    JobEntryDao jobRoleDao = mock(JobEntryDao.class);
    JobEntryValidator jobEntryValidator = mock(JobEntryValidator.class);

    JobEntryService jobRoleService = new JobEntryService( jobRoleDao, jobEntryValidator);

    Connection c;

    public JobEntryServiceTests() throws SQLException {
    }

    @Test
    void getAllJobRoles_shouldReturnAllJobRoles_whenDaoReturnsAllJobRoles() throws SQLException, FailedToGetJobEntriesException, DatabaseConnectionException {

        List<JobEntry> listOfJobRoles = Collections.singletonList(
                mock(JobEntry.class)
        );

        Mockito.when(jobRoleDao.getAllJobEntries()).thenReturn(listOfJobRoles);
        assertEquals(listOfJobRoles, jobRoleService.getAllJobEntries());
    }




    @Test
    void createJobRole_shouldReturnTrue_whenDaoReturnsTrue() throws SQLException, FailedToCreateJobEntryException, InvalidJobEntryException {

        JobEntryRequest jobEntry = new JobEntryRequest(
                "Job Role",
                "Job Spec Link",
                "Capability",
                "Band Level",
                "Job Family",
                "Responsibilities",
                "Job Spec Summary");

        Mockito.when(jobRoleDao.createJobEntry(jobEntry)).thenReturn(true);
        assertTrue( jobRoleService.createJobEntry(jobEntry));
    }

    @Test
    void createJobRole_shouldReturnFalse_whenInvalidJobEntry() throws SQLException, FailedToCreateJobEntryException, InvalidJobEntryException {

        JobEntryRequest jobEntry = new JobEntryRequest(
                "123456789012345678901234567890123456789012345678901234567890",
                "Job Spec Link",
                "Capability",
                "Band Level",
                "Job Family",
                "Responsibilities",
                "Job Spec Summary");

        Mockito.when(jobRoleDao.createJobEntry(jobEntry)).thenReturn(false);
        assertFalse(jobRoleService.createJobEntry(jobEntry));
    }
}

package org.example.api.service;

import org.example.api.JobEntryService;
import org.example.cli.JobEntry;
import org.example.cli.JobEntryRequest;
import org.example.client.FailedToCreateJobEntryException;
import org.example.client.FailedToGetJobEntriesException;
import org.example.client.InvalidJobEntryException;
import org.example.client.JobEntryDoesNotExistException;
import org.example.db.DatabaseConnector;
import org.example.db.JobEntryDao;
import org.example.validator.JobEntryValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.xml.crypto.Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class JobEntryServiceTests {

    JobEntryDao jobRoleDao = mock(JobEntryDao.class);
    JobEntryValidator jobEntryValidator = mock(JobEntryValidator.class);

    JobEntryService jobRoleService = new JobEntryService(jobRoleDao, jobEntryValidator);

    Connection c;

    public JobEntryServiceTests() throws SQLException {
    }

    @Test
    void getAllJobRoles_shouldReturnAllJobRoles_whenDaoReturnsAllJobRoles() throws SQLException, FailedToGetJobEntriesException {

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
        assertEquals(true, jobRoleService.createJobEntry(jobEntry));
    }
}

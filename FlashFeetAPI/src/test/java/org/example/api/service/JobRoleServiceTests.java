package org.example.api.service;

import org.example.api.JobEntryService;
import org.example.cli.JobEntry;
import org.example.client.FailedToGetJobEntriesException;
import org.example.db.DatabaseConnector;
import org.example.db.JobEntryDao;
import org.example.exception.JobRoleDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTests {

        JobEntryDao jobRoleDao = mock(JobEntryDao.class);
        DatabaseConnector databaseConnector = mock(DatabaseConnector.class);

        JobEntryService jobRoleService = new JobEntryService( jobRoleDao, databaseConnector);

        Connection c;

        @Test
        void getAllJobRoles_shouldReturnAllJobRoles_whenDaoReturnsAllJobRoles() throws FailedToGetJobEntriesException, SQLException {

                List<JobEntry> listOfJobRoles = Collections.singletonList(
                        mock(JobEntry.class)
                );

                Mockito.when(databaseConnector.getConnection()).thenReturn(c);
                Mockito.when(jobRoleDao.getAllJobEntries()).thenReturn(listOfJobRoles);
                assertEquals(listOfJobRoles, jobRoleService.getAllJobEntries());
        }
}







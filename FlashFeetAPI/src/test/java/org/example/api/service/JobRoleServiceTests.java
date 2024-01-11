package org.example.api.service;

import org.example.api.JobRoleService;
import org.example.cli.JobRole;
import org.example.client.FailedToGetJobsException;
import org.example.db.DatabaseConnector;
import org.example.db.JobRoleDao;
import org.example.exception.DatabaseConnectionException;
import org.example.exception.JobRoleDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTests {

        JobRoleDao jobRoleDao = mock(JobRoleDao.class);
        DatabaseConnector databaseConnector = mock(DatabaseConnector.class);

        JobRoleService jobRoleService = new JobRoleService( jobRoleDao, databaseConnector);

        Connection c;

        @Test
        void getAllJobRoles_shouldReturnAllJobRoles_whenDaoReturnsAllJobRoles() throws SQLException, DatabaseConnectionException, JobRoleDoesNotExistException, FailedToGetJobsException {

                List<JobRole> listOfJobRoles = Collections.singletonList(
                        mock(JobRole.class)
                );

                Mockito.when(databaseConnector.getConnection()).thenReturn(c);
                Mockito.when(jobRoleDao.getAllJobRoles(c)).thenReturn(listOfJobRoles);
                assertEquals(listOfJobRoles, jobRoleService.getAllJobRoles());
        }
}







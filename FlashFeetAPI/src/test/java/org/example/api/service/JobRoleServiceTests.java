package org.example.api.service;

import org.example.api.JobRoleService;
import org.example.cli.JobRole;
import org.example.client.FailedToGetJobsException;
import org.example.db.DatabaseConnector;
import org.example.db.JobRoleDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
        public void getAllJobRoles_ShouldReturnAllJobRoles_WhenDaoReturnsJobRoles() throws SQLException, FailedToGetJobsException {

                List<JobRole> roles = Arrays.asList(
                        mock(JobRole.class)
                );


                when(DatabaseConnector.getConnection()).thenReturn(c);
                when(jobRoleDao.getAllJobRoles(c)).thenReturn(roles);

                assertEquals(roles, jobRoleService.getAllJobRoles(databaseConnector));
        }






}

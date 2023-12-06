package org.example.api.service;

import org.example.api.JobRoleService;
import org.example.cli.JobRole;
import org.example.db.DatabaseConnector;
import org.example.db.JobRoleDao;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {

        JobRoleDao jobRoleDao = mock(JobRoleDao.class);
        DatabaseConnector databaseConnector = mock(DatabaseConnector.class);

        JobRoleService jobRoleService = new JobRoleService(jobRoleDao, databaseConnector);

        JobRole jRole = new JobRole(
                "Test engineer"
        );

}

package org.example.api.service;

import org.example.api.JobEntryService;
import org.example.cli.JobEntry;
import org.example.db.DatabaseConnector;
import org.example.db.JobEntryDao;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
@ExtendWith(MockitoExtension.class)
public class JobEntryServiceTest {

        JobEntryDao jobEntryDao = mock(JobEntryDao.class);
        DatabaseConnector databaseConnector = mock(DatabaseConnector.class);

        JobEntryService jobEntryService = new JobEntryService(jobEntryDao, databaseConnector);

        JobEntry jRole = new JobEntry(
                "Test engineer",
                "Test Specification",
                "Test capability",
                "Test bandLevel",
                "Test jobFamily",
                "Test responsibility"
        );

}

package org.example.api.service;

import org.example.api.CapabilityService;
import org.example.cli.Capability;
import org.example.db.CapabilityDao;
import org.example.db.DatabaseConnector;

import static org.mockito.Mockito.mock;

public class CapabilityServiceTest {

    CapabilityDao capabilityDao = mock(CapabilityDao.class);
    DatabaseConnector databaseConnector = mock(DatabaseConnector.class);

    CapabilityService capabilityService = new CapabilityService(capabilityDao, databaseConnector);

    Capability capability = new Capability(
            "Test engineer"
    );


}

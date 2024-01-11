package org.example.api;

import org.example.cli.Capability;
import org.example.cli.JobEntry;
import org.example.client.FailedToGetCapabilitiesException;
import org.example.client.FailedToGetJobEntriesException;
import org.example.db.CapabilityDao;
import org.example.exception.DatabaseConnectionException;

import java.sql.SQLException;
import java.util.List;

public class CapabilityService {

    private CapabilityDao capabilityDao = new CapabilityDao();
    public List<Capability> getAllCapabilities() throws FailedToGetCapabilitiesException {
        try {
            List<Capability> capabilityList = capabilityDao.getAllCapabilities();

            return capabilityList;
        } catch (SQLException e) {
            throw new FailedToGetCapabilitiesException();
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }
    }
}

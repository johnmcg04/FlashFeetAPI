package org.example.api;

import org.example.cli.Capability;
import org.example.client.FailedToGetCapability;
import org.example.db.DatabaseConnector;
import org.example.db.CapabilityDao;

import java.sql.SQLException;
import java.util.List;

public class CapabilityService {

    private CapabilityDao capabilityDao = new CapabilityDao();

    public CapabilityService(){

    }

    public CapabilityService(CapabilityDao capabilityDao, DatabaseConnector databaseConnector) {
    }

    public List<Capability> getAllCapabilities() throws FailedToGetCapability {
        try {
            List<Capability> capabilityList = capabilityDao.getAllCapabilities();

            return capabilityList;
        } catch (SQLException e) {
            throw new FailedToGetCapability();
        }
    }


}

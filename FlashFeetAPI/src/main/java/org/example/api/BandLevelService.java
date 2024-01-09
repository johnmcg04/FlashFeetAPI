package org.example.api;

import org.example.cli.BandLevel;
import org.example.client.FailedToGetBandLevelsException;
import org.example.db.BandLevelDao;

import java.sql.SQLException;
import java.util.List;

public class BandLevelService {
    private BandLevelDao bandLevelDao = new BandLevelDao();
    public List<BandLevel> getAllBandLevels() throws FailedToGetBandLevelsException {
        try {
            List<BandLevel> bandLevelList = bandLevelDao.getAllBandLevels();

            return bandLevelList;
        } catch (SQLException e) {
            throw new FailedToGetBandLevelsException();
        }
    }
}

package org.example.services;

import org.example.daos.BandDao;
import org.example.daos.DatabaseConnector;
import org.example.models.Band;

import java.sql.SQLException;
import java.util.List;

public class BandService {

    BandDao bandDao;
    private final DatabaseConnector databaseConnector;

    public BandService(BandDao bandDao, DatabaseConnector databaseConnector) {
        this.bandDao = bandDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Band> getAllBands() throws SQLException {
        return bandDao.getAllBands(databaseConnector.getConnection());
    }

}

package org.example.services;

import org.example.daos.DatabaseConnector;
import org.example.daos.RoleDetailDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.exceptions.FormatException;
import org.example.exceptions.InvalidException;
import org.example.models.RoleDetail;

import java.sql.SQLException;

public class RoleDetailService {
    RoleDetailDao roleDetailDao;
    DatabaseConnector databaseConnector;
    public RoleDetailService(final RoleDetailDao roleDetailDao,
                             final DatabaseConnector databaseConnector) {
        this.roleDetailDao = roleDetailDao;
        this.databaseConnector = databaseConnector;
    }

    public RoleDetail getRoleInformation(final String detailId)
            throws SQLException, FormatException, DoesNotExistException,
            InvalidException {
//        try {
//            int testDetailId = Integer.parseInt(detailId);
//        } catch (NumberFormatException e) {
//            throw new FormatException(Entity.ROLEDETAIL);
//        }

        int finalDetailId = Integer.parseInt(detailId);
        RoleDetail roleDetail = roleDetailDao.getRoleInformation(finalDetailId,
                DatabaseConnector.getConnection());

//        if (finalDetailId <= 0) {
//            throw new InvalidException(Entity.ROLEDETAIL);
//        } else if (roleDetail == null) {
//            throw new DoesNotExistException(Entity.ROLEDETAIL);
//        } else {
//            return roleDetail;
//        }
        return roleDetail;
    }
}

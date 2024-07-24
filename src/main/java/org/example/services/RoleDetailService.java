package org.example.services;

import org.example.daos.RoleDetailDao;
import org.example.models.RoleDetail;

import java.sql.SQLException;

public class RoleDetailService {
    RoleDetailDao roleDetailDao;
    public RoleDetailService(final RoleDetailDao roleDetailDao) {
        this.roleDetailDao = roleDetailDao;
    }

    public RoleDetail getRoleInformation(final int detailId)
            throws SQLException {
        return roleDetailDao.getRoleInformation(detailId);
    }
}

package controller;

import org.example.controllers.RoleDetailController;
import org.example.models.RoleDetail;
import org.example.services.RoleDetailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;


public class RoleDetailControllerTest {

    RoleDetailService roleDetailService = Mockito.mock(RoleDetailService.class);

    private final RoleDetailController roleDetailController = new RoleDetailController(roleDetailService);

    Date d1 = new Date(2024,8,2);

    private final RoleDetail roleDetail = new RoleDetail(
            1,
            "Engineer",
            "Description",
            "Responsibilities",
            "Link",
            "Belfast",
            "Engineering",
            "Entry",
            d1
    );

    @Test
    void getRoleInformation_shouldReturnRoleInformation_whenServiceReturnsROleInfo() {

        int id = 1;



    }


}

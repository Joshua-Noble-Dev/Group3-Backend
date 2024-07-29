package services;


import org.example.daos.DatabaseConnector;
import org.example.daos.RoleDetailDao;
import org.example.services.RoleDetailService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleDetailServiceTest {

    RoleDetailDao roleDetailDao = Mockito.mock(RoleDetailDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    RoleDetailService roleDetailService= new RoleDetailService(roleDetailDao, databaseConnector);

    EmployeeRequest employeeRequest = new EmployeeRequest(
            30000,
            "Tim",
            "Bloggs",
            "tbloggs@email.com",
            "1 Main Street",
            "Main Road",
            "Belfast",
            "Antrim",
            "BT99BT",
            "Northern Ireland",
            "12345678901",
            "12345678",
            "AA1A11AA"
    );

    Connection conn;
}

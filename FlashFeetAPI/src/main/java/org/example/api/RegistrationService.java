package org.example.api;

import org.example.cli.RegistrationDetails;
import org.example.client.FailedToRegisterException;
import org.example.client.InvalidRegisterException;
import org.example.client.RegisterUsernameAlreadyExistsException;
import org.example.db.DatabaseConnector;
import org.example.db.RegistrationDao;
import org.example.validator.RegistrationValidator;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationService {
    private final RegistrationDao roleDao;
    private final DatabaseConnector databaseConnector;
    private final RegistrationValidator registerValidator;

    public RegistrationService(
            RegistrationDao registerDao, DatabaseConnector databaseConnector,
            RegistrationValidator registerValidator) {
        this.roleDao = registerDao;
        this.databaseConnector = databaseConnector;
        this.registerValidator = registerValidator;
    }

    public void register(RegistrationDetails registerDetails)
            throws RegisterUsernameAlreadyExistsException, InvalidRegisterException,
            FailedToRegisterException, SQLException {
        try {
            Connection conn = databaseConnector.getConnection();
            if (roleDao.doesUsernameExist(conn, registerDetails.getUsername())) {
                throw new RegisterUsernameAlreadyExistsException();
            }

            RegistrationValidator.ValidationResult result =
                    registerValidator.validateRegistrationDetails(registerDetails);
            if (result == RegistrationValidator.ValidationResult.VALID) {
                roleDao.register(conn, registerDetails);
                return;
            }

            throw new InvalidRegisterException();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());

            throw new FailedToRegisterException();
        }
    }
}

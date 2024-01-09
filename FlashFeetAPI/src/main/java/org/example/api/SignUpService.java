package org.example.api;


import org.example.cli.SignUp;
import org.example.db.SignUpDao;
import org.example.db.DatabaseConnector;
import org.example.exception.FailedToLoginException;
import org.example.exception.FailedTogenerateTokenException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.sql.Connection;
import java.sql.SQLException;

public class SignUpService {
    private SignUpDao signUpDao = new SignUpDao();
    private static DatabaseConnector databaseConnector = new DatabaseConnector();
    static Connection c = databaseConnector.getConnection();

    public SignUpService(){
    }

    public SignUpService(SignUpDao signUpDao, DatabaseConnector databaseConnector) {
    }

    public static int signUpUser(SignUp signUp) throws FailedToLoginException, FailedTogenerateTokenException, Exception {
        //call hashing and salting method here
        SignUp saltedSignUpDetails = SignUpService.SaltUsernameAndPassword(signUp);
        SignUp hashedSignUpDetails = SignUpService.HashUsernameAndPassword(saltedSignUpDetails);

        return SignUpDao.signUpUser(hashedSignUpDetails, c); //returns -1 if failed to insert else 1 if valid insert
    }

    public static SignUp SaltUsernameAndPassword(SignUp signUpDetails) {
        String salt = generateSalt();
        signUpDetails.setSalt(salt);
        return signUpDetails;
    }

    public static SignUp HashUsernameAndPassword(SignUp saltedUsernameAndPassword) throws Exception {
        String salt = saltedUsernameAndPassword.getSalt();
        String password = saltedUsernameAndPassword.getPassword();
        String hashedPassword = hashPassword(password, salt);
        saltedUsernameAndPassword.setPassword(hashedPassword);
        return saltedUsernameAndPassword;
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) throws Exception {
        int iterations = 65536;
        int keyLength = 512;
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);

        PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
        SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hashedPassword = key.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}

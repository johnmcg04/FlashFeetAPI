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

    public static boolean signUpUser(SignUp signUp) throws FailedToLoginException, FailedTogenerateTokenException, Exception {
        //call hashing and salting method here
        try{
            //password validation here

            SignUp saltedSignUpDetails = SignUpService.SaltUsernameAndPassword(signUp);
            SignUp hashedSignUpDetails = SignUpService.HashPassword(saltedSignUpDetails);
            return SignUpDao.signUpUser(hashedSignUpDetails, c); //returns false if failed to insert else true if valid insert
        }
        catch(){

        }


    }

    public static SignUp SaltUsernameAndPassword(SignUp signUpDetails) {
        String salt = generateSalt();
        signUpDetails.setSalt(salt);
        return signUpDetails;
    }

    public static SignUp HashPassword(SignUp saltedPassword) throws Exception {
        String salt = saltedPassword.getSalt();
        String password = saltedPassword.getPassword();
        String hashedPassword = HashPassword(password, salt);
        saltedPassword.setPassword(hashedPassword); //have right hash
        return saltedPassword;
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String HashPassword(String password, String salt) throws Exception {
        if (password == null || salt == null) {
            throw new IllegalArgumentException("Password or salt is null");
        }
        try {
//            int iterations = 65536;
//            int keyLength = 512;
            int iterations = 1000;  // Temporary lower value for debugging
            int keyLength = 256;    // Temporary lower value for debugging
            char[] passwordChars = password.toCharArray();
            byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);

            PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
            SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hashedPassword = key.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(hashedPassword);

        } catch (Exception e) {
            e.printStackTrace();  // Print the exception details to the console
            throw new Exception("Error hashing password");
        }
    }
}

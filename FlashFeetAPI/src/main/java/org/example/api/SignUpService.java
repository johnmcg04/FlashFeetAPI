package org.example.api;


import org.example.cli.SignUp;
import org.example.db.SignUpDao;
import org.example.db.DatabaseConnector;
import org.example.exception.DatabaseConnectionException;
import org.example.exception.FailedToLoginException;
import org.example.exception.FailedToSignUpException;
import org.example.exception.FailedTogenerateTokenException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpService {
    private SignUpDao signUpDao = new SignUpDao();
    private static DatabaseConnector databaseConnector = new DatabaseConnector();
    static Connection c;

    static {
        try {
            c = databaseConnector.getConnection();
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SignUpService() throws DatabaseConnectionException, SQLException {
    }

    public SignUpService(SignUpDao signUpDao, DatabaseConnector databaseConnector) throws DatabaseConnectionException, SQLException {
    }

    public static boolean signUpUser(SignUp signUp) throws FailedToSignUpException, FailedTogenerateTokenException, Exception {
        //call hashing and salting method here
        try{
            //password validation here
            if(!validSignUpPassword(signUp.getPassword())){
                return false;
            }
            SignUp saltedSignUpDetails = SignUpService.SaltUsernameAndPassword(signUp);
            SignUp hashedSignUpDetails = SignUpService.HashPassword(saltedSignUpDetails);
            return SignUpDao.signUpUser(hashedSignUpDetails, c); //returns false if failed to insert else true if valid insert
        }
        catch(FailedToSignUpException ex){
            ex.getMessage();
        }
        return false;
    }

    public static boolean validSignUpPassword(String password) {
        // Check for at least 8 characters
        if (password.length() < 8) {
            return false;
        }
        // Check for at least 1 special character, 1 upper case, 1 lower case, and 1 digit
        String regex = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
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

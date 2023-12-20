package org.example.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.example.api.RegistrationService;
import org.example.cli.RegistrationDetails;
import org.example.db.RoleID;

public class RegistrationValidator {
    public static final int MIN_PASSWORD_LENGTH = 8;

    public enum ValidationResult {
        VALID,
        USERNAME_INCORRECT_FORMAT,
        PASSWORD_TOO_SHORT,
        PASSWORD_NO_UPPERCASE_CHARACTERS,
        PASSWORD_NO_LOWERCASE_CHARACTERS,
        PASSWORD_NO_SYMBOLS,
        INVALID_ROLE_ID
    }

    public ValidationResult validateUsername(String username) {
        String pattern = "^[a-zA-Z][a-zA-Z0-9]{7,19}$";
        Pattern regex = Pattern.compile(pattern);
        Matcher match = regex.matcher(username);
        if (match.matches()) {
            return ValidationResult.VALID;
        }
        return ValidationResult.USERNAME_INCORRECT_FORMAT;
    }

    public ValidationResult validatePassword(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return ValidationResult.PASSWORD_TOO_SHORT;
        }

        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return ValidationResult.PASSWORD_NO_UPPERCASE_CHARACTERS;
        }

        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            return ValidationResult.PASSWORD_NO_LOWERCASE_CHARACTERS;
        }

        String symbolsPattern = "[!@£$%^&*()#,./\\\\;:'\"\\-_+=\\[\\]{}|`~]";
        if (!Pattern.compile(symbolsPattern).matcher(password).find()) {
            return ValidationResult.PASSWORD_NO_SYMBOLS;
        }

        return ValidationResult.VALID;
    }

    public ValidationResult validateRoleId(int roleId) {
        if (roleId == RoleID.USER.getDBValue() || roleId == RoleID.ADMIN.getDBValue()) {
            return ValidationResult.VALID;
        }
        return ValidationResult.INVALID_ROLE_ID;
    }

    public ValidationResult validateRegistrationDetails(RegistrationDetails registerDetails) {
        ValidationResult result = validateUsername(registerDetails.getUsername());
        if (result != ValidationResult.VALID) {
            return result;
        }
        result = validateRoleId(registerDetails.getRoleId());
        if (result != ValidationResult.VALID) {
            return result;
        }
        return validatePassword(registerDetails.getPassword());
    }
}

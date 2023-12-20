package org.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.example.client.JWTExpiredException;
import org.example.db.RoleID;

import java.util.Date;

public final class JWTUtil {
    private JWTUtil() {

    }

    public static DecodedJWT decodeJWT(String jwt) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"));
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        return verifier.verify(jwt);
    }

    public static boolean isDecodedJWTExpired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(new Date());
    }

    public static int getRoleIdFromDecodedJWT(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("role_id").asInt();
    }

    public static boolean isAtLeastUser(String jwt)
            throws JWTVerificationException, JWTExpiredException {
        DecodedJWT decodedJWT = JWTUtil.decodeJWT(jwt);
        if (JWTUtil.isDecodedJWTExpired(decodedJWT)) {
            throw new JWTExpiredException();
        }
        int roleId = JWTUtil.getRoleIdFromDecodedJWT(decodedJWT);
        return roleId == RoleID.USER.getDBValue() || roleId == RoleID.ADMIN.getDBValue();
    }

    public static boolean isAdmin(String jwt)
            throws JWTVerificationException, JWTExpiredException {
        DecodedJWT decodedJWT = JWTUtil.decodeJWT(jwt);
        if (JWTUtil.isDecodedJWTExpired(decodedJWT)) {
            throw new JWTExpiredException();
        }
        int roleId = JWTUtil.getRoleIdFromDecodedJWT(decodedJWT);
        return roleId == RoleID.ADMIN.getDBValue();
    }
}

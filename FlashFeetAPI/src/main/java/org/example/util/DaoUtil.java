package org.example.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DaoUtil {
    private DaoUtil() {

    }

    public static ResultSet executeStatement(
            Connection conn, String statement, boolean isQuery, Object... values)
            throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(statement,
                Statement.RETURN_GENERATED_KEYS);
        DaoUtil.populatePreparedStatement(preparedStatement, values);
        if (isQuery) {
            return preparedStatement.executeQuery();
        }
        preparedStatement.executeUpdate();
        return null;
    }

    public static void populatePreparedStatement(
            PreparedStatement preparedStatement, Object... values)
            throws SQLException {
        for (int x = 0; x < values.length; x++) {
            preparedStatement.setObject(x + 1, values[x]);
        }
    }


}

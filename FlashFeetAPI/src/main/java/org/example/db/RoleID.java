package org.example.db;

public enum RoleID {
    ADMIN(1),
    USER(2);

    private final int dbValue;

    RoleID(int dbValue) {
        this.dbValue = dbValue;
    }

    public int getDBValue() {
        return this.dbValue;
    }
}

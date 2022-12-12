package de.tilldv.slutils.data;

public interface DataTypes {
    public enum ExecuteLocation {
        JOIN,
        LOGIN,
        NEVER
    }

    public enum AccessMode {
        OPEN,
        KNOWN,
        BLACKLIST,
        WHITELIST,
        CLOSED
    }
}

package edu.ntg.backend.framework.database;

public class FieldInfo {

    public String name;
    public String DBName;
    //Type 0 for String & 1 for long
    public int Type = 0;

    java.lang.reflect.Method setter;
}

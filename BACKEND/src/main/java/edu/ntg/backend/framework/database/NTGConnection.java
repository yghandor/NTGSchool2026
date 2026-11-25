package edu.ntg.backend.framework.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class NTGConnection {
    public NTGConnection(Connection con) {
        this.con = con;
        this.stm = stm;
    }

    protected Connection con = null;
    protected Statement stm = null;

    public Statement getStatement() throws SQLException {


        this.stm = this.con.createStatement();

        return stm;
    }

}

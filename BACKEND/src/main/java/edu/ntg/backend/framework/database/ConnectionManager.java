package edu.ntg.backend.framework.database;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ConnectionManager {

    private String url = "jdbc:postgresql://localhost:5432/test";
    private String uname = "postgres";
    private String pass = "hggi";

    int NConnection = 0;

    final static int MaxPoolSize = 10;

    private static ArrayList<NTGConnection> Connectionpool = new ArrayList<>();

    public NTGConnection fetchStatement() throws SQLException {

        synchronized (Connectionpool) {
            if (!Connectionpool.isEmpty()) {
                NTGConnection con = Connectionpool.get(0);
                Connectionpool.remove(con);
                return con;
            }
        }
        // create new connection as pool is empty
        {

            Connection con = DriverManager.getConnection(url, uname, pass);

            System.out.println("Connected to database successfully Created #" + (++NConnection));


            return new NTGConnection(con);
        }
    }


    public void freeResources(NTGConnection con) {
        try {

            if (con != null) {

                if (con.stm != null) {
                    con.stm.close();
                }
                if (Connectionpool.size() > MaxPoolSize) {
                    con.con.close();
                    System.out.println("Connected to database successfully destroyed #" + (--NConnection));

                } else {
                    Connectionpool.add(con);
                }


            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}

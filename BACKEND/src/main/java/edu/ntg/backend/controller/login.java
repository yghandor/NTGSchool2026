package edu.ntg.backend.controller;

import edu.ntg.backend.dto.LoginRequest;
import edu.ntg.backend.dto.LoginResponse;
import edu.ntg.backend.dto.NTGError;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
@RequestMapping("/rest")
public class login {


    @PostMapping("/login")
    public LoginResponse logUser(@RequestBody LoginRequest req) {
        Connection con = null;
        Statement stm = null;

        try {
            System.out.println("**** Try To Login " + req.getUsername());


            String Sql = BuildFeatchSqlForLoginVerfication(req);

            String url = "jdbc:postgresql://localhost:5432/test";
            String uname = "postgres";
            String pass = "hggi";
            con = DriverManager.getConnection(url, uname, pass);
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(Sql);
            LoginResponse re;
            if (rs.next()) {

                re = new LoginResponse(rs.getString("full_name"), rs.getString("email"), "03298740923840932");

            } else {

                re = new LoginResponse(new NTGError("Invalid User Name Or Password !", "LOG000"));
            }
            rs.close();

            return re;


        } catch (Exception ex) {
            ex.printStackTrace();
            return new LoginResponse(new NTGError(ex.getMessage(), "GN000"));
        } finally {
            freeResources(con, stm);
        }
    }

    private void freeResources(Connection con, Statement stm) {
        try {

            if (con != null) {

                if (stm != null) {
                    stm.close();
                }

                con.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private String BuildFeatchSqlForLoginVerfication(LoginRequest req) {
        StringBuffer Sql = new StringBuffer("select * from  public.users where lower(email) like '");
        Sql.append(req.getUsername().split("@")[0].toLowerCase());
        Sql.append("' || '@%'and password = '");
        Sql.append(req.getPassword());
        Sql.append("'");
        return Sql.toString();
    }


}

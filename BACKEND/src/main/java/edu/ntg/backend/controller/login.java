package edu.ntg.backend.controller;

import edu.ntg.backend.dto.LoginRequest;
import edu.ntg.backend.dto.LoginResponse;
import edu.ntg.backend.dto.NTGError;
import edu.ntg.backend.framework.database.ConnectionManager;
import edu.ntg.backend.framework.database.NTGConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;

@RestController
@RequestMapping("/rest")
public class login {


    @Autowired
    ConnectionManager con;

    @PostMapping("/login")
    public LoginResponse logUser(@RequestBody LoginRequest req) {
         NTGConnection ConInfo = null;

        try {
            System.out.println("**** Try To Login " + req.getUsername());
            ConInfo = con.fetchStatement();

            String Sql = BuildFeatchSqlForLoginVerfication(req);



            ResultSet rs = ConInfo.getStatement().executeQuery(Sql);
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
            con.freeResources(ConInfo);
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

package edu.ntg.backend.controller;

import edu.ntg.backend.dto.Department;
import edu.ntg.backend.dto.Users;
import edu.ntg.backend.framework.database.ConnectionManager;
import edu.ntg.backend.framework.database.ListOperation;
import edu.ntg.backend.framework.database.NTGConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class usersOperation {


    @Autowired
    ListOperation _ListFun;

    @GetMapping("/departments")
    public List<Department> getDepartments() throws Exception {

        return (List<Department>) _ListFun.getList(Department.class);

    }

    @GetMapping
    public List<Users> getUsers() throws Exception {

        try {
            return (List<Users>) _ListFun.getList(Users.class, "status = 1");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String featchSqlForUserList() {
        return "SELECT password, full_name, email, recid, user_name\n" +
                "\tFROM users; ";
    }

}

package edu.ntg.backend.dto;

import edu.ntg.backend.framework.database.NTGField;
import edu.ntg.backend.framework.database.NTGTable;

@NTGTable(name="users")
public class Users {

    @NTGField() private long recId;
    @NTGField(DBName = "user_name") private String userName;
    @NTGField() private String password;
    @NTGField() private String email;
    @NTGField(DBName = "full_name") private String fullName;


    public long getRecId() {
        return recId;
    }

    public void setRecId(long recId) {
        this.recId = recId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}

package edu.ntg.backend.dto;

public class LoginResponse {

    private String fullName;
    private String email;
    private String sessionID;
    private NTGError error;


    public LoginResponse() {
    }

    public LoginResponse(String fullName, String email, String sessionID) {
        this.fullName = fullName;
        this.email = email;
        this.sessionID = sessionID;
    }


    public LoginResponse(NTGError error) {
        this.error = error;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public NTGError getError() {
        return error;
    }

    public void setError(NTGError error) {
        this.error = error;
    }


}

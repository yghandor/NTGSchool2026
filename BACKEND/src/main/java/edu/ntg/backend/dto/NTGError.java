package edu.ntg.backend.dto;

public class NTGError {
    private String code;
    private String message;


    public NTGError()  {}
    public NTGError(String message, String code) {
        this.message = message;
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

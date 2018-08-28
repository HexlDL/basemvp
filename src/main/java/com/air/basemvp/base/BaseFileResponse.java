package com.air.basemvp.base;

import java.util.List;

public class BaseFileResponse {

    /**
     * code : 0000
     * message : Success
     * content : ["bW9uZ29kYiMjNWI1N2YyYjE3NjJhNjQxNDRhZTg5OTZmIyNhaXJfYnBtX3YwLjk3LnBkbQ=="]
     */

    private String code;
    private String message;
    private List<String> content;

    public boolean isSucceeded() {
        return code.equals("0000");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}

package com.deming.blog.exception;

public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("Login name exists"),
    LOGIN_USER_ERROR("Wrong username or password"),
    VOTE_REPEAT("Voted"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

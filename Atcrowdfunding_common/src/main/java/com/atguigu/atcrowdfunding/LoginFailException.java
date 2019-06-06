package com.atguigu.atcrowdfunding;

public class LoginFailException extends RuntimeException {
    public LoginFailException(String message){
        super(message);
    }
}

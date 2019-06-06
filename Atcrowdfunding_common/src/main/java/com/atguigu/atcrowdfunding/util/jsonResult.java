package com.atguigu.atcrowdfunding.util;

public class jsonResult {
    private boolean successful;
    private String message;
    private Page page;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean success) {
        this.successful = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }
}

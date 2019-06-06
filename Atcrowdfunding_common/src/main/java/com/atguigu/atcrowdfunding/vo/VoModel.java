package com.atguigu.atcrowdfunding.vo;

import com.atguigu.atcrowdfunding.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:jiege
 * @Date: 2019/6/4 16:47
 */
public class VoModel {
    private List<User> datas = new ArrayList<User>();
    private List<User> userList = new ArrayList<User>();
    private List<Integer> ids = new ArrayList<Integer>();

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<User> getDatas() {
        return datas;
    }

    public void setDatas(List<User> datas) {
        this.datas = datas;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}

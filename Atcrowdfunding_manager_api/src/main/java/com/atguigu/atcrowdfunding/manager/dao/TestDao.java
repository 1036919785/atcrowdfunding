package com.atguigu.atcrowdfunding.manager.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface TestDao {

     void deleteAccountByName(String username);
}

package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

import com.luv2code.aopdemo.Account;

// For component scanning -> it's a sub annotation of component. So we can actually inject this
// Implementation later on.
@Repository
public class AccountImpl implements AccountDAO {
    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK -> ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }
}

package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

import com.luv2code.aopdemo.Account;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountImpl implements AccountDAO {
    private String name;
    private String serviceCode;

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK -> ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        // Don't throw an exception
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        // Simulate an exception
        if (tripWire) {
            throw new RuntimeException("No soup for you!!");
        }
        List<Account> myAccounts = new ArrayList<>();
        Account account = new Account("pepe", "silver");
        Account account1 = new Account("alberto", "platinum");
        Account account2 = new Account("rojas", "gold");

        myAccounts.add(account);
        myAccounts.add(account1);
        myAccounts.add(account2);

        return myAccounts;
    }
}

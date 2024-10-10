package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;

import java.util.List;

public interface AccountDAO {
    void addAccount(Account theAccount, boolean vipFlag);
    boolean doWork();

    // This allows us to call getter/setter methods via the AccountDAO interface.
    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);

    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripWire);
}

package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

// For component scanning -> it's a sub annotation of component. So we can actually inject this
// Implementation later on.
@Repository
public class MembershipDAOImpl implements MembershipDAO {
    @Override
    public String addMembership() {
        System.out.println(getClass() + ": DOING MY DB WORK -> ADDING AN MEMBERSHIP");
        return "Sss";
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + ": goToSleep()");
    }
}

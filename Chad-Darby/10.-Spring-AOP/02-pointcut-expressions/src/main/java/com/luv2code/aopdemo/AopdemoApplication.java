package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	// Spring boot will automatically inject the dependency because of @Bean annotation
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDao,
											   MembershipDAO theMembershipDao,
											   TrafficFortuneService theTrafficFortuneService) {
		return runner -> {
			// demoBeforeAdvice(theAccountDao, theMembershipDao);
			// demoAfterReturningAdvice(theAccountDao);
			// demoAfterThrowingAdvice(theAccountDao);
			// demoAfterAdviceFinallyAdvice(theAccountDao);
			demoAroundAdvice(theTrafficFortuneService);
		};
	}

	private void demoAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("Calling getFortune()");

		// Simulate an exception
		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);

		System.out.println("My fortune is: " + data);
		System.out.println("Done!");
	}

	private void demoAfterAdviceFinallyAdvice(AccountDAO theAccountDao) {
		List<Account> myAccounts = null;

		try {
			// Add a boolean flag to simulate exceptions
			boolean tripWire = false;
			myAccounts = theAccountDao.findAccounts(tripWire);
		} catch (Exception exc) {
			System.out.println("Caught exception: " + exc);
		}

		System.out.println("\nAccount name: " + myAccounts);
	}

	private void demoAfterThrowingAdvice(AccountDAO theAccountDao) {
		List<Account> myAccounts = null;

		try {
			// Add a boolean flag to simulate exceptions
			boolean tripWire = true;
			myAccounts = theAccountDao.findAccounts(tripWire);
		} catch (Exception exc) {
			System.out.println("Caught exception: " + exc);
		}
		
		System.out.println("\nAccount name: " + myAccounts);
	}

	private void demoAfterReturningAdvice(AccountDAO theAccountDao) {
		// Call the method to find the accounts
		List<Account> myAccounts = theAccountDao.findAccounts();
		for (Account tempAccount : myAccounts) {
			System.out.println("Account name: " + tempAccount.getName() + " level: " + tempAccount.getLevel());
		}
	}

	private void demoBeforeAdvice(AccountDAO theAccountDao, MembershipDAO theMembershipDao) {
		Account myAccount = new Account();
		myAccount.setName("pepe");
		myAccount.setLevel("Platinum");

		// Call the account business method
		theAccountDao.addAccount(myAccount, true);

		// Call the membership business method
		theMembershipDao.addMembership();

		// Call doWork()
		theAccountDao.doWork();

		// Call goToSleep()
		theMembershipDao.goToSleep();

		// Call getter/setter methods
		theAccountDao.setName("Foobar");
		theAccountDao.setServiceCode("silver");

		String name = theAccountDao.getName();
		String serviceCode = theAccountDao.getServiceCode();
		System.out.println("name: " + name + " service code: " + serviceCode);
	}

}

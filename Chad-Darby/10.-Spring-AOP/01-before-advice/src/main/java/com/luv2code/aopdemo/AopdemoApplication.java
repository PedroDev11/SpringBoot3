package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	// Spring boot will automatically inject the dependency because of @Bean annotation
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDao, MembershipDAO theMembershipDao) {
		return runner -> {
			demoBeforeAdvice(theAccountDao, theMembershipDao);
		};
	}

	private void demoBeforeAdvice(AccountDAO theAccountDao, MembershipDAO theMembershipDao) {
		Account myAccount = new Account();

		// Call the account business method
		theAccountDao.addAccount(myAccount, true);

		// Call the membership business method
		theMembershipDao.addMembership();

		// Call doWork()
		theAccountDao.doWork();

		// Call goToSleep()
		theMembershipDao.goToSleep();
	}

}

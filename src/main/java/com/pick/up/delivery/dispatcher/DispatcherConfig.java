package com.pick.up.delivery.dispatcher;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DispatcherConfig {

	//@Bean
	CommandLineRunner commandLineRunner(DispatcherRepository dispatcherRepository) {
		return args -> {
			Dispatcher dispatcher;
			
			Dispatcher Michael = new Dispatcher("Michael", "Eshorome", "Shaba", "476253537625372", "0717424906",
					"shabamike", "Johannesburg South Africa", LocalDate.of(1990, Month.AUGUST, 23), "mic@mike.com",
					Gender.MALE, "Edo State");
			
			Dispatcher Joy = new Dispatcher("Joy", "Ogele", "Shaba", "486253537625372", "07174274906", "shabajoy",
					"Johannesburg South Africa", LocalDate.of(1990, Month.AUGUST, 23), "joy@mike.com", Gender.FEMALE,
					"Edo State");
			
			Dispatcher Blessing = new Dispatcher("Blessing", "Ehnemafa", "Shaba", "4762535376272", "07174234906",
					"bless", "Johannesburg South Africa", LocalDate.of(1990, Month.AUGUST, 23), "bles@mike.com",
					Gender.FEMALE, "Edo State");

			dispatcherRepository.saveAll(List.of(Michael, Joy, Blessing));
		};
	}
}
package com.geotraveljournal.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.geotraveljournal")
public class GeoTravelJournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoTravelJournalApplication.class, args);
	}

}

package com.geotraveljournal.app;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication(scanBasePackages = "com.geotraveljournal.app")
public class GeoTravelJournalApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().directory("./").load();

		System.setProperty("APP_NAME", Objects.requireNonNull(dotenv.get("APP_NAME")));
		System.setProperty("APP_PORT", Objects.requireNonNull(dotenv.get("APP_PORT")));
		System.setProperty("DB_URL", Objects.requireNonNull(dotenv.get("DB_URL")));
		System.setProperty("DB_USERNAME", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
		System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
		System.setProperty("PASSWORD_SOLE", Objects.requireNonNull(dotenv.get("PASSWORD_SOLE")));
		System.setProperty("WEATHER_TOKEN", Objects.requireNonNull(dotenv.get("WEATHER_TOKEN")));

		SpringApplication.run(GeoTravelJournalApplication.class, args);
	}

}

package com.snap.snap;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SnapApplication {

	public static void main(String[] args) {
		// SpringApplication.run(SnapApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SnapApplication.class);
        builder.headless(false).run(args);
	}

}

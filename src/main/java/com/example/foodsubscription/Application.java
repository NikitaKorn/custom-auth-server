package com.example.foodsubscription;

import com.example.foodsubscription.config.SslPropertiesInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.initializers(new SslPropertiesInitializer())
				.run(args);
	}
}

package in.ey.trs;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class GenerationMicroservice {
	
	public static void main(String[] args) {
		SpringApplication.run(GenerationMicroservice.class, args);
	}
}

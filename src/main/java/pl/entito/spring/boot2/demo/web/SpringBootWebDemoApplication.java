package pl.entito.spring.boot2.demo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import pl.entito.spring.boot2.demo.web.model.BookProperties;

@SpringBootApplication
@EnableConfigurationProperties(BookProperties.class)
public class SpringBootWebDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebDemoApplication.class, args);
	}
}

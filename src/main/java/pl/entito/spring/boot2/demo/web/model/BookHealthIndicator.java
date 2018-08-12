package pl.entito.spring.boot2.demo.web.model;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class BookHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {

		// check if up and running some
		return Health.status("666").build();
	}

}

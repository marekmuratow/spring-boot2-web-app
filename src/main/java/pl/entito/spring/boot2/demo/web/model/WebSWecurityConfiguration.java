package pl.entito.spring.boot2.demo.web.model;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSWecurityConfiguration extends WebSecurityConfigurerAdapter {

	String userPass = "$2a$10$7bN0bMn.ANI9q7uvKOtd1eWI7dziXgRJT/VjoDIcc1l6vDV28WbQ2";
	String adminPass = "$2a$10$RVOBQPLFrFsE3mYbk0PFT.zFzC263iCrIJZ3InQubTMlgu.BSmy7a";

	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password(userPass).roles("USER").build());
		manager.createUser(User.withUsername("admin").password(adminPass).roles("ADMIN", "USER", "ACTUATOR").build());
		return manager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// public access to actuator/health and actuator/info
		http.authorizeRequests().requestMatchers(EndpointRequest.to("info", "health")).permitAll()
				.requestMatchers(EndpointRequest.toAnyEndpoint()).hasAnyRole("ACTUATOR").antMatchers("/admin")
				.hasRole("ADMIN").antMatchers("/").hasRole("USER")
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().antMatchers("/**")
				.permitAll().and().httpBasic();

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

}

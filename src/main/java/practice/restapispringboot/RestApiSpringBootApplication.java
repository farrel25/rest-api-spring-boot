package practice.restapispringboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import practice.restapispringboot.utils.AuditorAwareImpl;

/**
 * @EnableJpaAuditing(auditorAwareRef = "auditorAware")
 * to enable auditing in JPA via annotation configuration.
 * @param auditorAwareRef diisi nama bean auditorAware
 */

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class RestApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiSpringBootApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

}

package practice.restapispringboot.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
    
    /**
     * By making it a Bean, the BCrypt object will be loaded
     * in the Spring Context and we can inject it from any
     * class that requires it.
     * 
     * Actually we can declare this Bean in RestApiSpringBootApplication.java,
     * but in this case we intentionally separate it using the utils package
     * so that we can declare several types of encoder when this application
     * is developed further and more complex.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

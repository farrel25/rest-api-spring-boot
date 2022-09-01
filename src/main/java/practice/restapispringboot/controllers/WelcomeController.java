package practice.restapispringboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // to mark this class as Rest Controller
@RequestMapping("/api/welcome") // to set up url endpoint
public class WelcomeController {

    /**
     * HOW TO RUN THIS PROJECT THROUGH TERMINAL
     * type the command below
     * mvn spring-boot:run
     * 
     * DEFAULT SPRING BOOT LOCALHOST PORT
     * The Spring Boot framework provides the 
     * default embedded server (Tomcat) to run 
     * the Spring Boot application. 
     * It runs on port 8080.
     * 
     * @return
     */
    
    /**
     * MAPPING ANNOTATION LIST
     * 
     * @GetMapping
     * @PostMapping
     * @DeleteMapping
     * @PutMapping
     * @OptionMapping
     * 
     * @return
     */
    @GetMapping // to mark this method as GET request
    public String welcome() {
        return "Welcome to Spring Boot REST API";
    }
}

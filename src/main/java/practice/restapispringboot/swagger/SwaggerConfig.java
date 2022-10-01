package practice.restapispringboot.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * To use Swagger UI, we need to add an additional Maven dependency:
 * springfox-swagger-ui
 * 
 * After that, we can test it in our browser by visiting:
 * @link http://localhost:8080/swagger-ui/
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        // return new Docket(DocumentationType.SWAGGER_2)
        // .select()
        // .apis(RequestHandlerSelectors.any())
        // .paths(PathSelectors.any())
        // .build();
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("practice.restapispringboot.controllers"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
            "REST API Spring Boot Documentation",
            "This is the documentaion of rest api with springboot",
            "v1.0",
            "Terms of Service",
            new Contact("Farrel Putra", "restapispringboot.com", "f.athaillahp@gmail.com"),
            "Apache License",
            "www.apache.com",
            Collections.emptyList()
            );
        return apiInfo;
    }
}

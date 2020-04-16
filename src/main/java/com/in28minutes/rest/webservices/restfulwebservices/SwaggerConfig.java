package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact("Kendy", "www.myurl.com", "kendyjm@gmail.com");
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("My API Documentation", "My Api description", "1.0", "urn:tos2",DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    private static final Set<String> PRODUCES_CONSUMES_DEFAULT = new HashSet<String>(Arrays.asList(MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE));
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .consumes(PRODUCES_CONSUMES_DEFAULT)
                .produces(PRODUCES_CONSUMES_DEFAULT)
                ;
    }
}

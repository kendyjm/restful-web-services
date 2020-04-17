# Context
* Formation: https://www.udemy.com/course/microservices-with-spring-boot-and-spring-cloud/
* Resources de la formation: [GitHub](http://github.com/in28minutes/spring-microservices) et [FAQ](https://github.com/in28minutes/in28minutes-initiatives/blob/master/The-in28Minutes-TroubleshootingGuide-And-FAQ/quick-start.md)

# Keywords
* **Service Definition** : 
    * [OData](https://www.odata.org) (standard for REST Service Definition), pas très utilisé, à voir si curieux...sinon en rester aux formats alternatifs (mais non standards) : WADL (pas populaire), et surtout Swagger.
    * Swagger : just put one dependency (springfox-swagger2) and a @Configuration (see SwaggerConfig class in this project) to get doc in json via endpoint /v2/api-docs , and another one (springfox-swagger-ui) to get Swagger UI via /swagger-ui.html
* **Exception handling** : ResponseEntityExceptionHandler, @ControllerAdvice, ResponseEntity
* **Validations** : @Valid, javax.validation.constraints.* (@Size, @Past etc)
* **HATEOAS** : Hypermedia as the Engine of Application State; With HATEOAS, a client interacts with a network application whose application servers provide information dynamically through hypermedia
* **Internationalization** : LocaleContextHolder, AcceptHeaderLocaleResolver
* **Content Negociation** : just add `jackson-dataformat-xml` to support response in xml (just an example)
* **Monitoring** : actuator, hal browser, management.endpoints.web.exposure.include
* **Static Filtering** : simply put an `@JsonIgnore` on each field to filter
* **Dynamic Filtering** : wrap the POJO into a `MappingJacksonValue` and pass it in to the RestTemplate (the biggest issue for me is the hardcoded fields name); `SimpleBeanPropertyFilter`, `SimpleFilterProvider`, `@JsonFilter` in the POJO.
    * As a user commented, `@JsonView` could be a good/better alternative; the biggest advantage of `@JsonView` is that there is no hardcoding of field names to filter, see https://spring.io/blog/2014/12/02/latest-jackson-integration-improvements-in-spring                  
* **Versioning** : 2 main strategies: URI/REQUESTPARAM versioning or CUSTOMHEADER/MEDIATYPE versioning, the first one is maybe the best
    * STARTING BUILDING OF AN API: CHOOSE YOUR VERSIONING STRATEGY !
* **Basic Authentification** : just add `spring-boot-starter-security`as a dependency

# Startup
* In IntelliJ, run maven goal spring-boot-run
* Enable Devtools with IntelliJ : https://mkyong.com/spring-boot/intellij-idea-spring-boot-template-reload-is-not-working/
 
# Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring HATEOAS](https://docs.spring.io/spring-hateoas/docs/current/reference/html/#fundamentals.obtaining-links.builder.methods)
* [Internationalization](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-internationalization)
* [Content Negociation](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#howto-write-an-xml-rest-service)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#production-ready)

# Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)


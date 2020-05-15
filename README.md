# Master Microservices with Spring Boot and Spring Cloud : Part 1 - Restful Web Services with Spring Boot

## Context

* Formation: <https://www.udemy.com/course/microservices-with-spring-boot-and-spring-cloud>
* Resources de la formation: [GitHub](https://github.com/in28minutes/spring-microservices/tree/master/02.restful-web-services) et [FAQ](https://github.com/in28minutes/in28minutes-initiatives/blob/master/The-in28Minutes-TroubleshootingGuide-And-FAQ/quick-start.md)

Instructor:

* [Ranga Rao Karanam](https://www.linkedin.com/in/rangakaranam)

### Udemy Certificate of completion

<https://www.udemy.com/certificate/UC-ed0e4498-34b0-485f-bce4-54782fc08206/>

## Content

* What is a RESTful Web Service?
* How to implement RESTful Web Services with Spring and Spring Boot?
* What are the best practices in designing RESTful Web Services?
* How to design Resources and GET, POST and DELETE operations?
* How to implement Validation for RESTful Web Services?
* How to implement Exception Handling for RESTful Web Services?
* What is HATEOAS? How to implement HATEOAS for a Resource?
* What are the different approach in versioning RESTful Services?
* How to use Postman to execute RESTful Service Requests?
* How to implement basic authentication with Spring Security?
* How to implement filtering for RESTful Services?
* How to monitor RESTful Services with Spring Boot Actuator?
* How to document RESTful Web Services with Swagger?
* How to connect RESTful Services to a backend with JPA?


## Keywords

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
  * As a user commented, `@JsonView` could be a good/better alternative; the biggest advantage of `@JsonView` is that there is no hardcoding of field names to filter, see <https://spring.io/blog/2014/12/02/latest-jackson-integration-improvements-in-spring>
* **Versioning** : 2 main strategies: URI/REQUESTPARAM versioning or CUSTOMHEADER/MEDIATYPE versioning, the first one is maybe the best
  * STARTING BUILDING OF AN API: CHOOSE YOUR VERSIONING STRATEGY !
* **Security / Basic Authentification** : just add `spring-boot-starter-security`as a dependency
* **REST best practices** : [See Octo Technology Reference Card, a MUST READ!!](https://blog.octo.com/wp-content/uploads/2014/12/OCTO-Refcard_API_Design_EN_3.0.pdf)
  * [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)
    * Level 0 : exposer les webservices SOAP en REST-style
      * <http://server/getPosts>
      * <http://server/deletePosts>
      * <http://server/doThis>
    * Level 1 : exposer les RESOURCES avec les URI qui conviennent (sans encore utiliser les bonnes méthodes HTTP)
      * <http://server/accounts>
      * <http://server/accounts/10>
    * Level 2 : Level 1 + utilisation correcte des méthodes HTTP
      * (GET|POST) <http://server/accounts>
      * (GET|PUT|DELETE) <http://server/accounts/10>  
    * Level 3 : Level 2 + HATEOAS
      * return DATA + NEXT POSSIBLE ACTIONS
  * Le client doit comprendre les WS
    * avoir une documentation suffisante et compréhensible
  * Utiliser au mieux les possibilités offertes par HTTP
    * Notamment en utilisant ses méthodes (GET, PUT, POST, DELETE) de façon appropriée
    * Renvoyer le satut de réponse le plus approprié (pas juste un 200, mais un 201 lors d'une création)
      * 200 - success
      * 404 - resource not found
      * 400 - bad request
      * 201 - created
      * 401 - unauthorized
      * 500 - server error
    * Pas d'informations sensibles dans les URI
    * Préférer le PLURIEL au SINGULIER
      * Préférer /users à /user
      * Préférer /users/1 to /user/1
    * Préférer des NOMS plutôt que des VERBES pour les resources
      * Préférer DELETE /users/1 plutôt que DELETE /deleteUser/1
      * PUT /posts/{id}/star // pour ajouter une étoile à un post
      * DELETE /posts/{id}/star // pour supprimer une étoile à un post

## Startup

* In IntelliJ, run maven goal spring-boot-run
* Enable Devtools with IntelliJ : <https://mkyong.com/spring-boot/intellij-idea-spring-boot-template-reload-is-not-working/>

## Reference Documentation

For further reference, please consider the following sections:

* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Data JPA - Query Creation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation)
* [Spring HATEOAS](https://docs.spring.io/spring-hateoas/docs/current/reference/html/#fundamentals.obtaining-links.builder.methods)
* [Internationalization](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-internationalization)
* [Content Negociation](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#howto-write-an-xml-rest-service)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#production-ready)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-security)

## Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)

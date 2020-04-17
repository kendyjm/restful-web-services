
package com.in28minutes.rest.webservices.restfulwebservices.versioning.header;

import com.in28minutes.rest.webservices.restfulwebservices.versioning.Name;
import com.in28minutes.rest.webservices.restfulwebservices.versioning.PersonV1;
import com.in28minutes.rest.webservices.restfulwebservices.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (custom) HEADER VERSIONING and MEDIA TYPE VERSIONING
 *
 * PROS: no URI pollution
 * CONS: misuse of http headers, no caching, not so user friendly (difficult to execute a request from a browser), not so easy to document
 */
@RestController
public class PersonHEADERorMEDIATYPEVersioningController {

    /* (custom) HEADER VERSIONING, used by Microsoft */
    @GetMapping(value = "/person", headers = "X-API-VERSION=1")
    public PersonV1 personV1CustomHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "person", headers = "X-API-VERSION=2")
    public PersonV2 personV2CustomHeader() {
        return new PersonV2(Name.builder().firstName("Bob").lastName("Charlie").build());
    }



    /* MEDIA TYPE VERSIONING (aka "content negociation" or ACCEPT HEADER VERSIONING), used by GITHUB */
    @GetMapping(value = "/person", produces = "application/odigo.application-v1+json")
    public PersonV1 personV1PARAM() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "person", produces = "application/odigo.application-v2+json")
    public PersonV2 personV2PARAM() {
        return new PersonV2(Name.builder().firstName("Bob").lastName("Charlie").build());
    }
}

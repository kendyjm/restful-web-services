
package com.in28minutes.rest.webservices.restfulwebservices.versioning.uri;

import com.in28minutes.rest.webservices.restfulwebservices.versioning.Name;
import com.in28minutes.rest.webservices.restfulwebservices.versioning.PersonV1;
import com.in28minutes.rest.webservices.restfulwebservices.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * URI VERSIONING and REQUEST PARAMETER VERSIONING
 * May be the easiest way to do versioning
 * but there is no perfect solution
 * STARTING BUILD AN API: CHOOSE YOUR VERSIONING STRATEGY !
 *
 * PROS: caching remains operational, user friendly, documentation remains easy
 * CONS: URI pollution
 */
@RestController
public class PersonURIorREQUESTPARAMVersioningController {

    /* URI VERSIONING, used by twitter */
    @GetMapping(value = "v1/person")
    public PersonV1 personV1URI() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "v2/person")
    public PersonV2 personV2URI() {
        return new PersonV2(Name.builder().firstName("Bob").lastName("Charlie").build());
    }



    /* REQUEST PARAMETER VERSIONING, used by Amazon */
    @GetMapping(value = "/person", params = "version=1")
    public PersonV1 personV1PARAM() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "person", params = "version=2")
    public PersonV2 personV2PARAM() {
        return new PersonV2(Name.builder().firstName("Bob").lastName("Charlie").build());
    }
}

package com.in28minutes.rest.webservices.restfulwebservices.filtered.staticaly;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class StaticFilteredController {

    @GetMapping(value = "/somebeanstaticfiltered")
    public SomeBeanStaticalyFiltered retrieveSomeBean() {
        return new SomeBeanStaticalyFiltered("filedValue1", "fieldValue2", "myPasswordIwantfilter");
    }

    @GetMapping(value = "/somebeanstaticfiltered-list")
    public List<SomeBeanStaticalyFiltered> retrieveListSomeBean() {
        return Arrays.asList(new SomeBeanStaticalyFiltered("filedValue1", "fieldValue2", "myPasswordIwantfilter"),
                new SomeBeanStaticalyFiltered("filedValue12", "fieldValue22", "myPasswordIwantfilter2"));
    }
}

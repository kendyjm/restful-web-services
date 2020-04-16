package com.in28minutes.rest.webservices.restfulwebservices.filtered;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class StaticFilteredController {

    @GetMapping(value = "/somebean")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("filedValue1", "fieldValue2", "myPasswordIwantfilter");
    }

    @GetMapping(value = "/somebean-list")
    public List<SomeBean> retrieveListSomeBean() {
        return Arrays.asList(new SomeBean("filedValue1", "fieldValue2", "myPasswordIwantfilter"),
                new SomeBean("filedValue12", "fieldValue22", "myPasswordIwantfilter2"));
    }
}

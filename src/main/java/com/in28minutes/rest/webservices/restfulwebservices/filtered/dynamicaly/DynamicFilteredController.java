package com.in28minutes.rest.webservices.restfulwebservices.filtered.dynamicaly;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DynamicFilteredController {

    public static final String FILTER_ID = "someID";

    @GetMapping(value = "/somebeandynamicfiltered")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBeanDynamicalyFiltered someBean = new SomeBeanDynamicalyFiltered("filedValue1", "fieldValue2", "myPasswordIwantfilter");

        // will filter field3password
        return dynamicFilter(someBean, SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2"));
    }

    @GetMapping(value = "/somebeandynamicfiltered-list")
    public MappingJacksonValue retrieveListSomeBean() {
        List<SomeBeanDynamicalyFiltered> someBeanDynamicalyFiltereds = Arrays.asList(new SomeBeanDynamicalyFiltered("filedValue1Iwantfilter", "fieldValue2", "myPassword3"),
                new SomeBeanDynamicalyFiltered("filedValue12Iwantfilter", "fieldValue22", "myPassword32"));

        // will filter field1
        return dynamicFilter(someBeanDynamicalyFiltereds, SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3Password"));
    }

    private MappingJacksonValue dynamicFilter(Object valueToFilter, PropertyFilter propertyFilter) {
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(FILTER_ID, propertyFilter);
        MappingJacksonValue mapping = new MappingJacksonValue(valueToFilter);
        mapping.setFilters(filterProvider);
        return mapping;
    }
}

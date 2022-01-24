package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    // field1, field2
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        return getMappingJacksonValue("field1", "field2", new MappingJacksonValue(someBean));
    }


    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeans(){
        List<SomeBean> list = Arrays.asList(
                new SomeBean("value1", "value2", "value3"),
                new SomeBean("value12", "value22", "value32")
        );

        return getMappingJacksonValue("field2", "field3", new MappingJacksonValue(list));
    }

    private MappingJacksonValue getMappingJacksonValue(String field1, String field2, MappingJacksonValue someBean) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(field1, field2);

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        someBean.setFilters(filters);

        return someBean;
    }
}
